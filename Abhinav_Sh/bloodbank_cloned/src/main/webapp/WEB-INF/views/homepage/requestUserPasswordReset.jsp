<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h1> <u>Reset the request user password</u> </h1>
<form action="/bloodbank/auth/requestUserPassReset" method="post">
    Enter Your Email <input type="email" value="${username}" placeholder="Enter your email" name="username"/>
    Enter the new password <input type="password" placeholder="Enter your new password" name="newPassword"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>