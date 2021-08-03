<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Add TODO</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="50%">Description</th>
                    <th width="20%">Start Date</th>
                    <th width="20%">Target Date</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <form:form action="/add-todo" modelAttribute="todo" method="POST">
                        <td width="50%"> <form:input path="workDescription" /></td>
                        <td width="20%"> <form:input path="startDate"/> </td>
                        <td width="20%"> <form:input path="targetDate"/> </td>


                        <td><input type="submit" value="Submit" class="btn btn-success"/></td>
                    </form:form>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>