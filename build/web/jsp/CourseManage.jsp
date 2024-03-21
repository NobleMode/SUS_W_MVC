<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Course,java.util.Vector"%>
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
    <div class="title"> SSU University - Course Page </div>

    <div class="body">

        <jsp:include page="Menu.jsp" />

        <p><a href="CourseControllerURL?service=listAll">Show All Course</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>Course ID</th>
                <th>Name</th>
                <th>Major</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Cart</th>
                </c:if>
            </tr>
            <% 
            Vector<Course> vector = (Vector<Course>)request.getAttribute("data");
            for (Course temp : vector) {
             %>
            <tr>
                <td><%= temp.getCID() %></td>
                <td><%= temp.getName() %></td>
                <td><%= temp.getMajor() %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="CourseControllerURL?service=update&ssn=<%= temp.getCID() %>">update</a></td>
                    <td class="table-danger"><a href="CourseControllerURL?service=delete&ssn=<%= temp.getCID() %>">delete</a></td>
                    <td class="table-info"><a href="CartController?service=addtocart&ssn=<%= temp.getCID() %>">add</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="CourseControllerURL?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH NAME: </td>
                    <td><input type="text" name="name" required></td>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <td><input type="hidden" name="option" value="searchname"></td>
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