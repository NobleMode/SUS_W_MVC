<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.StudentDep,java.util.Vector"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="title"> SSU University - Student Page </div>

    <div class="body">

        <jsp:include page="Menu.jsp" />

        <p><a href="StudentDepControllerURL?service=listAll">Show All Student</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>SID</th>
                <th>FName</th>
                <th>LName</th>
                <th>Relation</th>
                <th>Address</th>
                <th>Phone</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <% 
            Vector<StudentDep> vector = (Vector<StudentDep>)request.getAttribute("data");
            for (StudentDep temp : vector) {
             %>
            <tr>
                <td><%= temp.getStudentID() %></td>
                <td><%= temp.getFname() != null ? temp.getFname():"" %></td>
                <td><%= temp.getLname() != null ? temp.getLname():"" %></td>
                <td><%= temp.getRelation() %></td>
                <td><%= temp.getAddress() %></td>
                <td><%= temp.getPhone() != 0 ? temp.getPhone():"" %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="StudentDepControllerURL?service=update&ssn=<%= temp.getStudentID() %>">update</a></td>
                    <td class="table-danger"><a href="StudentDepControllerURL?service=delete&ssn=<%= temp.getStudentID() %>">delete</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="StudentDepControllerURL?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH SID: </td>
                    <td><input type="text" name="sid" required></td>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <td><input type="hidden" name="option" value="searchfname"></td>
                </tr>
            </table>
        </form>
    </div>
</body>

<style>
    .body {
        width: 90%;
        margin: 0.5em auto;
    }

    .title {
       text-align: center;
       font-size: 30px; 
    }
</style>
</html>