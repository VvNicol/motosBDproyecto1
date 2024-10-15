package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación de la conexión a PostgreSQL con verificación de null
 * 
 * @author nrojlla 101024
 */
public class ConexionPostgresqlImplementacion implements ConexionInterfaz {

	@Override
	public Connection GenerarConexion() {
		String host = "localhost";
		String puerto = "5433";
		String nombreBD = "postgres";
		String usuario = "postgres";
		String contraseña = "4lt41r";

		// Cadena de conexión JDBC
		String url = "jdbc:postgresql://" + host + ":" + puerto + "/" + nombreBD;

		Connection conn = null;

		try {
			// Intentar establecer conexión a la base de datos
			conn = DriverManager.getConnection(url, usuario, contraseña);
			System.out.println("Conexión establecida con éxito");

			// Verificar si la conexión no es null
			if (conn == null || conn.isClosed()) {
				System.out.println("La conexión no se ha establecido correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión: " + e.getMessage());
		}

		// Verificar una última vez antes de retornar
		if (conn == null) {
			System.out.println("La conexión es null, por lo que no puede ser usada.");
		}

		return conn; // Retorna la conexión para ser usada en otro lugar si es necesario
	}
}
