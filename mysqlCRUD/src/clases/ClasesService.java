package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClasesService {
    
    Connection connect;

    public ClasesService(Connection connect){
        this.connect = connect;
    }

    public ArrayList<Clase> requestAll()throws SQLException{
        ArrayList<Clase> listaClases= new ArrayList<Clase>();
        Statement statement = this.connect.createStatement();
        String sql = "SELECT ClaseId, ClaseNombre, ClaseProfesor FROM clase";
        ResultSet resultados = statement.executeQuery(sql);

        while(resultados.next()){
            int claseId = resultados.getInt("ClaseId");
            String claseNombre = resultados.getString("ClaseNombre"); 
            String claseProfesor = resultados.getString("ClaseProfesor");
            listaClases.add(new Clase(claseId,claseNombre,claseProfesor)); 
        }

        statement.close();
        return listaClases;
    }

    public Clase requestById(int id)throws SQLException{
        Clase clase = new Clase();
        Statement statement = this.connect.createStatement();
        String sql = "SELECT ClaseId, ClaseNombre, ClaseProfesor FROM clase WHERE ClaseId = " + id;
        ResultSet resultados = statement.executeQuery(sql);
        clase.setId(id);
        clase.setNombre(resultados.getString("ClaseNombre"));
        clase.setProfesor(resultados.getString("ClaseProfesor"));
        statement.close();
        return clase;
    }
}
