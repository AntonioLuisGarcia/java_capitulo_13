<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="alumnos.AlumnosService" %>
<%@ page import="alumnos.Alumno" %>
<%@ page import="connection.ConnectionPool" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    <title>Filtrar</title>
</head>
<body>
    <h1 class="titulo">Gestion de Alumnos</h1>
    <%
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String usuario = "antonio";
        String clave = "Lupiman77!";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);

        String filtro = request.getParameter("filtro");
        try{
            AlumnosService service = new AlumnosService(pool.getConnection());
            ArrayList<Alumno> resultado = service.filter(filtro);
                if(resultado.size() == 0){
                    out.print("Sin resultados");
                }else{%>

            <div id="tabla">
                <table>
                <tr class="primeraTr"> <td>ID</td> <td>Nombre</td> <td>Apellidos</td> <td>Clase</td></tr>    
                <%for(Alumno al : resultado){
                    out.print(al.toString2(pool.getConnection()));%>
                    </tr>
                <%}%>
            </table>
        </div>
        <%}
    }catch(SQLException e){
            pool.closeAll();
        }
    %>
    <button onclick="window.location.href='gestionAlumnos.jsp'">Volver</button>

</body>
</html>