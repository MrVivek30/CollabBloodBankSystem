<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page import="org.springproject.bloodbank.dto.UserDTO" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DashBoard</title>

</head>
<body>
<%
UserDTO dto=new UserDTO();
%>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <c:set var="user32" value="${sessionScope.user}" scope="request"/>
    </c:when>
    <c:otherwise>
        <c:set var="user32" value="${dto}" scope="request"/>
    </c:otherwise>
</c:choose>

<h1>Welcome ${user32.name} to the DashBoard</h1>
<div style="display: flex; flex-direction: column; align-items: center;">
    <c:set var="role" value="${user32.role.toLowerCase()}"/>
    <form action="/bloodbank/agent/profile" method="get">
        <button type="submit">Profile</button>
    </form>
    <form action="/bloodbank/agent/history" method="get">
        <button type="submit">History</button>
    </form>
    <form action="/bloodbank/agent/request" method="get">
        <input type="hidden" name="type" value="recieve">
        <button type="submit">Request</button>
    </form>
    <form action="/bloodbank/agent/request" method="get">
        <input type="hidden" name="type" value="donate">
        <button type="submit">Donate</button>
    </form>
    <form action="/bloodbank/agent/logout" method="get">
        <button type="submit">Logout</button>
    </form>
    <form action="/bloodbank/agent/deleteAccount" method="get">
        <button type="submit">Delete Account</button>
    </form>
    <form action="/bloodbank/auth/home" method="get">
        <button type="submit">Home</button>
    </form>
</div>
</body>
</html>