package clases;

public class Clase {
    
    long id;
    String nombre;
    String profesor;

    public Clase(){
        this.id = 0;
        this.nombre = "";
        this.profesor = "";
    }

    public Clase(int id,String nombre,String profesor){
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString(){
        return String.format("<tr> <td>" + this.id + "</td><td>" + this.nombre + "</td><td>" + this.profesor + "</td>");//el </td> lo hago cuando imprimo el toString junto a los botones
    }
}
