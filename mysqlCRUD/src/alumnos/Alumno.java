package alumnos;
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

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Apellidos: %s", this.id, this.nombre, this.apellidos);
    }
}
