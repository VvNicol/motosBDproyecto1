package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // Importación de ArrayList
import java.util.List;

import dto.UsuarioDto;

/**
 * Clase de utilidad que contiene los métodos de paso a DTO
 * 
 * @author nrojlla 101024
 */
public class ADto {
	/**
	 * Método que pasa un resultSet con usuarios a lista de UsuarioDto
	 * 
	 * @param resultadoConsulta
	 * @return Lista de usuario
	 */
	public List<UsuarioDto> resultsAUsuarioDto(ResultSet resultadoConsulta) {
		List<UsuarioDto> listaUsuarios = new ArrayList<>();

		try {
			while (resultadoConsulta.next()) {
				listaUsuarios.add(new UsuarioDto(
					resultadoConsulta.getLong("id_usuario"),
					resultadoConsulta.getString("nombre_usuario"),
					resultadoConsulta.getString("apellidos_usuario"),
					resultadoConsulta.getString("dni_usuario"),
					resultadoConsulta.getString("correo_usuario"),
					resultadoConsulta.getString("foto_usuario"),
					resultadoConsulta.getString("contra_usuario"),
					resultadoConsulta.getInt("tel_usuario"),
					resultadoConsulta.getBoolean("esClub")
				));
			}
			
			int i = listaUsuarios.size();
			System.out.println("[INFORMACIÓN-ADto-resultsAUsuarioDto] Número de usuarios: " + i);
			
		} catch (SQLException e) {
			System.err.println("[ERROR-ADto-resultsAUsuarioDto] Error al pasar el ResultSet a lista de UsuarioDto: " + e.getMessage());
		}
		
		return listaUsuarios; // Retorna la nueva lista de usuarios
	}
}
