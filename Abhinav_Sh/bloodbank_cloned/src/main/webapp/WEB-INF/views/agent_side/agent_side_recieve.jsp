<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="org.springproject.bloodbank.model.User" %>
<%@ page import="org.springproject.bloodbank.service.RequestService" %>
<%@ page import="org.springproject.bloodbank.model.Request" %>
<%@ page import="org.springproject.bloodbank.dto.RequestDTO" %>
<%@ page import="org.springproject.bloodbank.dto.UserDTO" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Donation By the User</title>
</head>
<body>
<%
UserDTO user32=(UserDTO)session.getAttribute("user");
if(user32==null)
request.getRequestDispatcher("/bloodbank/auth/home").forward(request,response);
Object attribute = request.getAttribute("editRequestSubmitTo");
String g = attribute == null || !(attribute instanceof String) ? "" : (String) attribute;
RequestDTO r=(RequestDTO)request.getAttribute("req");
if(g.isEmpty())
g="request/requestToDonateOrRecieve";
String bg=((r==null)?null:r.getBloodGroup());
Integer bgIndex=0;
if(bg==null || bg.equalsIgnoreCase("A+"))
bgIndex=1;
else if(bg.equalsIgnoreCase("A-"))
bgIndex=2;
else if(bg.equalsIgnoreCase("B+"))
bgIndex=3;
else if(bg.equalsIgnoreCase("B-"))
bgIndex=4;
else if(bg.equalsIgnoreCase("AB+"))
bgIndex=5;
else if(bg.equalsIgnoreCase("AB-"))
bgIndex=6;
else if(bg.equalsIgnoreCase("O+"))
bgIndex=7;
else if(bg.equalsIgnoreCase("O-"))
bgIndex=8;
else
bgIndex=1;
%>
<div align="center">
<form action="/bloodbank/<%=g%>" method="post" >
    <table>
        <tr>
            <td colspan="200">
                <label for="name" >Enter The Name</label>
            </td>
            <td colspan="200">
                <input id="name" required type="text" value="${req.getPlacedFor()}" name="placedFor" placeholder="Enter Your Name"/>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="email" >Enter The Email</label>
            </td>
            <td colspan="200">
                <input id="email" required type="email" value="${req.getEmail()}" name="email" placeholder="Enter Your emailId"/>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="age" >Enter The Age</label>
            </td>
            <td colspan="200">
                <input id="age" required type="number" value="${req.getAge()}" name="age" placeholder="Enter Your age"/>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="address" >Enter The Address</label>
            </td>
            <td colspan="200">
                <input id="address" required type="text" value="${req.getAddress()}" name="address" placeholder="Enter Your Address"/>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="date">Enter the Date when you want to recieve</label>
            </td>
            <td>
                <input  required type="date" value="${req.getDateTillOrOn()}" name="dateTillOrOn" id="date" placeholder="Enter When you want to recieve"/>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="choice">Choose a BloodGroup</label>
            </td>
            <td>
                <select name="bloodGroup" id="choice">
                    <option value="A+" <%= (bgIndex==1 )? "selected" : "" %> >A+</option>
                    <option value="A-" <%= (bgIndex==2 )? "selected" : "" %> >A-</option>
                    <option value="B+" <%= (bgIndex==3 )? "selected" : "" %> >B+</option>
                    <option value="B-" <%= (bgIndex==4 )? "selected" : "" %> >B-</option>
                    <option value="AB+"<%= (bgIndex==5 )? "selected" : "" %> >AB+</option>
                    <option value="AB-"<%= (bgIndex==6 )? "selected" : "" %> >AB-</option>
                    <option value="O+" <%= (bgIndex==7 )? "selected" : "" %> >O+</option>
                    <option value="O-" <%= (bgIndex==8 )? "selected" : "" %> >O-</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <label for="number">How many units</label>
            </td>
            <td>
                <input required type="number"  value="${req.getNoOfUnits()}"id="number" name="noOfUnits" placeholder="NoOfUnits">
            </td>
        </tr>
        <tr>
            <td colspan="200">
                <input type="hidden" name="index" value="${index}"/>
                <input type="hidden" name="action" value="Recieve">
            </td>
        </tr>
    </table>
    <button type="submit">Submit</button>
</form>
<form action="/bloodbank/agent/dashboard" method="get">
    <button type="submit">dashboard</button>
</form>
<p style="color:red;">${error}</p>
</div>
</body>
</html>