<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Password Reset</title>
</head>
<body>
<form action="/bloodbank/auth/forgetPass" method="post">
    <label for="username">Enter the registered email id</label>
    <input id="username" name="email" placeholder="Your Email" value="${email}" type="email" required />
    <label for="securityCode">Enter the security code For Pass reset</label>
    <input id="securityCode" type="text" name="securityCode" placeholder="Your security code" value="${securityCode}" required/>
    <button type="submit"> Reset </button>
</form>
<form action="/bloodbank/auth/home" method="get">
    <button type="submit">Home</button>
</form>
<p style="color: red;">${error}</p>
</body>
</html>