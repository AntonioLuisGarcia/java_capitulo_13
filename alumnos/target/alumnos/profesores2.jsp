<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="clases.ClasesService" %>
<%@ page import="clases.Clase" %>
<%@ page import="connection.ConnectionPool" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profesores</title>
</head>
<body>
    
    <%
        
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";
        
        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        try{

            ClasesService serviceClase = new ClasesService(pool.getConnection());
            
            int id = Integer.parseInt(request.getParameter("id"));
            String profesor = request.getParameter("profesor");

            serviceClase.updateTeacher(id,profesor);
            out.print("Cambiado con exito");

        }catch(SQLException e){
            out.print("Cambio fallido");
        }finally{
            pool.closeAll();
        }
    %>
    <button onclick="window.location.href='gestionProfesores.jsp'">Volver</button>
</body>
</html>