<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request User Login</title>
</head>
<body>
<form action="/bloodbank/auth/requestUserLogin" method="post">
Enter the Email <input type="email" value="${username}" placeholder="Enter your email" name="username"/>
Enter Your Password <input type="password" value="${password}" placeholder="Enter your password" name="password"/>
<button type="submit">Submit</button>
</form>
<p style="color: red;">${error}</p>
</body>
</html>