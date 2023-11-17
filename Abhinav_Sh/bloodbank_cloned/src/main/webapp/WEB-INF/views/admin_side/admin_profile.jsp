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
<% UserDTO user32=(UserDTO)session.getAttribute("user");
if(user32==null)
request.getRequestDispatcher("/bloodbank/auth/home").forward(request,response);
%>

<div class="user-details">
    <h2>User Details</h2>
    <p>Full Name     :  <%= user32.getName() %></p>
    <p>Email         :  <%= user32.getEmail() %></p>
    <p>Date Of Birth :  <%= user32.getDateOfBirth() %></p>
    <p>Role          :  <%= user32.getRole() %></p>

    <form action="/bloodbank/admin/dashboard" method="get">
        <button type="submit">Go to Dashboard</button>
    </form>
</div>

</body>
</html>