<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menú Principal</title>
</head>
<body>
    <h1>Gestión de Contactos</h1>
    
    <a href="<c:url value='/contactos?action=consultar'/>">Consultar Contactos</a>
    <br>
    
    <a href="<c:url value='/contactos?action=nuevo'/>">Nuevo Contacto</a>
</body>
</html>