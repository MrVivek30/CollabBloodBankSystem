<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wrong Signup</title>
</head>
<body>
<form action="/bloodbank/auth/signup" method="get">
    <h1 align="center">${message}</h1>
    <button type="submit">Go To Signup</button>
</form>
<br>
<form action="/bloodbank/auth/home" method="get">
    <button type="submit">Home</button>
</form>
<br>
</body>
</html>