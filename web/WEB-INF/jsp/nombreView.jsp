<%-- 
    Document   : nombreView
    Created on : 21-abr-2016, 18:20:43
    Author     : Angel A. Díaz Piña
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escribe tu nombre:</title>
    </head>
    <body>
        <h1>Escribe tu nombre:</h1>
        <spring:nestedPath path="nombre" >
            <form action="" method="POST">
                Nombre: 
                <spring:bind path="valor">
                    <input type="text" name="${status.expression}" value="${status.value}" >                    
                </spring:bind>
                    <input type="submit" value="OK">
            </form>     
        </spring:nestedPath>      
    </body>
</html>
