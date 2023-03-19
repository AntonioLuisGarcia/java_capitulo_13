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
    <title>Matriculas</title>
</head>
<body>
    <h1 class="titulo">Matriculas</h1>
    
    <%
    String url = "jdbc:mysql://localhost:3306/alumnos";
    String usuario = "antonio";
    String clave = "Lupiman77!";
    
    ConnectionPool pool = new ConnectionPool(url, usuario, clave);
    int id = Integer.parseInt(request.getParameter("id"));
    
    try{
            ClasesService serviceClase = new ClasesService(pool.getConnection());
            AlumnosService service = new AlumnosService(pool.getConnection());
            int consulta = Integer.parseInt(request.getParameter("consulta"));
            if(consulta == 1){
            %>

            <div id="tabla">
                <table>
                    <tr class="primeraTr"> <td>ID</td> <td>Clase</td> <td>Profesor</td></tr>    
                    <%for(Clase cla : serviceClase.requestAll()){
                        out.print(cla.toString());%>
                    </tr>
                <%}%>
                
            </table>
        </div>
        
        <div class="anadir">
        <form method="post" action="matriculas2.jsp">
            <p>Seleccione el id de alguno de los de arriba</p>
            <input type="hidden" value="<%=id%>" name="id">
            Id:
            <input type="text" name="idClase">
            <br>
            <input class="boton" type="submit" value="Cambiar">
        </form>
        
        <%}else if(consulta == 2){
            service.baja(id);%>
            <div class="anadir">
                <p>Alumno dado de baja</p>
                <button onclick="window.location.href='gestionMatriculas.jsp'">Volver</button>
        <%}
    }catch(SQLException e){
        pool.closeAll();
    }%>
</div>

</body>
</html>