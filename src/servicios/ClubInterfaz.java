/**
 * 
 */
package servicios;

import java.io.IOException;

/**
 * Lista de metodos de club
 * 
 * @author nrojlla 161024
 */
public interface ClubInterfaz {

	/**
	 * Accion de dar alta a un club
	 * 
	 * @author nrojlla 161024
	 * @throws IOException 
	 */
	void DarAltaClub() throws IOException;

	/**
	 * Eliminar club
	 * 
	 * @author nrojlla 161024
	 * @throws IOException 
	 */
	void BajaClub() throws IOException;

}
