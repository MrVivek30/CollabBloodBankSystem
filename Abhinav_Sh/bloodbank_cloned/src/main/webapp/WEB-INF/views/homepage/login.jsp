<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>User Portal</title>
</head>
<body>
<div align="center" >
<form method="post" action="/bloodbank/auth/authenticate">
    <div>
        <div>Login form</div>
        <br>
        <hr/>
        <table align="center" border="0">
            <tr>
                <td>
                    <input id="username" name="username" value="${userName}" placeholder="Your User Name" type="text"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="password" name="password" value="${userPass}" placeholder="Your Password" type="password"/>
                </td>
            </tr>
        </table>
        <br>
        <br>
        <button type="submit">Login</button>
        <br>
        <h2>New User Register</h2>
        <br>
    </div>
</form>
</div>
<div align="center">
    <div align="center">
        <form action="/bloodbank/auth/signup" method="get">
            <button type="submit">SignUp</button>
        </form>
    </div>
    <br>

    <div  align="center">
        <form action="/bloodbank/auth/home" method="get">
            <button type="submit">Home</button>
        </form>
    </div>
    <div  align="center">
        <form action="/bloodbank/auth/forgetPass" method="get">
            <button type="submit">Password Reset</button>
        </form>
    </div>
    <p style="color: red;">${error}</p>
</div>
</body>
</html>