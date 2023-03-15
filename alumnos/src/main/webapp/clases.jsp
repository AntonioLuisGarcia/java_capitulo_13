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
    <title>Document</title>
</head>
<body>
    <%
        int id = 0;
        int eleccion = Integer.parseInt(request.getParameter("consulta"));

        if(eleccion == 1){

            String nombreClase = request.getParameter("nombreClase");
            String profesorClase = request.getParameter("profesorClase");

            String url = "jdbc:mysql://localhost:3306/alumnos";
            String usuario = "antonio";
            String clave = "Lupiman77!";
            
            ConnectionPool pool = new ConnectionPool(url, usuario, clave);

            try{

                ClasesService serviceClase = new ClasesService(pool.getConnection());
                serviceClase.create(nombreClase,profesorClase);
                out.print("Clase creada");

            }catch(SQLException e){
                out.print("No se ha podido crear");
                e.printStackTrace();
                pool.closeAll();
            }%>
        <button onclick="window.location.href='gestionClases.jsp'">Volver</button>
        <%}else if(eleccion == 2){ 
            id = Integer.parseInt(request.getParameter("id"));%>
            
    <form method="post" action="clases2.jsp">
        <input type="hidden" value="<%=id%>" name="id">
        <input type="hidden" value="0" name="cambio">
        Nombre de la clase nueva:
        <input type="text" name="nombreClase">
        <input class="boton" type="submit" value="Cambiar">
    </form>
    
    <%}else if(eleccion==3){
        id = Integer.parseInt(request.getParameter("id"));%>
        <form action="clases2.jsp">
            <label for="cars">¿Estas seguro de que quieres borrarlo?</label>
            <input type="hidden" value="1" name="cambio">
            <select name="borrar">
                <option value="0">SI</option>
                <option value="1">NO</option>
            </select>
            <input type="hidden" value="<%=id%>" name="id">
            <br><br>
            <input type="submit" value="Submit">
          </form>
          
    <%}%>
</body>
</html>