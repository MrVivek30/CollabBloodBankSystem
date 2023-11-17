<%@ page import="com.trackobit.dto.RequestDTO" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
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
<body>
    <div class="container">
        <h1>Profile</h1>
         <div>Name: ${request.name}</div>
         <div>Age: ${request.age}</div>
         <div>Email: ${request.email}</div>
         <div>Role: ${request.role}user</div>
         <div>Address:${request.address}</div>

         <br/>
          <a href="dashboard" class="btn">Dashboard</a>
    </div>
</body>
</html>