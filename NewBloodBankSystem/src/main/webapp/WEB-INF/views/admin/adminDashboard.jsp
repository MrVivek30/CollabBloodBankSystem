<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .sidebar {
            height: 100vh;
            width: 250px;
            background-color: #333;
            position: fixed;
            left: 0;
            top: 0;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidebar a {
            padding: 8px 8px 8px 16px;
            text-decoration: none;
            font-size: 20px;
            color: white;
            display: block;
        }

        .sidebar a:hover {
            background-color: #555;
        }

        .content {
            margin-left: 260px;
            padding: 20px;
        }

        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            margin-bottom: 10px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="sidebar">

               <a href="/com/BloodBank/v1/user/profile" class="btn">View Profile</a>
 <%--      <a href="/com/BloodBank/v1/admin/getDonationList">View All Donors</a>
        <a href="/com/BloodBank/v1/admin/getReceiverList">View All Receivers</a>
        <a href="/com/BloodBank/v1/admin/getBloodCuntByGroup">View Blood Counts</a> --%>
    </div>

    <div class="content">
        <h1>Welcome to Admin Dashboard</h1>
                <h2> Profile Type ${user.role.toUpperCase()}</h2>


        <h3>Actions: Accept/ Reject /Remark </h3>
        <a href="/com/BloodBank/v1/admin/getDonationList" class="btn">View All Donors</a>
        <a href="/com/BloodBank/v1/admin/getReceiverList" class="btn">View All Receivers</a>
       <h3>Actions: Blood Bank UserDetails Agent/ User</h3>
        <a href="/com/BloodBank/v1/admin/getAllUserList" class="btn">View all UserList</a>
         <h3>Actions: Filter by category </h3>
        <a href="/com/BloodBank/v1/admin/getAllBloodHistoryHandler" class="btn">Blood Bank History / Filter</a>
            <h3>Report card</h3>

  <a href="/com/BloodBank/v1/admin/getBloodCuntByGroup" class="btn"> Blood group Report</a>
  <a href="/com/BloodBank/v1/admin/bloodUnitsAndDonorsCountReport" class="btn"> Blood group  Report with UsrCount</a>
  <a href="/com/BloodBank/v1/admin/bloodBankReportCard" class="btn"> Blood Bank Report Card</a>


        <h3>Logout</h3>

               <a href="/com/BloodBank/v1/auth/logout" class="btn">Logout</a>
    </div>

</body>
</html>
