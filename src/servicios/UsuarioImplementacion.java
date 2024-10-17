package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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
			System.out.println("Ingrese dni:");
			String dni = sc.nextLine();
			System.out.println("Ingrese correo:");
			String correo = sc.nextLine();
			System.out.println("Ingrese contraseña:");
			String contrasenia = sc.nextLine();
			System.out.println("Ingrese telefono:");
			int tel = sc.nextInt();
			sc.nextLine(); // Consumir la línea nueva

			Long id = util.Util.GenerarIdUsuario(conexion); // Generar un ID único

			if (id != null) {

				// Consulta SQL para insertar el nuevo usuario
				String insertQuery = "INSERT INTO \"dlk_motos\".usuario (id_usuario, nombre_usuario, apellidos_usuario, dni_usuario ,correo_usuario, contra_usuario, tel_usuario, \"esClub\") VALUES (?, ?, ?, ?, ?, ?, ?,?)";

				// Ejecutar la inserción a la base de datos
				try (PreparedStatement ps = conexion.prepareStatement(insertQuery)) {
					ps.setLong(1, id);
					ps.setString(2, nombre);
					ps.setString(3, apellido);
					ps.setString(4, dni);
					ps.setString(5, correo);
					ps.setString(6, contrasenia);
					ps.setInt(7, tel);
					ps.setBoolean(8, false);

					// Ejecutar la inserción
					ps.executeUpdate();
					System.out.println("Usuario creado y guardado con éxito en la base de datos.");
				} catch (SQLException e) {
					System.out.println("Error al insertar usuario en la base de datos: " + e.getMessage());
				}
			} else {
				System.out.println("No se pudo generar un nuevo ID para el usuario.");
			}

		} else {
			System.out.println("No se pudo establecer la conexión.");
		}
	}

	@Override
	public void BajaUsuario() throws IOException {

		try (Connection conexion = ci.GenerarConexion()) {

			if (conexion != null) {

				System.out.println("Ingrese su dni:");
				String dni = sc.nextLine();

				String eliminarUsuario = " DELETE FROM \"dlk_motos\".usuario WHERE dni_usuario = ?";

				try (PreparedStatement ps = conexion.prepareStatement(eliminarUsuario)) {
					ps.setString(1, dni);

					int filasEliminadas = ps.executeUpdate();
					if (filasEliminadas > 0) {
						System.out.println("Usuario eliminado con éxito.");
					} else {
						System.out.println("No se pudo eliminar el usuario.");
					}
				} catch (SQLException e) {
					System.out.println("Error al eliminar usuario: " + e.getMessage());
				}

			} else {
				System.out.println("No se encontró un usuario con el DNI ingresado.");
			}

		} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
		}

	}

	@Override
	public void ModificarDtoUsuario() throws IOException {
		MenuInterfaz mi = new MenuImplementacion();
		ConsultaInterfaz cz = new ConsultaPostgresqlImplementacion();

		// Try-catch para la conexión
		try (Connection conexion = ci.GenerarConexion()) {
			if (conexion != null) { // Verificar que no sea null
				System.out.println("Ingrese su DNI:");
				String dni = sc.nextLine();

				// Consulta para buscar usuario por DNI
				String buscarUsuarioPorDni = "SELECT * FROM \"dlk_motos\".usuario WHERE dni_usuario = ?";

				try (PreparedStatement ps = conexion.prepareStatement(buscarUsuarioPorDni)) {
					ps.setString(1, dni);
					var resultSet = ps.executeQuery();

					if (resultSet.next()) { // Si se encuentra el usuario
						boolean esCerrado = false;

						// Bucle de modificación de datos
						while (!esCerrado) {
							byte opcion = mi.MenuDatos(); // Muestra el menú
							switch (opcion) {
							case 0:
								System.out.println("Volviste");
								esCerrado = true;
								break;
							case 1:
								System.out.println("Ingrese su nuevo nombre:");
								String nuevoNombre = sc.nextLine();
								cz.ModificarNombreUsuario(nuevoNombre, dni, conexion);
								break;
							case 2:
								System.out.println("Ingrese su nuevo apellido:");
								String nuevoApellido = sc.nextLine();
								cz.ModificarApellidosUsuario(nuevoApellido, dni, conexion);
								break;
							case 3:
								System.out.println("Ingrese su nuevo DNI:");
								String nuevoDni = sc.nextLine();
								cz.ModificarDniUsuario(nuevoDni, dni, conexion);
								System.out.println("Para seguir modificando, ingrese de nuevo con su nuevo DNI.");
								esCerrado = true; // Terminar para volver a loguear
								break;
							case 4:
								System.out.println("Ingrese su nuevo correo:");
								String nuevoCorreo = sc.nextLine();
								cz.ModificarCorreoUsuario(nuevoCorreo, dni, conexion);
								break;
							case 5:
								System.out.println("Ingrese el nombre de la nueva foto:");
								String nuevaFoto = sc.nextLine();
								cz.ModificarFotoUsuario(nuevaFoto, dni, conexion);
								break;
							case 6:
								System.out.println("Ingrese su nueva contraseña:");
								String nuevaPwd = sc.nextLine();
								cz.ModificarPwdUsuario(nuevaPwd, dni, conexion);
								break;
							case 7:
								System.out.println("Ingrese su nuevo teléfono:");
								int nuevoTel = sc.nextInt();
								sc.nextLine(); // Consumir el salto de línea
								cz.ModificarTelefonoUsuario(nuevoTel, dni, conexion);
								break;
							default:
								System.out.println("Error, opción no válida: " + opcion);
							}
						}
					} else {
						System.out.println("No se encontró un usuario con el DNI ingresado.");
					}
				} catch (SQLException e) {
					System.out.println("Error al buscar el usuario: " + e.getMessage());
				}
			} else {
				System.out.println("No se pudo realizar la conexión.");
			}
		} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
		}
	}

}
