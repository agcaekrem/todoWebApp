<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation_register.jspf"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
<div align="center">
    <center>
        <form:form id="resetForm" action="forgot-password" method="post">
            <h4>Reset Your Password</h4>
            <table>
                <tr>
                    <td><input type="text" name="email" id="email" size="20" placeholder="Email" style="width:300px;"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button class="btn btn-success" type="submit">Reset Password</button>
                    </td>
                </tr>
            </table>
        </form:form>
    </center>
</div>
</body>
</html>
<%@ include file="common/footer_out.jspf"%>