package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import controladores.Inicio;
import dto.UsuarioDto;
import util.ADto;

/**
 * Metodos detallados de consulta interfaz
 * 
 * @author nrojlla
 */
public class ConsultaPostgresqlImplementacion implements ConsultaInterfaz {

	@Override
	public List<UsuarioDto> consultaUsuario(Connection conexion) {

		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ADto adto = new ADto();

		try {
			// Se abre una declaración
			declaracionSQL = conexion.createStatement();
			// Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"dlk_motos\".usuario");

			// Llamada a la conversión a UsuarioDto
			Inicio.UsuarioLista = adto.resultsAUsuarioDto(resultadoConsulta); // Se asigna a la lista estática

			int i = Inicio.UsuarioLista.size();
			System.out
					.println("[INFORMACIÓN-ConsultaPostgresqlImplementacion-consultaUsuario] Número de usuarios: " + i);

			// Cierre de conexión, declaración y resultado
			resultadoConsulta.close();
			declaracionSQL.close();
			conexion.close();

		} catch (SQLException e) {
			System.err.println(
					"[ERROR-ConsultaPostgresqlImplementacion-consultaUsuario] Error generando o ejecutando la declaración SQL: "
							+ e.getMessage());
		}

		return Inicio.UsuarioLista;
	}

	@Override
	public void ModificarNombreUsuario(String nuevoNombre, String dni, Connection conexion) {
		// Definir la consulta SQL para actualizar el nombre en la base de datos.
		// El símbolo "?" actúa como un parámetro que será reemplazado por valores
		// específicos más adelante.
		String updateQuery = "UPDATE \"dlk_motos\".usuario SET nombre_usuario = ? WHERE dni_usuario = ?";

		// try-catch para asegurarse de que el PreparedStatement se cierre
		// automáticamente.
		try (PreparedStatement ps = conexion.prepareStatement(updateQuery)) {
			ps.setString(1, nuevoNombre);// Asignar el nuevo nombre al primer parámetro de la
											// consulta (el "?")
			ps.setString(2, dni);// DNI como el segundo parámetro para identificar el registro a
									// actualizar

			// Ejecutar la consulta de actualización en la base de datos
			// Este método devuelve el número de filas afectadas por la actualización.
			int filasActualizadas = ps.executeUpdate();

			// Comprobar si la actualización fue exitosa verificando cuántas filas se han
			// actualizado
			if (filasActualizadas > 0) {// Si se actualizó al menos una fila, informar al usuario
										// que la operación fue exitosa
				System.out.println("Nombre modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el nombre.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el nombre en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarApellidosUsuario(String nuevoApellido, String dni, Connection conexion) {

		String updateQueryApellido = "UPDATE \"dlk_motos\".usuario SET apellidos_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryApellido)) {
			ps.setString(1, nuevoApellido);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Apellido modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el apellido.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el apellido en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarDniUsuario(String nuevoDni, String dni, Connection conexion) {

		String updateQueryDni = "UPDATE \"dlk_motos\".usuario SET dni_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryDni)) {
			ps.setString(1, nuevoDni);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Dni modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el dni.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el dni en la base de datos: " + e.getMessage());
		}

	}

	@Override
	public void ModificarCorreoUsuario(String nuevoCorreo, String dni, Connection conexion) {

		String updateQueryCorreo = "UPDATE \"dlk_motos\".usuario SET correo_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryCorreo)) {
			ps.setString(1, nuevoCorreo);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Correo modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el correo.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el correo en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarFotoUsuario(String nuevaFoto, String dni, Connection conexion) {

		String updateQueryFoto = "UPDATE \"dlk_motos\".usuario SET foto_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryFoto)) {
			ps.setString(1, nuevaFoto);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Foto modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el Foto.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar la Foto en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarPwdUsuario(String nuevaPwd, String dni, Connection conexion) {

		String updateQueryPwd = "UPDATE \"dlk_motos\".usuario SET contra_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryPwd)) {
			ps.setString(1, nuevaPwd);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Contraseña modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el Contraseña.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar la contraseña en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarTelefonoUsuario(int nuevoTel, String dni, Connection conexion) {

		String updateQueryTel = "UPDATE \"dlk_motos\".usuario SET tel_usuario = ? WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQueryTel)) {
			ps.setInt(1, nuevoTel);
			ps.setString(2, dni);

			int filasActualizadas = ps.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Telefono modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el numero telefonico.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el numero telefonico en la base de datos: " + e.getMessage());
		}
	}

	@Override
	public void ModificarNombreClub(String nuevoNombre, String nombre, Connection conexion) {

		String updateQuery = "UPDATE \"dlk_motos\".club SET nombre_club = ? WHERE nombre_club = ?";

		try (PreparedStatement ps = conexion.prepareStatement(updateQuery)) {
			ps.setString(1, nuevoNombre);
			ps.setString(2, nombre);

			int filasActualizadas = ps.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("Nombre modificado con éxito.");
			} else {
				System.out.println("No se ha podido modificar el nombre.");
			}
		} catch (SQLException e) {
			System.out.println("Error al actualizar el nombre en la base de datos: " + e.getMessage());
		}

	}
}
