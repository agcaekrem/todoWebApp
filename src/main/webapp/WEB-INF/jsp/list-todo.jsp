<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<head>
    <style>
        div.panel-body{
            margin-top: 4px;
            margin-right: 4px;
            margin-left: 4px;
            margin-bottom: 4px;
            padding:4px;
            background-color: white;
            width: 100%;
            height: 280px;
            overflow-x: hidden;
            overflow-x: auto;
            text-align:justify;
        }
    </style>
</head>
<div class="container">
    <div><br/>
        <a type="button" class="btn btn-success" href="/add-todo">Add Todo</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>List of TODOs</h3>
        </div>
        <div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="4%">ID</th>
                    <th width="50%">Description</th>
                    <th width="12%">Start Date</th>
                    <th width="12%">Target Date</th>
                    <th width="10%">Update</th>
                    <th width="10%">Delete</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <tbody>
                <c:forEach var="todo" items="${todo}">
                    <tr>
                        <td width="4%">${todo.userId}</td>
                        <td width="50%">${todo.workDescription}</td>
                        <td width="12%"><fmt:formatDate value="${todo.startDate}"
                                                        pattern="dd/MM/yyyy"/></td>
                        <td width="12%"><fmt:formatDate value="${todo.targetDate}"
                                                        pattern="dd/MM/yyyy"/></td>
                        <td width="10%"><a type="button" class="btn btn-success"
                                           href="/update-todo/${todo.userId}">Update</a></td>
                        <td width="10%"><a type="button" class="btn btn-warning"
                                           href="/delete-todo?id=${todo.userId}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>