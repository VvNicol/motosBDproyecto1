package controladores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDto;
import servicios.ConexionInterfaz;
import servicios.ConexionPostgresqlImplementacion;
import servicios.ConsultaInterfaz;
import servicios.ConsultaPostgresqlImplementacion;
import servicios.FicheroImplementacion;
import servicios.FicheroInterfaz;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;

public class Inicio {

	public static List<UsuarioDto> UsuarioLista = new ArrayList<UsuarioDto>();
	
    public static void main(String[] args) {
    	
    	
    	
        ConexionInterfaz ci = new ConexionPostgresqlImplementacion();
        ConsultaInterfaz cc = new ConsultaPostgresqlImplementacion();
        MenuInterfaz mi = new MenuImplementacion();
        FicheroInterfaz fi = new FicheroImplementacion();
        
        byte opcion;
        boolean esCerrado = false;
        
        
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
            
            do {
				
            	opcion = mi.MenuPrincipal();
            	
            	switch (opcion) {
				case 0:
					esCerrado = true;
					break;
				case 1:
					
					break;
				case 2:
					break;
				case 3: 
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;

				default:
					System.out.println("la opcion " + opcion + "no existe");
					break;
				}
            	
			} while (!esCerrado);

        } catch (Exception e) {
            // Manejar la excepción y mostrar el error
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
