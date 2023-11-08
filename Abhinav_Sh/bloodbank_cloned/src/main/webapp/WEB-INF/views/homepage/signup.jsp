<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Enter Your Details</title>
</head>
<body>
<form action="/bloodbank/auth/registeruser" id="myForm" method="post">
    <div align="center">Register New User</div>
    <br>
    <hr/>
    <table align="center" border="0">
        <tr align="center">
            <td align="left">
                <label for="email">Enter Your Email</label>
            </td>
            <td>
                <input id="email" name="email"  placeholder="Your Email" value="${user.email}" type="email"/>
            </td>
            <td>
                <span id="emailError" style="color: red;"></span>
            </td>
        </tr>
        <tr align="center">
            <td align="left">
                <label for="password">Enter Your Password</label>
            </td>
            <td>
                <input id="password" name="password" value="${user.password}" placeholder="Your Password" type="password"/>
            </td>
            <td>
                <span id="passwordError" style="color: red;"></span>
                </td>
        </tr>
        <tr align="center">
            <td align="left">
                <label for="name">Full Name</label>
            </td>
            <td>
                <input id="name" name="name" value="${user.name}" placeholder="Your Full Name"  type="text"/>
            </td>
            <td>
                <span id="nameError" style="color: red;"></span>
            </td>
        </tr>
        <tr align="center">
            <td align="left">
                <label for="dob">Date Of Birth</label>
            </td>
            <td align="left" colspan="22">
                <input id="dob" name="dateOfBirth" value="${user.dateOfBirth}" placeholder="Your Date Of Birth" type="date"/>
            </td>
            <td>
                <span id="dobError" style="color: red;"></span>
            </td>
        </tr>
        <tr>
            <td >
                <label for="choice">Choose a Role: </label>
                </td>
            <td colspan="200">
                <select id="choice" name="role" required>
                    <option value="AGENT" ${user.role == 'AGENT' ? 'selected' : ''}>AGENT</option>
                    <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>USER</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label for="choice1">Choose a BloodGroup</label>
            </td>
            <td align="left" colspan="200">
                <select name="bloodGroup" id="choice1">
                    <option value="A+" ${user.bloodGroup == 'A+' ? 'selected' : ''}>A+</option>
                    <option value="A-" ${user.bloodGroup == 'A-' ? 'selected' : ''}>A-</option>
                    <option value="B+" ${user.bloodGroup == 'B+' ? 'selected' : ''}>B+</option>
                    <option value="B-" ${user.bloodGroup == 'B-' ? 'selected' : ''}>B-</option>
                    <option value="AB+" ${user.bloodGroup == 'AB+' ? 'selected' : ''}>AB+</option>
                    <option value="AB-" ${user.bloodGroup == 'AB-' ? 'selected' : ''}>AB-</option>
                    <option value="O+" ${user.bloodGroup == 'O+' ? 'selected' : ''}>O+</option>
                    <option value="O-" ${user.bloodGroup == 'O-' ? 'selected' : ''}>O-</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Enter The Security Code for password reset: <input name="securityCode" placeholder="your security code" type="text" required/>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <div align="center">
        <button type="submit">Sign UP</button>
        <br>
        <h2>
            If Existing User
        </h2>
        <br>
    </div>
</form>
<div align="center">
    <form action="/bloodbank/auth/login" method="get">
        <button type="submit">Login</button>
    </form>
</div>
<div align="center">
<br>
<form action="/bloodbank/auth/home" method="get">
    <button type="submit">Home</button>
</form>
</div>
<br>
<div align="center">
    <p style="color: red;">${error}</p>
</div>

<script>
    function validateForm() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var name = document.getElementById('name').value;
    var dob = document.getElementById('dob').value;
    var email = document.getElementById('email').value;

    if (username === '') {
        document.getElementById('usernameError').textContent = 'Username cannot be empty';
        return false;
    }

    if (password === '') {
        document.getElementById('passwordError').textContent = 'Password cannot be empty';
        return false;
    }

    if (name === '') {
        document.getElementById('nameError').textContent = 'The name field should not be empty';
        return false;
    }
    if (dob === '') {
        document.getElementById('dobError').textContent = 'Date field must be entered';
        return false;
    }
    if (email === '') {
        document.getElementById('emailError').textContent = 'Email should not be empty';
        return false;
    } else {
        var regex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})*$/;
        if (!regex.test(email)) {
            document.getElementById('emailError').textContent = 'Email is in wrong format';
            return false;
        }
    }

    return true;
}
    document.getElementById('myForm').onsubmit = function() {
    return validateForm();
}
</script>
</body>
</html>