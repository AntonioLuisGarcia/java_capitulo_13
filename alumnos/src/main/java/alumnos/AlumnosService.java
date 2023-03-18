package alumnos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnosService {
    Connection conn;
    public AlumnosService(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Alumno> requestAll() throws SQLException{
        Statement statement = null;
        ArrayList<Alumno> result = new ArrayList<Alumno>();
        statement = this.conn.createStatement();   
        String sql = "SELECT id, nombre, apellidos, ClaseId FROM alumnos";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            int claseId = querySet.getInt("ClaseId");
            result.add(new Alumno(id, nombre, apellidos,claseId));
        } 
        statement.close();    
        return result;
    }

    public Alumno requestById(int id) throws SQLException{
        Statement statement = null;
        Alumno result = null;
        statement = this.conn.createStatement();    
        String sql = String.format("SELECT id, nombre, apellidos, ClaseId FROM alumnos WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            int claseId = querySet.getInt("ClaseId");
            result = new Alumno(id, nombre, apellidos,claseId);
        }
        statement.close();    
        return result;
    }

    public long create(String nombre, String apellidos) throws SQLException{
        Statement statement = null;
        long id = 0;
        String sql = "";
        statement = this.conn.createStatement();  
        sql = String.format("INSERT INTO alumnos (nombre, apellidos) VALUES ('%s', '%s')", nombre, apellidos); 
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
                statement.close();
                return id;
            }
            else {
                statement.close();
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public int update(int id, String nombre, String apellidos) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("UPDATE alumnos SET nombre = '%s', apellidos = '%s' WHERE id=%d", nombre, apellidos, id);
        // Ejecución de la consulta
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean delete(long id) throws SQLException{
        Statement statement = null;
        statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM alumnos WHERE id=%d", id);
        // Ejecución de la consulta
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }

    public int updateIdClass(int id, int idClass)throws SQLException{
        Statement statement = this.conn.createStatement(); 
        String sql = String.format("UPDATE alumnos SET ClaseId = '%d' WHERE id=%d", idClass, id);
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if(affectedRows == 0){
            throw new SQLException("Creating user failed, no rows affected.");
        }else{
            return affectedRows;
        }
    }

    public int baja(int id)throws SQLException{
        Statement statement = this.conn.createStatement(); 
        String sql = String.format("UPDATE alumnos SET ClaseId = NULL WHERE id=%d", id);
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if(affectedRows == 0){
            throw new SQLException("Creating user failed, no rows affected.");
        }else{
            return affectedRows;
        }
    }

    public ArrayList<Alumno> filter(String filtro)throws SQLException{
        Statement statement =  this.conn.createStatement();
        ArrayList<Alumno> result = new ArrayList<Alumno>();
        String sql = "SELECT id, nombre, apellidos, ClaseId FROM alumnos WHERE nombre like '%" + filtro + "%' UNION SELECT id, nombre, apellidos, ClaseId FROM alumnos WHERE apellidos like '%" + filtro + "%' ";
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        while(querySet.next()) {
            int id = querySet.getInt("id");
            String nombre = querySet.getString("nombre");
            String apellidos = querySet.getString("apellidos");
            int claseId = querySet.getInt("ClaseId");
            result.add(new Alumno(id, nombre, apellidos,claseId));
        } 
        statement.close();    
        return result;
    }
}
