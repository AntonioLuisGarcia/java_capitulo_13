import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";
        
        // Declaración de variables
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String sql = "";

        try {
            // Conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, clave);
            sentencia = conexion.createStatement();/////////////////////////
            do{
                muestraMenu();
                opcion = sc.nextInt();
                switch(opcion){
                    case 1:
                        sql = "SELECT id, nombre, apellidos FROM alumnos";
                        resultado = sentencia.executeQuery(sql);
                        muestraTodosDatos(resultado);
                    break;

                    case 2:
                        System.out.println("Diga por que ID quiere borrar:");
                        int ident = sc.nextInt();
                        sql = "DELETE FROM alumnos WHERE id = " + ident;
                        sentencia.execute(sql);
                    break;

                    case 3:
                        System.out.println("Nombre:");
                        String nombre = "'";
                        nombre += sc.next();
                        nombre += "'";
                        System.out.println("Apellidos:");
                        String apellidos = "'";
                        apellidos += sc.next();
                        apellidos += "'";
                        sql = "INSERT INTO alumnos VALUES (" + nuevoId(sentencia.executeQuery("SELECT id, nombre, apellidos FROM alumnos")) + ", " + nombre + " ," + apellidos + ")";
                        sentencia.execute(sql);
                    break;

                    case 4:
                        System.out.println("Que id de registro quieres cambiar:");
                        int iden = sc.nextInt();
                        System.out.println("Nombre:");
                        String nombre2 = "'";
                        nombre2 += sc.next();
                        nombre2 += "'";
                        System.out.println("Apellidos:");
                        String apellidos2 = "'";
                        apellidos2 += sc.next();
                        apellidos2 += "'";
                        sql = "UPDATE alumnos SET nombre = " + nombre2 + ", apellidos = " + apellidos2 + " WHERE id = " + iden;
                        sentencia.execute(sql);
                    break;
                }

            }while(opcion != 5);
                
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
            try {
                // Cierre de la conexión
                if (conexion != null) conexion.close();
                if (sentencia != null) sentencia.close();
                if (resultado != null) resultado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void muestraMenu(){
        System.out.println("1. Mostrar datos\n2. Eliminar registro por ID\n3. Añadir registro\n4. Actualizar registro\n5. Salir");
    }

    public static void muestraTodosDatos(ResultSet resultado){

        try{
            while(resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
            
    }

    public static int UltimoId(ResultSet resultado){

        int id = 0;
        try{
            while(resultado.next()) {
                id = resultado.getInt("id");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }        
        return id;
    }

    public static int nuevoId(ResultSet resultado){
        return UltimoId(resultado)+1;
    }
}

