<%--
  Created by IntelliJ IDEA.
  User: HD
  Date: 25/06/2024
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
    <h1>Create Account</h1>
    <form action="create-account-servlet" method="post">
        <span class="input-description">First name:</span> <input type="text" name="firstname"><br>
        <span class="input-description">Last name:</span> <input type="text" name="lastname"><br>
        <span class="input-description">Balance:</span> <input type="text" name="balance"><br>
        <input type="submit" value="Create Account">
    </form>
</body>
</html>
