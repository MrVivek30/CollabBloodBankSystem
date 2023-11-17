<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>
<c:set var="user" value="${sessionScope.user}" />
<c:choose>
    <c:when test="${user == null}">
        <p>Please log in to access this portal.</p>
    </c:when>
    <c:when test="${user.role != 'USER'}">
        <p>You must be a USER to access this portal.</p>
    </c:when>
    <c:otherwise>
        <div class="user-details">
            <h2>User Details</h2>
            <p>Full Name     :  ${user.name}</p>
            <p>Email         :  ${user.email}</p>
            <p>Date Of Birth :  ${user.dateOfBirth}</p>
            <p>Role          :  ${user.role}</p>
            <p>BloodGroup    :  ${user.bloodGroup}</p>

            <form action="/bloodbank/user/dashboard" method="get">
                <button type="submit">Go to Dashboard</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>