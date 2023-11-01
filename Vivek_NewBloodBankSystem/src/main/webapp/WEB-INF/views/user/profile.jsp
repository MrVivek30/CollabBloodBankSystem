<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
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
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
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
        <h2>User Profile</h2>
            <p><strong>User ID:</strong> ${curr.id}</p>
            <p><strong>AccountType :</strong> ${curr.role.toUpperCase()}</p>
            <p><strong>First Name:</strong> ${curr.firstName}</p>
            <p><strong>Last Name:</strong> ${curr.lastName}</p>
            <p><strong>Email:</strong> ${curr.email}</p>
            <p><strong>Date Of Birth:</strong> ${curr.dob}</p>
            <p><strong>Blood Group:</strong> ${curr.bloodGroup}</p>
            <p><strong>Account Created On:</strong> ${curr.accountCreationDateTime}</p>
<c:choose>
    <c:when test="${curr.role.trim() == 'Admin'}">
        <a href="/com/BloodBank/v1/admin/dashboard" class="btn">Dashboard</a>
    </c:when>
    <c:when test="${curr.role.trim() == 'user'}">
        <a href="/com/BloodBank/v1/user/userDashboard" class="btn">Dashboard</a>
    </c:when>
    <c:when test="${curr.role.trim() == 'agent'}">
        <a href="/com/BloodBank/v1/user/userDashboard" class="btn">Dashboard</a>
    </c:when>
</c:choose>

    </div>
</body>

</html>
