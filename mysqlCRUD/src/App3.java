import java.sql.*;
import java.util.Scanner;

import alumnos.Alumno;
import alumnos.AlumnosService;
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
            Scanner sc = new Scanner(System.in);
            int eleccion = 0;

            do{

                muestraMenuAlumnos();
                eleccion = Integer.parseInt(sc.nextLine());

                switch(eleccion){
                    case 1:
                        for(Alumno al : service.requestAll()){
                            System.out.println(al);
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
                        String nombre = sc.next();
                        System.out.println("Apellidos:");
                        String apellidos = sc.next();
                        System.out.println("Id de la clase(si no tiene clase asignada ponga 0):");
                        int claseId = Integer.parseInt(sc.nextLine());
                        service.create(nombre, apellidos,claseId);
                    break;

                    case 4:

                    break;

                    case 5:
                        System.out.println("Diga el ID que quiera borrar");
                        int iden = Integer.parseInt(sc.nextLine());
                        service.delete(iden);
                    break;
                }

            }while(eleccion!=6);        
            sc.close();
            //TODO: Incluye llamadas para probar el servicio
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.closeAll();
        }
    }

    public static void muestraMenuAlumnos(){
        System.out.println("1. Mostrar datos\n2. Mostrar datos por ID\n3. Añadir registro\n4. Actualizar registro\n5. Eliminar registros por ID\n6. Salir");
    }
}



