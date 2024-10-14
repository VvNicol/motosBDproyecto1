/**
 * 
 */
package servicios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logica de metodos de fichero interfaz
 * 
 * @author nrojlla 141024
 */
public class FicheroImplementacion implements FicheroInterfaz {

	@Override
	public void EscribirFichero(String mensaje, String fichero) {

		try (PrintWriter p = new PrintWriter(new FileWriter(fichero, true))) {
			p.println(mensaje);
		} catch (IOException e) {
			System.out.println("No se ha podido escribir en el fichero" + e.getMessage());
		}
	}

}
