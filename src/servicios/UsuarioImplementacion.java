package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

import controladores.Inicio;
import dto.UsuarioDto;

/**
 * Logica de metodos de Usuario interfaz
 * 
 * @author nrojlla
 */
public class UsuarioImplementacion implements UsuarioInterfaz {
	Scanner sc = new Scanner(System.in);
	ConexionInterfaz ci = new ConexionPostgresqlImplementacion();

	@Override
	public void DarAltaUsuario() throws IOException {
		// Primero hacer la conexión con la bd
		Connection conexion = ci.GenerarConexion();

		// Verificar si la conexión no es null
		if (conexion != null) {
			// Recibir los datos del nuevo usuario
			System.out.println("Ingrese nombre:");
			String nombre = sc.nextLine();
			System.out.println("Ingrese apellidos:");
			String apellido = sc.nextLine();
			System.out.println("Ingrese correo:");
			String correo = sc.nextLine();
			System.out.println("Ingrese contraseña:");
			String contrasenia = sc.nextLine();
			System.out.println("Ingrese telefono:");
			int tel = sc.nextInt();
			sc.nextLine(); // Consumir la línea nueva

			Long id = util.Util.GenerarIdUsuario(conexion); // Generar un ID único

			if (id != null) {
				// Crear un objeto UsuarioDto y setear los datos
				UsuarioDto u = new UsuarioDto();
				u.setNombre(nombre);
				u.setApellidos(apellido);
				u.setCorreo(correo);
				u.setContrasenia(contrasenia);
				u.setTelefono(tel);
				u.setId(id);
				u.setEsClub(false); // Establecer esClub a false

				// Agregar el nuevo usuario a la lista
				Inicio.UsuarioLista.add(u);

				// Consulta SQL para insertar el nuevo usuario
				String insertQuery = "INSERT INTO \"dlk_motos\".usuario (id_usuario, nombre_usuario, apellidos_usuario, correo_usuario, contra_usuario, tel_usuario, \"esClub\") VALUES (?, ?, ?, ?, ?, ?, ?)";

				// Ejecutar la inserción a la base de datos
				try (PreparedStatement ps = conexion.prepareStatement(insertQuery)) {
					ps.setLong(1, id);
					ps.setString(2, nombre);
					ps.setString(3, apellido);
					ps.setString(4, correo);
					ps.setString(5, contrasenia);
					ps.setInt(6, tel);
					ps.setBoolean(7, u.isEsClub()); // Establecer el valor de esClub

					// Ejecutar la inserción
					ps.executeUpdate();
					System.out.println("Usuario creado y guardado con éxito en la base de datos.");
				} catch (SQLException e) {
					System.out.println("Error al insertar usuario en la base de datos: " + e.getMessage());
				}
			} else {
				System.out.println("No se pudo generar un nuevo ID para el usuario.");
			}

			System.out.println("··Usuario creado con éxito··");
		} else {
			System.out.println("No se pudo establecer la conexión.");
		}
	}

}
