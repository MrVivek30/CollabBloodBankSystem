<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DashBoard</title>
</head>
<body>
<c:set var="user32" value="${sessionScope.user}" />
<c:choose>
    <c:when test="${user32 == null}">
        <p>Please log in to access this portal.</p>
    </c:when>
    <c:when test="${user32.role != 'USER'}">
        <p>You must be a USER to access this portal.</p>
    </c:when>
    <c:otherwise>
        <h1>Welcome ${user32.name} to the DashBoard</h1>
        <div style="display: flex; flex-direction: row; align-items: center;">
            <form action="/bloodbank/user/profile" method="get">
                <button type="submit">Profile</button>
            </form>
            <form action="/bloodbank/user/history" method="get">
                <button type="submit">History</button>
            </form>
            <form action="/bloodbank/user/request" method="get">
                <input type="hidden" name="type" value="recieve">
                <button type="submit">Request</button>
            </form>
            <form action="/bloodbank/user/request" method="get">
                <input type="hidden" name="type" value="donate">
                <button type="submit">Donate</button>
            </form>
            <form action="/bloodbank/user/logout" method="get">
                <button type="submit">Logout</button>
            </form>
            <form action="/bloodbank/user/deleteAccount" method="get">
                <button type="submit">Delete Account</button>
            </form>
            <form action="/bloodbank/auth/home" method="get">
                <button type="submit">Home</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>