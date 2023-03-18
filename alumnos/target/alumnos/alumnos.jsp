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
    <title>Alumnos</title>
</head>
<body>
    <h1 class="titulo">Gestion de Alumnos</h1>
    <div class="mensaje">
        <%
            int eleccion = Integer.parseInt(request.getParameter("consulta"));
            int id = 0;
            String url = "jdbc:mysql://localhost:3306/alumnos";
            String usuario = "antonio";
            String clave = "Lupiman77!";

            ConnectionPool pool = new ConnectionPool(url, usuario, clave);

            if(eleccion == 1){
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");

                try{
                    AlumnosService service = new AlumnosService(pool.getConnection());
                    service.create(nombre,apellidos);
                    out.print("Alumno añadido");

                }catch(SQLException e){
                    out.print("No se ha podido crear");
                    e.printStackTrace();
                    pool.closeAll();
                }%>
                <br>
            <p><button onclick="window.location.href='gestionAlumnos.jsp'">Volver</button></p>
            <%}else if(eleccion == 2){
                id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");%>
                <form method="post" action="alumnos2.jsp">
                    <input type="hidden" value="<%=id%>" name="id">
                    <input type="hidden" value="0" name="cambio">
                    Nombre:
                    <input type="text" name="nombre" value="<%=nombre%>">
                    Apellidos:
                    <input type="text" name="apellidos" value="<%=apellidos%>">
                    <br><br>
                    <input class="boton" type="submit" value="Cambiar">
                </form>
            <%}else if(eleccion == 3){
                id = Integer.parseInt(request.getParameter("id"));%>
                <form action="alumnos2.jsp">
                    <label for="cars">¿Estas seguro de que quieres borrarlo?</label>
                    <input type="hidden" value="1" name="cambio">
                    <select name="borrar">
                        <option value="0">Si</option>
                        <option value="1">No</option>
                    </select>
                    <input type="hidden" value="<%=id%>" name="id">
                    <br><br>
                    <input type="submit" value="Seleccionar">
                </form>
            <%}%>
        </div>
</body>
</html>