<html>
<head>
    <title>Login Success</title>

</head>
<body>
    <h2>Login Successful!</h2>
    <p>${message}</p>
        <script>
                   setTimeout(function() {
                       window.location.href = "/com/BloodBank/v1/admin/dashboard";
                   }, 1000);
               </script>
</body>
</html>
