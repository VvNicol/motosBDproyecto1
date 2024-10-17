package controladores;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDto;
import servicios.ClubImplementacion;
import servicios.ClubInterfaz;
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

		MenuInterfaz mi = new MenuImplementacion();
		FicheroInterfaz fi = new FicheroImplementacion();
		UsuarioInterfaz ui = new UsuarioImplementacion();
		ClubInterfaz ci = new ClubImplementacion();

		byte opcion;
		boolean esCerrado = false;
		String mensaje = "";

		try {

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
					mensaje = "Dar alta club";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					ci.DarAltaClub();
					break;
				case 2:
					mensaje = "Dar alta usuario";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					ui.DarAltaUsuario();
					break;
				case 3:
					mensaje = "Eliminar club";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					ci.BajaClub();
					break;
				case 4:
					mensaje = "Eliminar usuario";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					ui.BajaUsuario();
					break;
				case 5:
					
					
					break;
				case 6:
					mensaje = "Modificar datos de usuario";
					fi.EscribirFichero(mensaje, fichero);
					System.out.println(mensaje);
					ui.ModificarDtoUsuario();
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
