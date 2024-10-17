/**
 * 
 */
package servicios;

import java.io.IOException;

/**
 * Lista de metodos de usuario
 * 
 * @author nrojlla 141024
 */
public interface UsuarioInterfaz {

	/**
	 * Dar alta usuario a nuevo usuario
	 * 
	 * @author nrojla 141024
	 * @throws IOException
	 */
	void DarAltaUsuario() throws IOException;

	/**
	 * Modificar los datos de usuario
	 * 
	 * @author nrojlla 151024
	 * @throws IOException
	 */
	void ModificarDtoUsuario() throws IOException;

	/**
	 * Eliminar usuario
	 * 
	 * @author nrojlla 171024
	 * @throws IOException 
	 */
	void BajaUsuario() throws IOException;

}
