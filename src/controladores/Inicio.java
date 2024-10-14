package controladores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDto;
import servicios.ConexionInterfaz;
import servicios.ConexionPostgresqlImplementacion;
import servicios.ConsultaInterfaz;
import servicios.ConsultaPostgresqlImplementacion;

public class Inicio {

	public static List<UsuarioDto> UsuarioLista = new ArrayList<UsuarioDto>();
	
    public static void main(String[] args) {
    	
    	
    	
        ConexionInterfaz ci = new ConexionPostgresqlImplementacion();
        ConsultaInterfaz cc = new ConsultaPostgresqlImplementacion();
        
        
        try {
            // Generar la conexión a la base de datos
            Connection conexion = ci.GenerarConexion();

            // Verificar si la conexión no es null
            if (conexion != null) {
            	
            	UsuarioLista = cc.consultaUsuario(conexion);
            	
                System.out.println("Conexion");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }

        } catch (Exception e) {
            // Manejar la excepción y mostrar el error
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
