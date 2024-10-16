/**
 * 
 */
package servicios;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import dto.UsuarioDto;

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
		// TODO Auto-generated method stub
		try (Connection conexion = ci.GenerarConexion()) {

			if (conexion != null) {

				System.out.println("Para crear un club necesita ser un usuario");
				System.out.println("··········································");
				System.out.println("Ingrese su dni");
				String dni = sc.nextLine();

				UsuarioDto u = util.Util.BuscarUsuarioPorDni(dni, conexion); // Buscar en la BD dni
				if (u != null) {
					System.out.println("Ingrese nombre del club");
					String nombre = sc.nextLine();
					System.out.println("Ingrese Descripcion del club");
					String descripcion = sc.nextLine();
					
					long id = util.Util.GenerarIdClub(conexion);
					LocalDateTime creacion = LocalDateTime.now();
					
					
					

				} else {
					System.out.println("No se encontró un usuario con el DNI ingresado.");
				}

			} else {
				System.out.println("No se pudo establecer la conexión, conexion nula.");
			}
		} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
		}
	}

}
