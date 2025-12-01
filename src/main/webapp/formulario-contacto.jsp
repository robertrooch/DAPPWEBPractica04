<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${empty contacto ? 'Nuevo Contacto' : 'Editar Contacto'}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilos.css'/>">
</head>
<body>

    <h1>${empty contacto ? 'Crear Nuevo Contacto' : 'Editar Contacto'}</h1>
    <hr>

    <form method="post" action="<c:url value='/contactos'/>">

        <input type="hidden" name="action" value="${empty contacto ? 'guardar' : 'actualizar'}">

        <div>
            <label>Clave:</label>
            <input type="text" name="clave" value="<c:out value='${contacto.clave}'/>" ${empty contacto ? '' : 'readonly'}>
        </div>
        <br>
        <div>
            <label>Nombre:</label>
            <input type="text" name="nombre" value="<c:out value='${contacto.nombre}'/>" required>
        </div>
        <br>
        <div>
            <label>Teléfono:</label>
            <input type="text" name="telefono" value="<c:out value='${contacto.telefono}'/>" required>
        </div>
        <br>
        <div>
            <label>Dirección:</label>
            <input type="text" name="direccion" value="<c:out value='${contacto.direccion}'/>" required>
        </div>
        <br>
        
        <button type="submit">${empty contacto ? 'Guardar Contacto' : 'Actualizar Cambios'}</button>
    </form>
    
    <br>
    <a href="<c:url value='/contactos'/>">Volver a la lista</a>

</body>
</html>
