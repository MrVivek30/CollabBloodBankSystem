<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Request Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h2 {
            color: #b71c1c;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="email"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #b71c1c;
            color: #ffffff;
            border: none;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h2>Request Form</h2>
    <form action="/com/BloodBank/v1/agent/receive" method="post">
       Name: <input type="text" name="name" minlength="3" required value="${not empty param.name? param.name : ''}"><br>
             Age: <input type="number" name="age" required value="${not empty param.age? param.age : ''}" ><br>
             Email: <input type="email" name="email" required value="${not empty param.email? param.email : ''}"><br>
              Blood Group:
                      <select name="bloodGroup" required value="${not empty param.bloodGroup? param.bloodGroup : ''}">
                          <option value="A+">A+</option>
                          <option value="A-">A-</option>
                          <option value="B+">B+</option>
                          <option value="B-">B-</option>
                          <option value="AB+">AB+</option>
                          <option value="AB-">AB-</option>
                          <option value="O+">O+</option>
                          <option value="O-">O-</option>
                      </select><br>
             Unit: <input type="number" name="unit" required value="${not empty param.unit? param.unit : ''}" min="1"><br>
             Address: <input type="text" name="address" required value="${not empty param.address? param.address : ''}"><br>

             <input type="submit" value="Submit Request">
         </form>
         <c:if test="${not empty error}">
                     <p style="color: red;">${error}</p>
                 </c:if>
                 <c:if test="${not empty message}">
                     <p style="color: green;">${message}</p>
                 </c:if>
</body>
</html>
