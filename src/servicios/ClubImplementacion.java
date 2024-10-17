/**
 * 
 */
package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Logica de los metodos de club
 * 
 * @author nrojlla 161024
 */
public class ClubImplementacion implements ClubInterfaz {
	Scanner sc = new Scanner(System.in);
	ConexionInterfaz ci = new ConexionPostgresqlImplementacion();

	@Override
	public void DarAltaClub() {
		
		try (Connection conexion = ci.GenerarConexion()) {

			if (conexion != null) {
				System.out.println("················");
				System.out.println("Creación del club");
				System.out.println("················");
				System.out.println("Ingrese nombre del club");
				String nombre = sc.nextLine();
				System.out.println("Ingrese Descripcion del club");
				String descripcion = sc.nextLine();

				Long id = util.Util.GenerarIdClub(conexion);
				LocalDateTime creacion = LocalDateTime.now();

				String insertQuery = "INSERT INTO \"dlk_motos\".club (id_club, nombre_club, descripcion_club, creacion_club) VALUES (?,?,?,?) ";

				try (PreparedStatement ps = conexion.prepareStatement(insertQuery)) {

					ps.setLong(1, id);
					ps.setString(2, nombre);
					ps.setString(3, descripcion);
					ps.setTimestamp(4, java.sql.Timestamp.valueOf(creacion));// convertir el localDateTime a Timesstamp

					ps.executeUpdate();

					System.out.println("Club creado y guardado con éxito en la base de datos.");
				}

			} else {
				System.out.println("No se pudo establecer la conexión, conexion nula.");
			}
		} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
		}
	}

}
