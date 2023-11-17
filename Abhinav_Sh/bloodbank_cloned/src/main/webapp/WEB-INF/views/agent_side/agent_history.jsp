<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<body>
<div align="center">
    <h1>
        <u>History</u>
    </h1>
</div>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <p>Please log in to access this portal.</p>
    </c:when>
    <c:when test="${sessionScope.user.role != 'AGENT'}">
        <p>You must be a AGENT to access this portal.</p>
    </c:when>
    <c:otherwise>
        <c:set var="arl" value="${requestScope.arData}" scope="request"/>
        <div align="center">
            <table border="1" cellspacing="25">
                <tr>
                    <th>Placed For</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Date</th>
                    <th>Blood Group</th>
                    <th>Units</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Request Iteration Count</th>
                    <th>Remark</th>
                    <th>Edit</th>
                </tr>
                <c:forEach var="requestDTO" items="${arl}" varStatus="status">
                    <tr>
                        <td>${requestDTO.placedFor}</td>
                        <td>${requestDTO.age}</td>
                        <td>${requestDTO.email}</td>
                        <td>${requestDTO.address}</td>
                        <td>${requestDTO.dateTillOrOn}</td>
                        <td>${requestDTO.bloodGroup}</td>
                        <td>${requestDTO.noOfUnits}</td>
                        <td>${requestDTO.action}</td>
                        <td>${requestDTO.status}</td>
                        <td>${requestDTO.requestIterationCount}</td>
                        <td>${requestDTO.remark}</td>
                        <td>
                            <%-- Check if remark is not empty --%>
                            <c:choose>
                                <c:when test="${not empty requestDTO.remark}">
                                    <%-- If remark is not empty, enable the Edit button --%>
                                    <form action="/bloodbank/${sessionScope.user.role.toLowerCase()}/editRequest" method="post">
                                        <input type="hidden" name="index" value="${status.index}">
                                        <button type="submit">Edit</button>
                                    </form>
                                </c:when>
                                <%-- If remark is empty, disable the Edit button --%>
                                <c:otherwise><button disabled>Edit</button></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%-- Dashboard button --%>
            <form action="/bloodbank/agent/dashboard" method="get">
                <button type="submit">Dashboard</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>