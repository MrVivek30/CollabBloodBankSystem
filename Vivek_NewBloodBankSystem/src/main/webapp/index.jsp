<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Bank System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color:#c79383;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            text-align: center;
            background-color: white;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        }

        .heading {
            font-size: 36px;
            margin-bottom: 20px;
        }

        .btn-container {
            margin-top: 30px;
        }

        .btn {
            display: inline-block;
            margin: 10px;
            padding: 15px 40px;
            font-size: 18px;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .login-btn {
            background-color: #4CAF50;
        }

        .login-btn:hover {
            background-color: #45a049;
        }

        .register-btn {
            background-color: #2196F3;
        }

        .register-btn:hover {
            background-color: #0b7dda;
        }

        .admin-btn {
            background-color: #FFC107;
        }

        .admin-btn:hover {
            background-color: #ffb60a;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="heading">Welcome to Blood Bank System</div>
        <div class="btn-container">
            <a href="auth/login" class="btn login-btn">Login</a>
            <a href="auth/signup" class="btn register-btn">Register</a>
         <%--     <a href="admin/login" class="btn admin-btn">Admin Login</a>  --%>
        </div>
    </div>
</body>

</html>
