<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="alumnos.AlumnosService" %>
<%@ page import="alumnos.Alumno" %>
<%@ page import="connection.ConnectionPool" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/style2.css" type="text/css">
    <title>Gestion</title>
</head>
<body>
    <h1 class="titulo">Gestion de Alumnos</h1>
    
    <div class="mensaje">
        <%

            int cambio = Integer.parseInt(request.getParameter("cambio"));
            
            String url = "jdbc:mysql://localhost:3306/alumnos";
            String usuario = "antonio";
            String clave = "Lupiman77!";
            
            ConnectionPool pool = new ConnectionPool(url, usuario, clave);

            try{

                AlumnosService service = new AlumnosService(pool.getConnection());
                
                int id = Integer.parseInt(request.getParameter("id"));
                
                if(cambio == 0){
                    String nombre = request.getParameter("nombre");
                    String apellidos = request.getParameter("apellidos");
                    int rows = service.update(id,nombre,apellidos);
                    out.print("Filas cambiadas: " + rows);

                }else if(cambio == 1){
                    int borrar = Integer.parseInt(request.getParameter("borrar"));
                    
                    if(borrar == 0){
                        out.print("Borrado completado");     
                        service.delete((long)id);     
                    }else if(borrar == 1){
                        out.print("No se han hecho cambios");
                    }
                }

            }catch(SQLException e){
                out.print("Borrado fallado");
                pool.closeAll();
            }

        %>

        <p><button onclick="window.location.href='gestionAlumnos.jsp'">Volver</button></p>
    </div>

</body>
</html>