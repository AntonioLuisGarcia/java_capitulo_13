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
            AlumnosService service = new AlumnosService(pool.getConnection());%>

            <div id="tabla">
                <table>
                <tr> <td>ID</td> <td>Nombre</td> <td>Apellidos</td> <td>Clase</td> <td>Cambiar</td> <td>Borrar</td> </tr>    
                <%for(Alumno al : service.requestAll()){
                    out.print(al.toString2(pool.getConnection()));%>
                    <td>
                        <form method="post" action="alumnos.jsp">
                            <input type="hidden" value="<%=al.getId()%>" name="id">
                            <input type="hidden" value="2" name="consulta">
                            <input class="boton" type="submit" value="Cambiar">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="alumnos.jsp">
                            <input type="hidden" value="<%=al.getId()%>" name="id">
                            <input type="hidden" value="3" name="consulta">
                            <input class="boton" type="submit" value="Borrar">
                        </form>
                    </td>
                    </tr>
                <%}%>
            </table>
        </div>
        <%}catch(SQLException e){
            pool.closeAll();
        }
    %>

    <form method="post" action="alumnos.jsp">
        <p>Nuevo Alumno</p>
        <input type="hidden" value="1" name="consulta">
        Nombre:
        <input type="text" name="nombre">
        Profesor:
        <input type="text" name="apellidos">
        <br>
        <input class="boton" type="submit" value="Crear">
    </form>
    <br><br>
    <button onclick="window.location.href='index.jsp'">Inicio</button>
</body>
</html>