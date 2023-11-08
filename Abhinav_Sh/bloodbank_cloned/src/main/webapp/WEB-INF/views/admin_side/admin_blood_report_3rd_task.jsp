<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.stream.Stream,java.util.stream.*" %>
<%@ page import="org.springproject.bloodbank.dto.RequestDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<body>
<div align="center">
    <h1><u>Blood Report</u></h1>
</div>
    <%! public String util(ArrayList<RequestDTO> arl,String bg,String status) {
        long count= arl.stream().filter(a->a.getBloodGroup().equalsIgnoreCase(bg)).
        filter(a->a.getStatus().equalsIgnoreCase(status)).count();
        return String.valueOf(count);
        }
        %>
<c:set var="arl" value="${requestScope.arData}" scope="session"/>
<c:set var="blood" value="${requestScope.blood}" scope="request"/>

<div align="center">
    <table border="1">
        <thead>
        <tr>
            <th>Blood Group</th>
            <th>Units in Stock</th>
            <th>Donate Requests</th>
            <th>Recieve Requests</th>
            <th>Pending</th>
            <th>Rejected</th>
            <th>Accepted</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${blood.entrySet()}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>

                <!-- Replace '+' with '%2B' -->
                <c:set var="blood2" value="${fn:replace(entry.key, '+', '%2B')}"/>

                <td><a href="/bloodbank/admin/allrequests?action=donate&status=all&bg=${blood2}"><button>Donors</button></a></td>
                <td><a href="/bloodbank/admin/allrequests?action=recieve&status=all&bg=${blood2}"><button>Requests</button></a></td>

                <%-- Calculate counts --%>
                <c:set var="pendingCount" value="${pageContext.page.util(arl, entry.key, 'PENDING')}"/>
                <c:set var="refusedCount" value="${pageContext.page.util(arl, entry.key, 'REFUSED')}"/>
                <c:set var="grantedCount" value="${pageContext.page.util(arl, entry.key, 'GRANTED')}"/>
                <!-- Display counts -->
                <td>${pendingCount}</td>
                <td>${refusedCount}</td>
                <td>${grantedCount}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- Dashboard button --%>
    <form action="/bloodbank/admin/dashboard" method="get">
        <button type="submit">Dashboard</button>
    </form>

</div>

</body>
</html>