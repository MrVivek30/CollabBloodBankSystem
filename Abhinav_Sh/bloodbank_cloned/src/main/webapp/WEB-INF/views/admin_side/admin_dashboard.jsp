<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
<div align="centers">
<h1>Welcome Admin to the DashBoard</h1>
</div>
    <div align="center" style="display: flex; flex-direction: column; align-items: center;">
        <form action="/bloodbank/admin/profile" method="get">
            <button type="submit">Profile</button>
        </form>
        <form action="/bloodbank/admin/allrequests" method="get">
            <input type="hidden" name="type" value="recieve">
            <button type="submit">All Requests</button>
        </form>
        <form action="/bloodbank/admin/bloodReport" method="get">
            <input type="hidden" name="type" value="recieve">
            <button type="submit">Blood Report</button>
        </form>
        <form action="/bloodbank/admin/overallBloodReport" method="get">
            <input type="hidden" name="type" value="recieve">
            <button type="submit">Over all Blood Report</button>
        </form>
        <form action="/bloodbank/admin/usersList" method="get">
            <button type="submit">User's List</button>
        </form>
        <form action="/bloodbank/admin/logout" method="get">
            <button type="submit">Logout</button>
        </form>
        <form action="/bloodbank/auth/home" method="get">
            <button type="submit">Home</button>
        </form>
    </div>
<br>
</body>
</html>