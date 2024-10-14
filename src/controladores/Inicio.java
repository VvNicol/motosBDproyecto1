package controladores;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDto;
import servicios.ConexionInterfaz;
import servicios.ConexionPostgresqlImplementacion;
import servicios.ConsultaInterfaz;
import servicios.ConsultaPostgresqlImplementacion;
import servicios.FicheroImplementacion;
import servicios.FicheroInterfaz;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;
import servicios.UsuarioImplementacion;
import servicios.UsuarioInterfaz;

public class Inicio {

	public static List<UsuarioDto> UsuarioLista = new ArrayList<UsuarioDto>();

	public static void main(String[] args) {

		LocalDate fecha = LocalDate.now();
		String directorioFichero = "ficheros";
		String fichero = directorioFichero + File.separator + fecha + " FicheroLog.txt";

		ConexionInterfaz ci = new ConexionPostgresqlImplementacion();
		ConsultaInterfaz cc = new ConsultaPostgresqlImplementacion();
		MenuInterfaz mi = new MenuImplementacion();
		FicheroInterfaz fi = new FicheroImplementacion();
		UsuarioInterfaz ui = new UsuarioImplementacion();

		byte opcion;
		boolean esCerrado = false;
		String mensaje = "";

		try {

			// Generar la conexión a la base de datos
			Connection conexion = ci.GenerarConexion();

			// Verificar si la conexión no es null
			if (conexion != null) {

				UsuarioLista = cc.consultaUsuario(conexion);

				System.out.println("Conexion");
			} else {
				System.out.println("No se pudo establecer la conexión.");
			}

			do {

				mensaje = "Menu principal";
				fi.EscribirFichero(mensaje, fichero);
				opcion = mi.MenuPrincipal();

				switch (opcion) {
				case 0:
					mensaje = "Menu cerrado";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					esCerrado = true;
					break;
				case 1:
					ui.DarAltaUsuario();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;

				default:
					mensaje = "la opcion " + opcion + "no existe";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					break;
				}

			} while (!esCerrado);

		} catch (Exception e) {
			mensaje = "Ocurrió un error: " + e.getMessage();
			System.out.println(mensaje);
			fi.EscribirFichero(mensaje, fichero);
			e.printStackTrace();
		}
	}
}
