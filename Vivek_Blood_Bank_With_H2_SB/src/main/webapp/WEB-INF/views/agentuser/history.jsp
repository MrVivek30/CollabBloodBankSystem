<%@page import="java.util.*"%>
<%@ page import="com.trackobit.dto.RequestDTO" %>
<%@page import="com.trackobit.dto.UserDTO"%>
<%@page import="java.util.stream.Collectors"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>History</title>
</head>
<style>
    table,tr,td,th{
        border: 2px solid black;
        border-collapse: collapse;
        padding: 8px;
        text-align: center;
    }
</style>

<body>
    <h1>History</h1>
     <%
            List<RequestDTO> userList = (List<RequestDTO>)request.getAttribute("requestList");
            if(userList!=null){
     %>
    <table>
            <caption>Donate History</caption>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Unit</th>
                <th>Date</th>
                <th>BloodGroup</th>
                <th>Role</th>
                <th>Type</th>
                <th>Address</th>
                <th>Status</th>
                <th>Comment</th>
            </tr>
             <%
                for(RequestDTO req: userList){
                    if(req.getType().equals("Donate")){
             %>
            <tr>
             <td><%=req.getName()%></td>
             <td><%=req.getAge()%></td>
             <td><%=req.getUnit()%></td>
             <td><%=req.getLocalDateTime()%></td>
             <td><%=req.getBloodGroup()%></td>
             <td><%=req.getRole()%></td>
             <td><%=req.getType()%></td>
             <td><%=req.getAddress()%></td>
             <td><%=req.getStatus()%></td>
             <td><%=req.getRemark()%></td>
            </tr>
          <%
            }
             }
          %>

    </table>

    <br/>
    <br/>
    <br/>

    <table>
                <caption>Receive History</caption>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Unit</th>
                    <th>Date</th>
                    <th>BloodGroup</th>
                    <th>Role</th>
                    <th>Type</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Comment</th>
                </tr>
                 <%
                    for(RequestDTO req: userList){
                        if(req.getType().equals("Receive")){

                 %>
                <tr>
                    <td><%=req.getName()%></td>
                    <td><%=req.getAge()%></td>
                    <td><%=req.getUnit()%></td>
                    <td><%=req.getLocalDateTime()%></td>
                    <td><%=req.getBloodGroup()%></td>
                    <td><%=req.getRole()%></td>
                    <td><%=req.getType()%></td>
                    <td><%=req.getAddress()%></td>
                   <td><%=req.getStatus()%></td>
                    <td><%=req.getRemark()%></td>
                </tr>
              <%
                      }
                 }
              %>

        </table>

    <%
        }

        else{
    %>
    <h1>No donate history</h1>
    <%
    }
    %>
    <a href="dashboard">Dashboard</a>
</body>
</html>