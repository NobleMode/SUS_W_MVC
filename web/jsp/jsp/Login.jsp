<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="display: flex">
        <form action="LoginController" method="post" style="margin: auto">
            <h1>Login</h1>
            <p style="color: red;">${requestScope.error}</p>
            
            <td><input type="text" placeholder="Email" name="user" required></td>
            <td><input type="password" placeholder="Password" name="pass" required></td>
            
            <input type="submit" value="submit">
        </form>
    </body>
</html>
