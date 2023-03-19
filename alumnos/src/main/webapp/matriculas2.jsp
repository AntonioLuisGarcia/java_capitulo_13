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
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    <title>Matriculas</title>
</head>
<body>
    <h1 class="titulo">Matriculas</h1>
    <div class="anadir">
    <%
        
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";
        
        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        try{

            AlumnosService service = new AlumnosService(pool.getConnection());
            
            int id = Integer.parseInt(request.getParameter("id"));
            int idClase = Integer.parseInt(request.getParameter("idClase"));

            service.updateIdClass(id,idClase);
            out.print("Cambiado con exito");

        }catch(SQLException e){
            out.print("Cambio fallido");
            pool.closeAll();
        }

    %>
    <button onclick="window.location.href='gestionMatriculas.jsp'">Volver</button>
</div>
</body>
</html>