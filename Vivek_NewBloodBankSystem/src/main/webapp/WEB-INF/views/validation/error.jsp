<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>


<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #e74c3c;
            margin-bottom: 20px;
        }

        p {
            color: #333;
            margin-bottom: 30px;
        }

        .go-back-link {
            text-decoration: none;
            color: #3498db;
            font-weight: bold;

        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error Occurred</h1>
        <p>${msg}</p>

         </br>
         <br>
<a
              <a href="/com/BloodBank/v1/index.jsp"  class="go-back-link">Home page</a>
    </div>
</body>
</html>
