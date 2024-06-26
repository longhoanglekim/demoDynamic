<%--
  Created by IntelliJ IDEA.
  User: HD
  Date: 26/06/2024
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Find Account</title>
</head>
<body>
    <form action="find-account-servlet" method="post">
        <table>
            <tr>
                <td>Account number:</td>
                <td><input type="text" name="accno"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Find Account"></td>
            </tr>
        </table>
    </form>
    <%-- Check if account is found and account details are not empty --%>
    <%-- Check if account is found and account number is not empty --%>
    <c:if test="${not empty requestScope.account and not empty requestScope.account.accno}">
        <h1>Account Details</h1>
        <table>
            <tr>
                <td>Account number:</td>
                <td>${account.accno}</td>
            </tr>
            <tr>
                <td>First name:</td>
                <td>${account.firstname}</td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td>${account.lastname}</td>
            </tr>
            <tr>
                <td>Balance:</td>
                <td>${account.bal}</td>
            </tr>
        </table>
    </c:if>
</body>
</html>
