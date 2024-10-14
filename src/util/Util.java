package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase Util con métodos útiles para la aplicación.
 */
public class Util {

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
                // Manejar el error adecuadamente según tus necesidades
            }
        } else {
            System.err.println("La conexión es nula. No se puede generar el ID.");
        }

        // Verifica si nuevoId es null y maneja el caso si es necesario
        if (nuevoId == null) {
            // Maneja el caso en que no se pudo generar un nuevo ID
            System.err.println("No se pudo generar un nuevo ID.");
        }

        return nuevoId; // Devuelve el nuevo ID, que puede ser null
    }
}
