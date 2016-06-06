<%-- 
    Document   : addUserView
    Created on : 22-abr-2016, 17:11:03
    Author     : Angel A. Díaz Piña
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escribe tu nombre y educación:</title>
    </head>
    <body>
        <h1>Escribe tu nombre:</h1>
        <spring:nestedPath path="user" >
            <form action="" method="POST">
                Nombre: 
                <spring:bind path="name">
                    <input type="text" name="${status.expression}" value="${status.value}" >                    
                </spring:bind>
               
                Education: 
                <spring:bind path="education">
                    <input type="text" name="${status.expression}" value="${status.value}" >                    
                </spring:bind>    
                    <input type="submit" value="OK">
            </form>     
        </spring:nestedPath>   
        
        <div></div> 
    </body>
</html>
