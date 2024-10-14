/**
 * 
 */
package servicios;

import java.sql.Connection;

/**
 * 
 */
public interface ConexionInterfaz {
	
	/**
	 * Conecta con la bd de postgresql
	 * @return Connection
	 * @author nrojlla 101024
	 */
	Connection GenerarConexion();

}
