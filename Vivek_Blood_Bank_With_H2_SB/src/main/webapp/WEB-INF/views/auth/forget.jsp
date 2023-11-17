<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>forget password</title>
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
            padding: 50px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .register-btn {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Forget Password Form</h2>
        <form action="/com/BloodBank/v1/auth/forgetpassword" method="post">
            <label for="email">Email:</label>
             <input type="email" id="email" name="email" required value="${not empty param.email ? param.email : ''}"><br>

            <label for="secretCode">Secret Code:</label>
           <input type="text" id="secretCode" name="secretCode" required value="${not empty param.secretCode ? param.secretCode : ''}"><br>

            <input type="submit" value="forget">
        </form>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color: green;">${message}</p>
        </c:if>
        <div class="register-btn">
              <p><a href="/com/BloodBank/v1/index">Home</a></p>
        </div>
    </div>
</body>
</html>
