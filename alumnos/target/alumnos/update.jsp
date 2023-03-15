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
    <title>Update</title>
</head>
<body>
    <%
        String nombreClase = request.getParameter("nombreClase");
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        int id = Integer.parseInt(request.getParameter("id"));

        try{
            ClasesService serviceClase = new ClasesService(pool.getConnection());

            int rows = serviceClase.update(id,nombreClase);
            out.print("Filas cambiadas" + rows + "<br>");

        }catch(SQLException e){
            pool.closeAll();
        }
    %>

    <button onclick="window.location.href='gestionClases.jsp'">Gestion Clases</button>

</body>
</html>