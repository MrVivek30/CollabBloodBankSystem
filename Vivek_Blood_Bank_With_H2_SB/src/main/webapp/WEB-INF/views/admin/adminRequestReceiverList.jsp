   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receive History</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    h1 {
        color: #b71c1c;
    }

    table {
        width: 80%;
        margin-top: 20px;
        border-collapse: collapse;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
        border: 1px solid #dddddd;
        padding: 12px;
        text-align: center;
    }

    th {
        background-color: #f2f2f2;
    }

            .no-receivers-message {
        margin-top: 20px;
        font-size: 18px;
        color: #333;
    }

            .action-links {
        margin-top: 20px;
    }

            .action-links a {
        text-decoration: none;
        margin: 0 10px;
        padding: 10px 20px;
        border: 2px solid #1565c0;
        border-radius: 5px;
        color: #1565c0;
        transition: all 0.3s ease;
    }

            .action-links a:hover {
        background-color: #1565c0;
        color: #ffffff;
    }
    select, input[type="text"] {
        width: calc(100% - 20px);
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }
.btn {
        margin-top: 10px;
        display: inline-block;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }

                .btn:hover {
        background-color: #45a049;
    }
        </style>
</head>

<body>
    <div class="container">
    <h2>Receive History</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Unit</th>
    <th>Blood Group</th>
                <th>Address</th>
                <th>Date & Time</th>
                <th>Role</th>
                <th>Type</th>

             <th>Status</th>
                   <th>Action</th>
          <th>Remark Given</th>
            <th>Remark</th>
            <th>Req Iteration Count</th>

            </tr>
            <c:forEach var="request" items="${requestedReceiverList}">
                <tr>
    <td>${request.id}</td>
    <td>${request.name}</td>
    <td>${request.age}</td>
    <td>${request.email}</td>
    <td>${request.unit}</td>
    <td>${request.bloodGroup}</td>
    <td>${request.address}</td>
    <td>${request.localDateTime}</td>
    <td>${request.role.toUpperCase()}</td>
    <td>${request.type}</td>

                    <td style="color:red;">${request.status}</td>
                   <c:choose>
                     <c:when test="${request.getStatus() eq 'Pending'}">
                         <td>
                             <a href="receiveAccept/${request.uniqueId}">Accept</a><br/><br/>
                             <a href="receiveReject/${request.uniqueId}">Reject</a>
                         </td>
                     </c:when>
                     <c:otherwise>
    <td>N A</td>
                     </c:otherwise>
                 </c:choose>
    <td>
    ${request.remark}
           </td>
                  <c:choose>
                   <c:when test="${request.getStatus() eq 'Rejected'}">
                      <td>
                           <form action="/com/BloodBank/v1/admin/receiveRemark" method="post" name="${request.uniqueId}" id="${request.uniqueId}">
                    <input type="hidden" id="userId" name="uniqueId" value="${request.uniqueId}">
                               <label for="remark">Remark:</label>
                               <textarea id="remark" name="receiveRemark" rows="4" cols="5"></textarea>
                                 <input type="submit" value="Submit">
                                 </form>
                                 </td>

                          </c:when>
                          <c:otherwise>
    <td>N A</td>
                          </c:otherwise>
                      </c:choose>

<td>${request.iterationCount} </td>
                </tr>
            </c:forEach>
        </table>
    </div>


        <a href="/com/BloodBank/v1/admin/dashboard" class="btn">Dashboard</a>
</body>

</html>
