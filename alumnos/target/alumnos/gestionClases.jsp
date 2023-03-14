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
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    <title>Gestion de Clases</title>
</head>
<body>

    <h1>Gestion de Clases</h1>

    <%
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        try{
            ClasesService serviceClase = new ClasesService(pool.getConnection());%>

            <div id="tabla">
                <table>
                <tr> <td>ID</td> <td>Clase</td> <td>Profesor</td> </tr>    
                <%for(Clase cla : serviceClase.requestAll()){
                out.print(cla.toString());
                }%>

                </table>
            </div>

        <%}catch(SQLException e){
            pool.closeAll();
        }

    %>

    <button onclick="window.location.href='index.jsp'">Inicio</button>

</body>
</html>