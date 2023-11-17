<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<c:set var="user32" value="${sessionScope.user}" />
<c:choose>
    <c:when test="${user32 == null}">
        <p>Please log in to access this portal.</p>
    </c:when>
    <c:when test="${user32.role != 'USER'}">
        <p>You must be a USER to access this portal.</p>
    </c:when>
    <c:otherwise>
        <c:set var="arl" value="${requestScope.arData}" />
        <c:set var="arl" value="${sessionScope.arData}" scope="session" />

        <div align="center">
            <table border="1" cellspacing="25">
                <tr>
                    <th>Date</th>
                    <th>Blood Group</th>
                    <th>Units</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>RequestIterationCount</th>
                    <th>Remark</th>
                    <th>Edit</th>
                </tr>

                <c:forEach var="i" items="${arl}" varStatus="status">
                    <tr>
                        <td>${i.dateTillOrOn}</td>
                        <td>${i.bloodGroup}</td>
                        <td>${i.noOfUnits}</td>
                        <td>${i.action}</td>
                        <td>${i.status}</td>
                        <td>${i.requestIterationCount}</td>
                        <td>${i.remark}</td>


                        <c:choose>
                            <c:when test="${not empty i.remark}">

                                <td><form action="/bloodbank/user/editRequest" method="post">
                                    <input type="hidden" name="index" value="${status.index}">
                                    <button type="submit">Edit</button></form></td>

                            </c:when>


                            <c:otherwise><td><form action="/bloodbank/user/editRequest" method="post">
                                <input type="hidden" name="index" value="${status.index}">
                                <button type="submit" disabled>Edit</button></form></td></c:otherwise>

                        </c:choose>

                    </tr>

                </c:forEach>

            </table>


            <form action="/bloodbank/user/dashboard" method="get">
                <button type="submit">dashboard</button></form>

        </div>

    </c:otherwise>

</c:choose>

</body>
</html>