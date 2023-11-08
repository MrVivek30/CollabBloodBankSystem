<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="org.springproject.bloodbank.dto.RequestDTO" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<body>
<div align="center">
    <h1><u>Blood Report</u></h1>
</div>
<%
HashMap<String, Integer> blood = (HashMap<String, Integer>) request.getAttribute("blood");
%>
    <div align="center">
        <table border="1">
            <thead>
            <tr>
                <th>Blood Group</th>
                <th>Units in Stock</th>
                <th>Donors Constituting the Units</th>
                <th>Pending Receive Requests</th>
            </tr>
            </thead>
            <tbody>
            <% for (Map.Entry<String, Integer> bg : blood.entrySet()) { %>
            <tr>
                <td><%= bg.getKey() %></td>
                <td><%= bg.getValue() %></td>
                <% String blood2 = bg.getKey().replace("+", "%2B"); %>
                <td><a href="/bloodbank/admin/allrequests?action=donate&status=granted&bg=<%= blood2 %>"><button>View Donors</button></a></td>
                <td><a href="/bloodbank/admin/allrequests?action=recieve&status=pending&bg=<%= blood2 %>"><button>View Requests</button></a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <br />
        <form action="/bloodbank/admin/dashboard" method="get">
            <button type="submit">Dashboard</button>
        </form>
    </div>
</body>
</html>