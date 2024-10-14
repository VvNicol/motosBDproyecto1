/**
 * 
 */
package servicios;

import java.sql.Connection;
import java.util.List;

import dto.UsuarioDto;

/**
 * Lista de metodos de consultas postgresql
 * @author nrojlla 101024
 */
public interface ConsultaInterfaz {

	/**
	 * Query de todos los usuarios
	 * @author nrojlla 101024
	 * @param conexion
	 * @return Connection
	 */
 	public List<UsuarioDto> consultaUsuario(Connection conexion);

}
