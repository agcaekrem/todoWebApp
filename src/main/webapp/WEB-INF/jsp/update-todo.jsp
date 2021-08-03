<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Update TODO</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="10%">ID</th>
                    <th width="30%">Description</th>
                    <th width="30%">Start Date</th>
                    <th width="30%">Target Date</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <form:form action = "/update-todo" modelAttribute="todo" method="POST">
                        <td> <form:input path="userId" readonly="true"/> </td>
                        <td> <form:input path="workDescription"/> </td>
                        <td> <form:input path="startDate"/> </td>
                        <td> <form:input path="targetDate"/> </td>

                        <td><button type="submit" class="btn btn-success">Update</button></td>
                    </form:form>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>