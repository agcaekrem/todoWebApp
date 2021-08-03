<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation_login.jspf"%>

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
    <title>Log in with your account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" type="text/css"">

</head>

<body>
<center>
    <div class="container">
        <%--@elvariable id="loginBean" type=""--%>
        <form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <h4 class="form-signin-heading">Log In</h4>
                <form:input id="username" name="username" path="username" placeholder="Email" style="width:300px;"/><br/><br/>
                <form:password id="password" name="password" path="password" placeholder='Password' style="width:300px;"/><br/><br/>
                <input type="submit" class="btn btn-success" value="Submit" />
            </div>
        </form:form>
        <a type="button" href="/forgot-password">Forgot Password</a>
    </div>
</center>
</body>
</html>
<%@ include file="common/footer_out.jspf"%>