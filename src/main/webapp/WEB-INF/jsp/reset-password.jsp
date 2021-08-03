<%--suppress ALL --%>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation_login.jspf"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Forgot Password</title>
</head>
<body>
<div class="container">
    <center>
        <!--/*@thymesVar id="passwordResetForm" type=""*/-->
        <form th:action="@{/reset-password}" th:object="${passwordResetForm}" method="post">
            <h4>Reset password</h4>
            <input type="hidden" name="token" th:value="${reset}"/>

            <input id="password" style="width:300px;"
                   class="form-control"
                   placeholder="password"
                   type="password"
                   th:field="*{password}"/><br/>

            <input id="confirmPassword" style="width:300px;"
                   class="form-control"
                   placeholder="Confirm password"
                   type="password"
                   th:field="*{confirmPassword}"/><br/>

            <button type="submit" class="btn btn-success">Reset password</button>
        </form>
        <a href="/" th:href="@{/login}">Login</a>
    </center>
</div>
</body>
</html>