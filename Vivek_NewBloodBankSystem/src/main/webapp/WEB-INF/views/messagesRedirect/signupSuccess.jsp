
<html>
<head>
    <title> Success </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .message-container {
            background-color: #ffeeba;
            color: #856404;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            text-align: center;
            position: relative;
        }

        .decorative-line {
            width: 100px;
            height: 5px;
            background-color: #ffc107;
            margin: 20px auto;
            border-radius: 5px;
        }

        h2 {
            font-size: 28px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-top: 20px;
        }

        a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="message-container">
        <div class="decorative-line"></div>
        <h2> Successfully Done!</h2>
          <p>${message}</p>

        <p>Redirecting to <a href="/com/BloodBank/v1//index.jsp">Home Page</a>...</p>
    </div>
    <script>
        setTimeout(function() {
            window.location.href = "/com/BloodBank/v1/index.jsp";
        }, 2000);
    </script>
</body>
</html>


