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

    public long create(String claseNombre, String claseProfesor)throws SQLException{
        long claseId = 0;
        Statement statement = connect.createStatement();
        String sql = String.format("INSERT INTO clase (ClaseNombre, ClaseProfesor) VALUES ('%s', '%s')", claseNombre, claseProfesor);
        //statement.execute(sql);
        int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                claseId = generatedKeys.getLong(1);
                statement.close();
                return claseId;
            }
            else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public int update(int claseId, String claseNombre)throws SQLException{
        Statement statement = connect.createStatement();
        String sql = String.format("UPDATE clase SET ClaseNombre = '%s' WHERE ClaseId=%d", claseNombre, claseId);
        int affectedRows = statement.executeUpdate(sql);
        statement.close();

        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean delete(long claseId) throws SQLException{
        Statement statement = this.connect.createStatement();    
        String sql = String.format("DELETE FROM clase WHERE ClaseId=%d", claseId);
        // Ejecución de la consulta+
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }

    public int updateTeacher(int claseId, String profesor)throws SQLException{
        Statement statement = connect.createStatement();
        String sql = String.format("UPDATE clase SET ClaseProfesor = '%s' WHERE ClaseId=%d", profesor, claseId);
        int affectedRows = statement.executeUpdate(sql);
        statement.close();

        if(affectedRows == 0){
            throw new SQLException("Creating user failed, no rows affected.");
        }else{
            return affectedRows;
        }
    }
}
