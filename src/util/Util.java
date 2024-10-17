package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.ClubDto;
import dto.UsuarioDto;

/**
 * Clase Util con métodos útiles para la aplicación.
 * 
 * @author nrojlla 151024
 */
public class Util {

	/**
	 * Genera un nuevo ID para el club.
	 * 
	 * @param conexion Conexión a la base de datos
	 * @return El nuevo ID generado o null si ocurre un error
	 */
	public static Long GenerarIdUsuario(Connection conexion) {
		Long nuevoId = null; // Usar Long para permitir null

		if (conexion != null) {
			try (Statement stmt = conexion.createStatement()) {
				// Consulta para obtener el máximo ID existente
				String query = "SELECT COALESCE(MAX(id_usuario), 0) FROM \"dlk_motos\".usuario";

				try (ResultSet rs = stmt.executeQuery(query)) {
					if (rs.next()) {
						nuevoId = rs.getLong(1) + 1; // Incrementa el máximo ID existente
					} else {
						nuevoId = 1L; // Asignar 1 si no hay resultados
					}
				}
			} catch (SQLException s) {
				System.err.println("Error al obtener el nuevo ID: " + s.getMessage());
			}
		} else {
			System.err.println("La conexión es nula. No se puede generar el ID.");
		}

		return nuevoId; // Devuelve el nuevo ID, que puede ser null
	}

	/**
	 * Genera un nuevo ID para el club.
	 * 
	 * @param conexion Conexión a la base de datos
	 * @return El nuevo ID generado o null si ocurre un error
	 */
	public static long GenerarIdClub(Connection conexion) {

		Long nuevoId = null;
		if (conexion != null) {

			try (Statement stmt = conexion.createStatement()) {

				String query = "SELECT COALESCE(MAX(id_club),0) FROM \"dlk_motos\".club";

				try (ResultSet rs = stmt.executeQuery(query)) {
					if (rs.next()) {
						nuevoId = rs.getLong(1) + 1;
					} else {
						nuevoId = 1L;
					}
				}
			} catch (SQLException s) {
				System.err.println("Error al obtener el nuevo ID: " + s.getMessage());
			}

		} else {
			System.err.println("La conexión es nula. No se puede generar el ID.");
		}

		return nuevoId;
	}

	/**
	 * Busca un usuario en la base de datos por su dni.
	 *
	 * @param dni      El DNI del usuario que se busca.
	 * @param conexion La conexión a la base de datos.
	 * @return Un objeto UsuarioDto que representa el UsuarioDto encontrado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos, o null
	 *                      si no se encuentra.
	 */
	public static UsuarioDto BuscarUsuarioPorDni(String dni, Connection conexion) throws SQLException {
		UsuarioDto usuario = null;

		// Consulta SQL para buscar el usuario por DNI
		String selectQuery = "SELECT * FROM \"dlk_motos\".usuario WHERE dni_usuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(selectQuery)) {
			ps.setString(1, dni); // Asignar el DNI a la consulta

			// Ejecutar la consulta
			var resultSet = ps.executeQuery();

			// Verificar si se encontró un resultado y asignar los datos encontrado en obj
			// usuario
			if (resultSet.next()) {
				usuario = new UsuarioDto();

			}
		} catch (SQLException e) {
			System.out.println("Error al buscar usuario por DNI: " + e.getMessage());
		}

		return usuario;

	}

	/**
	 *
	 * @param nombre   El nombre del club a buscar.
	 * @param conexion La conexión a la base de datos.
	 * @return Un objeto ClubDto que representa el club encontrado, o null si no se
	 *         encuentra.
	 */
	public static ClubDto BuscarClubPorNombre(String nombre, Connection conexion) {
		ClubDto club = null;

		String selectQuery = "SELECT * FROM \"dlk_motos\".club WHERE nombre_club = ?";

		try (PreparedStatement ps = conexion.prepareStatement(selectQuery)) {
			ps.setString(1, nombre);

			var resultSet = ps.executeQuery();

			if (resultSet.next()) {
				club = new ClubDto();
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar club por nombre: " + e.getMessage());
		}

		return club;
	}
}
