<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    <title>Profesores</title>
</head>
<body>
    <h1 class="titulo">Profesores</h1>

    <%
       int id = Integer.parseInt(request.getParameter("id"));
    %>
    <div class="anadir">
        <form method="post" action="profesores2.jsp">
            <p>Profesor/a nuevo</p>
            <input type="hidden" value="<%=id%>" name="id">
            Nombre
            <input type="text" name="profesor">
            <br>
            <input class="boton" type="submit" value="Cambiar">
        </form>
    </div>
        
    <button onclick="window.location.href='gestionProfesores.jsp'">Volver</button>
</body>
</html>