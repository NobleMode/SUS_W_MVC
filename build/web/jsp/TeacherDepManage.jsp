<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.TeacherDep,java.util.Vector"%>
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
    <div class="title"> SSU University - Teacher Page </div>

    <div class="body">

        <jsp:include page="Menu.jsp" />

        <p><a href="TeacherDepControllerURL?service=listAll">Show All Teacher</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>Teacher ID</th>
                <th>FName</th>
                <th>LName</th>
                <th>Relation</th>
                <th>Address</th>
                <th>Phone</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Cart</th>
                </c:if>
            </tr>
            <% 
            Vector<TeacherDep> vector = (Vector<TeacherDep>)request.getAttribute("data");
            for (TeacherDep temp : vector) {
             %>
            <tr>
                <td><%= temp.getTeacherID() %></td>
                <td><%= temp.getFname() != null ? temp.getFname() : "" %></td>
                <td><%= temp.getLname() != null ? temp.getLname() : "" %></td>
                <td><%= temp.getRelation() %></td>
                <td><%= temp.getAddress() %></td>
                <td><%= temp.getPhone() %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="TeacherDepControllerURL?service=update&ssn=<%= temp.getTeacherID() %>">update</a></td>
                    <td class="table-danger"><a href="TeacherDepControllerURL?service=delete&ssn=<%= temp.getTeacherID() %>">delete</a></td>
                    <td class="table-info"><a href="CartController?service=addtocart&ssn=<%= temp.getTeacherID() %>">add</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="TeacherDepControllerURL?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH Teacher ID: </td>
                    <td><input type="text" name="tid" required></td>
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