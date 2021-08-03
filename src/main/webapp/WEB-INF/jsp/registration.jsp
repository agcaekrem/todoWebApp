<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation_register.jspf"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <style>
        .container{
            margin: 0;
            position: absolute;
            top: 40%;
            left: 5%;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }
    </style>

    <meta charset="utf-8">
    <title>Create an account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" type="text/css"">
</head>
<body>
<div class="container">
    <center><%--@elvariable id="userForm" type=""--%>
    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h4 class="form-signin-heading">Create your account</h4>
        <spring:bind path="firstname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" style="width:300px;" path="firstname" class="form-control" placeholder="Firstname"
                            autofocus="true"></form:input>
                <form:errors path="firstname"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="email" style="width:300px;" path="username" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" style="width:300px;" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" style="width:300px;" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>
        <button class="btn btn-success" type="submit">Register</button>
    </form:form>
        <a type="button" href="/forgot-password">Forgot Password</a>
    </center>
</div>
</body>
</html>
<%@ include file="common/footer_out.jspf"%>