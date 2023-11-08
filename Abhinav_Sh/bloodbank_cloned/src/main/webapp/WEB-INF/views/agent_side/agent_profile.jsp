<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ page import="org.springproject.bloodbank.dto.UserDTO" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <p>Please log in to access this portal.</p>
    </c:when>
    <c:when test="${sessionScope.user.role != 'AGENT'}">
        <p>You must be a AGENT to access this portal.</p>
    </c:when>
    <c:otherwise>
        <c:set var="user32" value="${sessionScope.user}" scope="request"/>
        <div class="user-details">
            <h2>User Details</h2>
            <p>Full Name     :  ${user32.name}</p>
            <p>Email         :  ${user32.email}</p>
            <p>Date Of Birth :  ${user32.dateOfBirth}</p>
            <p>Role          :  ${user32.role}</p>

            <form action="/bloodbank/${user32.role.toLowerCase()}/dashboard" method="get">
                <button type="submit">Go to Dashboard</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>