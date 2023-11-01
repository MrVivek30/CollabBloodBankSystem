<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 600px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
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
        <h2>Welcome to Dashboard ${user.firstName}</h2>
        <h4> Profile Type ${user.role.toUpperCase()}</h4>

    <h3>Profile</h3>
           <a href="profile" class="btn">View Profile</a>

     <h3>Actions</h3>
           <c:choose>
            <c:when test="${user.role == 'Admin'}">
                               <a href="/com/BloodBank/v1/admin/dashboard" class="btn">Dashboard</a>
                          </c:when>

               <c:when test="${user.role == 'user'}">
                   <!-- Options for regular users -->
                   <a href="/com/BloodBank/v1/request/donate" class="btn">Donate Blood</a>
                   <a href="/com/BloodBank/v1/request/receive" class="btn">Receive Blood</a>
               </c:when>
               <c:when test="${user.role == 'agent'}">
                   <!-- Options for agents -->
                   <a href="/com/BloodBank/v1/agent/donate" class="btn">Donate Blood as Agent</a>
                   <a href="/com/BloodBank/v1/agent/receive" class="btn">Receive Blood as Agent</a>
               </c:when>
           </c:choose>

           <br>
 <c:choose>
     <c:when test="${user.role == 'user'}">
         <h3>User History</h3>
         <a href="history" class="btn">User History</a>
     </c:when>
     <c:when test="${user.role == 'agent'}">
         <h3>Agent History</h3>
         <a href="/com/BloodBank/v1/agent/history" class="btn">Agent History</a>
     </c:when>
 </c:choose>
<br>
 <h3>Logout</h3>

       <a href="/com/BloodBank/v1/auth/logout" class="btn">Logout</a>
    </div>
</body>
