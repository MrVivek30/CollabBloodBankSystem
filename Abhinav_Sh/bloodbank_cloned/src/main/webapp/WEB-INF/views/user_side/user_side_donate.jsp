<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Donation By the User</title>
</head>
<body>
<c:set var="user32" value="${sessionScope.user}" />
<c:choose>
    <c:when test="${user32 == null}">
        <h1>Please login first.</h1>
    </c:when>
    <c:when test="${user32.role != 'USER'}">
        <h1>You must be a logged in USER to access this page.</h1>
    </c:when>
    <c:otherwise>
        <c:set var="g" value="${requestScope.editRequestSubmitTo}" />
        <c:choose>
            <c:when test="${empty g}">
                <c:set var="g" value="request/requestToDonateOrRecieve"/>
            </c:when>
        </c:choose>
        <form action="/bloodbank/${g}" method="post">
            <table>
                <tr>
                    <td colspan="200">
                        <label for="date">Enter the Date till when you donate</label>
                    </td>
                    <td>
                        <input type="date" value="${req.dateTillOrOn}" name="dateTillOrOn" id="date" placeholder="Enter When you want to recieve"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="200">
                        <label for="number">How many units</label>
                    </td>
                    <td>
                        <input type="number" id="number" value="${req.noOfUnits}" name="noOfUnits" placeholder="NoOfUnits">
                    </td>
                </tr>
                <tr>
                    <td colspan="200">
                        <input type="hidden" name="index" value="${index}"/>
                        <input type="hidden" name="action" value="Donate"/>
                    </td>
                </tr>
            </table>
            <button type="submit">Submit</button>
        </form>

        <form action="/bloodbank/${user32.role.toLowerCase()}/dashboard" method="get">
            <button type="submit">dashboard</button>
        </form>

        <p style=”color:red'>${error}</p>
    </c:otherwise>
</c:choose>
</body>
</html>