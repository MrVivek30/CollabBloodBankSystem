<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Signup</title>
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
            width: 80%; /* Set the width to fit within the screen */
            max-width: 400px; /* Set a maximum width for the container */
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        input[type="text"], input[type="password"], input[type="email"], input[type="number"], select, input[type="date"] {
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
        .login-btn {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Signup Form</h2>

        <form action="${pageContext.request.contextPath}/auth/signup" method="post">


           <label for="firstName">First Name:</label>
           <input type="text" id="firstName" name="firstName"  required value="${not empty param.firstName ? param.firstName : ''}" ><br>

           <label for="lastName">Last Name:</label>
           <input type="text" id="lastName" name="lastName" required value="${not empty param.lastName ? param.lastName : ''}"><br>

           <label for="dob">Date of Birth:</label>
           <input type="date" id="dob" name="dob" required value="${not empty param.dob ? param.dob : ''}"><br>

           <label for="email">Email:</label>
           <input type="email" id="email" name="email" required value="${not empty param.email ? param.email : ''}"><br>

           <label for="password">Password:</label>
           <input type="password" id="password" name="password" required minlength="6"    value="${not empty param.password ? param.password : ''}"><br>

           <label for="bloodGroup">Blood Group:</label>
           <select id="bloodGroup" name="bloodGroup" required value="${not empty param.bloodGroup ? param.bloodGroup : ''}" >

                     <option value="A+">A+</option>
                     <option value="A-">A-</option>
                     <option value="B+">B+</option>
                     <option value="B-">B-</option>
                     <option value="AB+">AB+</option>
                     <option value="AB-">AB-</option>
                     <option value="O+">O+</option>
                     <option value="O-">O-</option>
                 </select><br>


           <label for="role">Role:</label>
           <select id="role" name="role" required value="${not empty param.role ? param.role : ''}">
               <option value="agent">Agent</option>
               <option value="user">User</option>
           </select><br>

           <label for="address">Address:</label>
           <input type="text" id="address" name="address" required value="${not empty param.address ? param.address : ''}"><br>


            <input type="submit" value="Signup">
        </form>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <div class="login-btn">
            <p>Already registered? <a href="${pageContext.request.contextPath}/auth/login">Login here</a></p>
        </div>
    </div>
</body>
</html>
