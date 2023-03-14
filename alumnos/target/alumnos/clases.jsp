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
        int id = Integer.parseInt(request.getParameter("id"));
        out.print(id);
    %>

    <form method="post" action="update.jsp">
        <input type="hidden" value="<%=id%>" name="id">
        Nombre de la clase nueva:
        <input type="text" name="nombreClase">
        <input class="boton" type="submit" value="Cambiar">
    </form>

</body>
</html>