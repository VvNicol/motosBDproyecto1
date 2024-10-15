package servicios;

import java.io.IOException;

/**
 * Lista de metodos de menu interfaz
 * @author nrojlla 141024
 */
public interface MenuInterfaz {

	/**
	 * Vista de menu principal
	 * @return byte
	 * @throws IOException
	 */
	byte MenuPrincipal() throws IOException;

	/**
	 * Vista de menu para modificar datos
	 * @return
	 * @throws IOException 
	 */
	byte MenuDatos() throws IOException;

}
