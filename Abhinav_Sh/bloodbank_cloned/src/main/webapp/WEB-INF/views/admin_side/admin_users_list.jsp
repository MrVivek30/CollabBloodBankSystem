<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="org.springproject.bloodbank.dto.UserDTO" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<c:set var="arData" value="${requestScope.arData != null ? requestScope.arData : sessionScope.arData}" scope="session"/>
<div align="center">
    <h1>
        <u>
            User's List
        </u>
    </h1>
    <table border="2">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>DoB</th>
            <th>BloodGroup</th>
            <th>Role</th>
            <th>Unlock</th>
        </tr>
        <c:forEach var="userDTO" items="${arData}" varStatus="status">
            <tr>
                <td>${userDTO.name}</td>
                <td>${userDTO.email}</td>
                <td>${userDTO.dateOfBirth}</td>
                <td>${userDTO.bloodGroup}</td>
                <td>${userDTO.role}</td>
                <td>
                    <form action="/bloodbank/admin/unBlock" method="post">
                        <input type="text" name="index" disabled value="${status.index}">
                        <button type="submit" ${userDTO.locked ? "" : "disabled"}>UnBlock</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>