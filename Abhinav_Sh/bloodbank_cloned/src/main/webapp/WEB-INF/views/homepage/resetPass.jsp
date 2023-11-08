<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Pass</title>
</head>
<body>
<form action="/bloodbank/auth/newPass" method="post">
    <input type="hidden" value="${email}" name="email">
    <input type="hidden" value="${securityCode}" name="securityCode">
    <label for="newPass">Ente new password</label>
    <input id="newPass" placeholder="Enter your new password" type="text" required name="newPass"/>
    <button type="submit">Change Pass</button>
</form>

<h1 style="color: red;">${error}</h1>
</body>
</html>