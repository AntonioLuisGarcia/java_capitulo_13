import java.sql.*;
import java.util.Scanner;

import alumnos.Alumno;
import alumnos.AlumnosService;
import clases.Clase;
import clases.ClasesService;
import connection.ConnectionPool;
public class App3 {
    public static void main(String[] args) throws Exception {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);
        
        try {
            // Conexión a la base de datos
            AlumnosService service = new AlumnosService(pool.getConnection());
            ClasesService serviceClase = new ClasesService(pool.getConnection());
            Scanner sc = new Scanner(System.in);
            int eleccion = 0;
            int eleccionAlumnos = 0;
            int eleccionClase = 0;
            int eleccionMatricula = 0;

            do{
                System.out.println("1. Gestion Alumnos\n2. Gestion Grupos\n3. Gestion matriculas\n4. Salir");
                eleccion = Integer.parseInt(sc.nextLine());

                switch(eleccion){
                    case 1:
                        do{
                            muestraMenuAlumnos();
                            eleccionAlumnos = Integer.parseInt(sc.nextLine());

                            switch(eleccionAlumnos){

                                
                                case 1:
                                    for(Alumno al : service.requestAll()){
                                        System.out.println(al.toString2(pool.getConnection()));
                                    }
                                break;

                                case 2:
                                    System.out.println("Diga el ID");
                                    int ident = Integer.parseInt(sc.nextLine());
                                    for(Alumno al : service.requestAll()){
                                        if(al.getId()==ident){
                                            System.out.println(al);
                                        }
                                    }
                                break;

                                case 3:
                                    System.out.println("Nombre:");
                                    String nombre = sc.nextLine();
                                    System.out.println("Apellidos:");
                                    String apellidos = sc.nextLine();
                                    System.out.println("Id de la clase(si no tiene clase asignada ponga 0):");
                                    int claseId = Integer.parseInt(sc.nextLine());
                                    service.create(nombre, apellidos,claseId);
                                break;

                                case 4:
                                    System.out.println("Diga el ID que quiera actualizar");
                                    int identi = Integer.parseInt(sc.nextLine());
                                    System.out.println("Nombre:");
                                    String nombre1 = sc.nextLine();
                                    System.out.println("Apellidos:");
                                    String apellidos1 = sc.nextLine();
                                    service.update(identi, nombre1, apellidos1);
                                break;

                                case 5:
                                    System.out.println("Diga el ID que quiera borrar");
                                    int iden = Integer.parseInt(sc.nextLine());
                                    service.delete(iden);
                                break;
                            }
                        }while(eleccionAlumnos!=6);
                    break;

                    case 2:
                        
                        do{
                            muestraMenuClases();
                            eleccionClase = Integer.parseInt(sc.nextLine());

                            switch(eleccionClase){
                                case 1:
                                    for(Clase cla : serviceClase.requestAll()){
                                        System.out.println(cla.toString());
                                    }
                                break;

                                case 2:
                                    System.out.println("Diga el ID");
                                    int ident = Integer.parseInt(sc.nextLine());
                                    for(Clase cla : serviceClase.requestAll()){
                                        if(cla.getId()==ident){
                                            System.out.println(cla);
                                        }
                                    }
                                break;

                                case 3:
                                    System.out.println("Nombre de la Clase:");
                                    String nombre = sc.nextLine();
                                    System.out.println("Tutor:");
                                    String tutor = sc.nextLine();
                                    serviceClase.create(nombre, tutor);
                                break;

                                case 4:
                                    System.out.println("Diga el ID de la clase que quiera actualizar");
                                    int identi = Integer.parseInt(sc.nextLine());
                                    System.out.println("Nombre de la clase:");
                                    String nombre1 = sc.nextLine();
                                    serviceClase.update(identi, nombre1);
                                break;

                                case 5:
                                    System.out.println("Diga el ID de la clase que quiera borrar");
                                    int iden = Integer.parseInt(sc.nextLine());
                                    serviceClase.delete(iden);
                                break;
                            }
                        }while(eleccionClase!=6);
                    break;

                    case 3:

                        do{
                            System.out.println("1. Matricula Alumnos\n2. Matricula Profesores\n3. Salir");
                            eleccionMatricula = Integer.parseInt(sc.nextLine());

                            switch(eleccionMatricula){
                                case 1:
                                    System.out.println("Diga el id del alumno que quiere cambiar:");
                                    int idAlumno = Integer.parseInt(sc.nextLine());
                                    System.out.println("Diga el id de la clase nueva:");
                                    int idClaseNuevo = Integer.parseInt(sc.nextLine());
                                    service.updateIdClass(idAlumno, idClaseNuevo);
                                break;

                                case 2:
                                    System.out.println("Diga el id de la clase que quiere cambiar:");
                                    int idClase = Integer.parseInt(sc.nextLine());
                                    System.out.println("Diga el nombre del profesor/a nuevo:");
                                    String profesorNuevo = sc.nextLine();
                                    serviceClase.updateTeacher(idClase, profesorNuevo);
                                break;
                            }
                        }while(eleccionMatricula !=3);

                    break;
                }
                    
            }while(eleccion!=4);        
            sc.close();
            System.out.println("Saliendo de la gestion");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.closeAll();
        }
    }

    public static void muestraMenuAlumnos(){
        System.out.println("1. Mostrar datos\n2. Mostrar datos por ID\n3. Añadir registro\n4. Actualizar registro\n5. Eliminar registros por ID\n6. Salir");
    }

    public static void muestraMenuClases(){
        System.out.println("1. Mostrar datos\n2. Mostrar datos por ID\n3. Añadir clase\n4. Actualizar clase\n5. Eliminar clase por ID\n6. Salir");
    }
}