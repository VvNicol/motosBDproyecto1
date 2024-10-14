package servicios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import controladores.Inicio;
import dto.UsuarioDto;
import util.ADto;

/**
 * Metodos detallados de consulta interfaz
 * @author nrojlla
 */
public class ConsultaPostgresqlImplementacion implements ConsultaInterfaz {

    @Override
    public List<UsuarioDto> consultaUsuario(Connection conexion) {
        
        Statement declaracionSQL = null;
        ResultSet resultadoConsulta = null;
        ADto adto = new ADto();
        
        try {
            // Se abre una declaración
            declaracionSQL = conexion.createStatement();
            // Se define la consulta de la declaración y se ejecuta
            resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"dlk_motos\".usuario");
            
            // Llamada a la conversión a UsuarioDto
            Inicio.UsuarioLista = adto.resultsAUsuarioDto(resultadoConsulta);  // Se asigna a la lista estática
            
            int i = Inicio.UsuarioLista.size();
            System.out.println("[INFORMACIÓN-ConsultaPostgresqlImplementacion-consultaUsuario] Número de usuarios: " + i);
            
            // Cierre de conexión, declaración y resultado
            resultadoConsulta.close();
            declaracionSQL.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.err.println("[ERROR-ConsultaPostgresqlImplementacion-consultaUsuario] Error generando o ejecutando la declaración SQL: " + e.getMessage());
        }
        
        return Inicio.UsuarioLista;  // Retorna la lista estática
    }
}
