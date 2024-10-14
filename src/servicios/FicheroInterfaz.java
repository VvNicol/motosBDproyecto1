/**
 * 
 */
package servicios;

/**
 * Lista de metodos de ficheros
 * 
 * @author nrojlla 141024
 */
public interface FicheroInterfaz {
	/**
	 * Abrir, escribir y cerrar fichero log
	 * 
	 * @param mensaje
	 * @param fichero
	 */
	void EscribirFichero(String mensaje, String fichero);

}
