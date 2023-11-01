<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>



<head>
    <title>Success Page</title>
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

        .success-container {
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
    <div class="success-container">
        <h1>Successfully Done</h1>
        <p>${msg}</p>

           <p>Redirecting to <a href="/com/BloodBank/v1/index.jsp">Home Page</a>...</p>
    </div>


     <script>
            setTimeout(function() {
                window.location.href = "/com/BloodBank/v1/index.jsp";
            }, 2000);
        </script>
</body>
</html>
