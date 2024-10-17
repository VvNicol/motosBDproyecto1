/**
 * 
 */
package servicios;

import java.sql.Connection;
import java.util.List;

import dto.UsuarioDto;

/**
 * Lista de metodos de consultas postgresql
 * 
 * @author nrojlla 101024
 */
public interface ConsultaInterfaz {

	/**
	 * Query de todos los usuarios
	 * 
	 * @author nrojlla 101024
	 * @param conexion
	 * @return Connection
	 */
	public List<UsuarioDto> consultaUsuario(Connection conexion);

	/**
	 * Modifica el campo nombre
	 * 
	 * @author nrojlla 171024
	 * @param String
	 * @param String
	 * @param Connection
	 */
	public void ModificarNombreUsuario(String nuevoNombre, String dni, Connection conexion);

	/**
	 * Modifica el campo apellido
	 * 
	 * @author nrojlla 171024
	 * @param String
	 * @param String
	 * @param Connection
	 */
	public void ModificarApellidosUsuario(String nuevoApellido, String dni, Connection conexion);
	
	/**
	 * Modifica el campo dni
	 * 
	 * @author nrojlla 171024
	 * @param nuevoDni
	 * @param dni
	 * @param conexion
	 */
	public void ModificarDniUsuario(String nuevoDni, String dni, Connection conexion);
	
	/**
	 * Modifica el campo correo
	 * 
	 * @author nrojlla 171024
	 * @param nuevoCorreo
	 * @param dni
	 * @param conexion
	 */
	public void ModificarCorreoUsuario(String nuevoCorreo, String dni, Connection conexion);
	
	/**
	 * Modifica el campo Foto
	 * 
	 * @author nrojlla 171024
	 * @param nuevaFoto
	 * @param dni
	 * @param conexion
	 */
	public void ModificarFotoUsuario(String nuevaFoto, String dni, Connection conexion);
	
	/**
	 * Modifica la contraseña
	 * 
	 * @author nrojlla 171024
	 * @param nuevaPwd
	 * @param dni
	 * @param conexion
	 */
	public void ModificarPwdUsuario(String nuevaPwd, String dni, Connection conexion);
	
	/**
	 * Modifica el numero telefonico
	 * 
	 * @author nrojlla 171024
	 * @param nuevoTel
	 * @param dni
	 * @param conexion
	 */
	public void ModificarTelefonoUsuario(int nuevoTel, String dni, Connection conexion);

}
