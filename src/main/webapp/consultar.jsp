<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Consultar Contactos</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilos.css'/>">
</head>
<body>
    <h1>Lista de Contactos</h1>
    <a href="<c:url value='/'/>">Volver al inicio</a>
    
    <table>
        <tr>
            <th>Clave</th>
            <th>Nombre</th>
            <th>Teléfono</th>
            <th>Dirección</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="contacto" items="${listaContactos}">
            <tr>
                <td><c:out value="${contacto.clave}" /></td>
                <td><c:out value="${contacto.nombre}" /></td>
                <td><c:out value="${contacto.telefono}" /></td>
                <td><c:out value="${contacto.direccion}" /></td>
                <td>
                    <a href="<c:url value='/contactos?action=editar&clave=${contacto.clave}'/>">
                        Editar
                    </a>
                    <a href="<c:url value='/contactos?action=eliminar&clave=${contacto.clave}'/>">
                        Eliminar
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>