<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Simple Navigation</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Josefin+Slab:wght@100&display=swap" rel="stylesheet">
</head>
<body>
<div class="center" >
    <h1 style="font-family: 'Josefin Slab', serif;"><u>Welcome to Blood Bank</u> </h1>
</div>
<form action="/bloodbank/auth/login" method="get">
    <button type="submit">login</button>
</form>
<form action="/bloodbank/auth/signup" method="get">
    <button type="submit">signup</button>
</form>
</body>
</html>
