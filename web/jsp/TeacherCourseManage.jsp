<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.teacherCourseManage,java.util.Vector"%>
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
    <div class="title"> SSU University - Teacher Course Page </div>

    <div class="body">

        <jsp:include page="Menu.jsp" />

        <p><a href="TeacherCourseControllerURL?service=listAll">Show All Teacher</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>Teacher ID</th>
                <th>Course 1 ID</th>
                <th>Course 2 ID</th>
                <th>Course 3 ID</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <% 
            Vector<teacherCourseManage> vector = (Vector<teacherCourseManage>)request.getAttribute("data");
            for (teacherCourseManage temp : vector) {
             %>
            <tr>
                <td><%= temp.getTeacherID() %></td>
                <td><%= temp.getCourseID1() != null ? temp.getCourseID1() : "Not set" %></td>
                <td><%= temp.getCourseID2() != null ? temp.getCourseID2() : "Not set" %></td>
                <td><%= temp.getCourseID3() != null ? temp.getCourseID3() : "Not set" %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="TeacherCourseControllerURL?service=update&ssn=<%= temp.getTeacherID() %>">update</a></td>
                    <td class="table-danger"><a href="TeacherCourseControllerURL?service=delete&ssn=<%= temp.getTeacherID() %>">delete</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="TeacherCourseControllerURL?service=listAll" method="post">
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