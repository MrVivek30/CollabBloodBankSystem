<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,java.util.Map.Entry,java.util.Map.*,java.util.HashMap" %>
<%@ page import="org.springproject.bloodbank.dto.RequestDTO" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<body>
<div align="center">
    <h1>
        <u>Total Requests</u>
    </h1>
</div>
<%
ArrayList<RequestDTO> arl=(ArrayList<RequestDTO>)request.getAttribute("arData");
session.setAttribute("history",arl);
HashMap<String,Integer> blood=(HashMap<String,Integer>)request.getAttribute("blood");
%>
    <div align="center">
        <u>Blood Report</u>

    <table border="1">
        <tr>
            <th>
                Blood Group
            </th>
            <%
            for(String bg: blood.keySet()){
            %>
            <th>
            <%out.print(bg);}%>
            </th>
        </tr>
        <tr>
            <th>
                Units in Stock
            </th>
            <%
            for(Integer bg: blood.values()){
            %>
            <td>
                <%out.print(bg);}%>
            </td>
        </tr>
    </table>
    </div>
    <form action="/bloodbank/admin/allrequests" method="get">
        <fieldset>
            <legend><h2 align="center"><u>Filter</u></h2></legend>
            <hr>
            <table>
                <tr>
                    <th colspan="200"><p>Donate or Receive:</p></th>
                </tr>
                <tr>
                    <td>
                        <label for="donate">Donate</label>
                    </td>
                    <td>
                        <input type="radio" id="donate" name="action" value="donate">
                    </td>

                </tr>
                <tr>
                    <td>
                        <label for="receive">Receive</label>
                    </td>
                    <td>
                        <input type="radio" id="receive" name="action" value="recieve">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="all">All</label>
                    </td>
                    <td>
                        <input type="radio" id="all" name="action" value="all" checked="checked">
                    </td>
                </tr>
                <tr>
                    <td colspan="200"><br><br></td>
                </tr>
                <tr>
                    <th align="left">
                        <p>Status:</p>
                    </th>
                </tr>
                <tr>
                    <td>
                        <label for="pending">Pending</label>
                    </td>
                    <td>
                        <input type="radio" id="pending" name="status" value="pending">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="granted">Granted</label>
                    </td>
                    <td>
                        <input type="radio" id="granted" name="status" value="granted">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="refused">Refused</label><br>
                    </td>
                    <td>
                        <input type="radio" id="refused" name="status" value="refused">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="all1">All</label>
                    </td>
                    <td>
                        <input type="radio" id="all1" name="status" value="all" checked="checked">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="bg">Filter by Blood Type</label>
                    </td>
                    <td>
                        <select id="bg" name="bg">
                            <option value="" selected>Display All</option>
                            <option value="A+">A+</option>
                            <option value="A-">A-</option>
                            <option value="B+">B+</option>
                            <option value="B-">B-</option>
                            <option value="AB+">AB+</option>
                            <option value="AB-">AB-</option>
                            <option value="O+">O+</option>
                            <option value="O-">O-</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="200">
                        <input type="submit" value="Filter">
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
<div align="center">
    <table border="1" cellspacing="25">
        <tr>
            <th>Placed By Name</th>
            <th>Role</th>
            <th>Placed For</th>
            <th>Age</th>
            <th>Email</th>
            <th>Address</th>
            <th>Date</th>
            <th>Blood Group</th>
            <th>Units</th>
            <th>Type</th>
            <th>RequestIterationCount</th>
            <th>Status</th>
            <th>Accept</th>
<!--            <th>Blocked</th>-->
            <th>Reject</th>
        </tr>
        <%
        int size=arl.size();
        for(int s=0;s<size;s++){
        RequestDTO i=arl.get(s);
        %>
        <tr>
            <td>
                <%= i.getPlacedBy().getName() %>
            </td>
            <td>
                <%= i.getRoleOfPlacedBy() %>
            </td>
           <td>
               <%= i.getPlacedFor() %>
           </td>
            <td>
                <%= i.getAge() %>
            </td>
            <td>
                <%= i.getEmail() %>
            </td>
            <td>
                <%= i.getAddress() %>
            </td>
            <td>
                <%= i.getDateTillOrOn().toString() %>
            </td>
            <td>
                <%= i.getBloodGroup() %>
            </td>
            <td>
                <%= String.valueOf(i.getNoOfUnits()) %>
            </td>
            <td>
                <%= i.getAction() %>
            </td>
            <td>
                <%= i.getRequestIterationCount() %>
            </td>
            <td>
                <%= i.getStatus() %>
            </td>
            <td>
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <form action="/bloodbank/admin/candidateProcessor" method="post">
                        <input type="hidden" name="index" value="<%=String.valueOf(s)%>">
                        <input type="hidden" name="what" value="accept">
                        <button type="submit">Accept</button>
                    </form>
                </div>
            </td>
            <td>
                <div style="display: flex; flex-direction: column; align-items: center;">
                    <form action="/bloodbank/admin/candidateProcessor" method="post">
                        <input type="hidden" name="index" value="<%=String.valueOf(s)%>">
                        <input type="hidden" name="what" value="reject">
                        <input type="text" name="remark" placeholder="remarks">
                        <button type="submit">Reject</button>
                    </form>
                </div>
            </td>
        </tr>
        <% }%>
    </table>
    <form action="/bloodbank/admin/dashboard" method="get">
        <button type="submit">dashboard</button>
    </form>
</div>
</body>
</html>