<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="alumnos.AlumnosService" %>
<%@ page import="alumnos.Alumno" %>
<%@ page import="clases.ClasesService" %>
<%@ page import="clases.Clase" %>
<%@ page import="connection.ConnectionPool" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Alumnos</title>
</head>
<body>
    <h1>Gestion de Alumnos</h1>
    <%
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        try{
            AlumnosService service = new AlumnosService(pool.getConnection());

            for(Alumno al : service.requestAll()){%>
                <p><%=al.toString2(pool.getConnection())%></p>
            <%}
        }catch(SQLException e){
            pool.closeAll();
        }
    %>
    <button onclick="window.location.href='index.jsp'">Inicio</button>
</body>
</html>