package alumnos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Alumno {
    long id;
    String nombre;
    String apellidos;
    long claseId;
 
    public Alumno(){
        this(0,"","",0);
    }

    public Alumno(int id, String nombre, String apellidos,long claseId){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.claseId = claseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getClaseId() {
        return claseId;
    }

    public void setClaseId(long claseId) {
        this.claseId = claseId;
    }

    public String toString2(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();  
        String sql = "SELECT ClaseNombre FROM clase WHERE ClaseId = " + this.claseId;
        ResultSet querySet = statement.executeQuery(sql);

        String nombreClase = "";
        if (querySet.next()) {
            nombreClase = querySet.getString("ClaseNombre");
        }

        if(nombreClase.equals("")){
            nombreClase = "Sin clase";
        }

        return String.format("ID: %d, Nombre: %s, Apellidos: %s, Clase: %s", this.id, this.nombre, this.apellidos,nombreClase);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Apellidos: %s, ClaseId: %d", this.id, this.nombre, this.apellidos,this.claseId);
    }
}
