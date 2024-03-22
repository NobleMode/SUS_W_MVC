<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.studentCourse,java.util.Vector"%>
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
    <div class="title"> SSU University - Student Course Page </div>

    <div class="body">

        <jsp:include page="Menu.jsp" />

        <p><a href="studentCourseControllerURL?service=listAll">Show All Student</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>Student ID</th>
                <th>Course 1 ID</th>
                <th>Course 1 Score</th>
                <th>Course 2 ID</th>
                <th>Course 2 Score</th>
                <th>Course 3 ID</th>
                <th>Course 3 Score</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <% 
            Vector<studentCourse> vector = (Vector<studentCourse>)request.getAttribute("data");
            for (studentCourse temp : vector) {
             %>
            <tr>
                <td><%= temp.getStudentID() %></td>
                <td><%= temp.getCourseID1() != null ? temp.getCourseID1() : "Not set" %></td>
                <td><%= temp.getScore1() != 0.0 ? temp.getScore1() : "" %></td>
                <td><%= temp.getCourseID2() != null ? temp.getCourseID2() : "Not set" %></td>
                <td><%= temp.getScore2() != 0.0 ? temp.getScore2() : "" %></td>
                <td><%= temp.getCourseID3() != null ? temp.getCourseID3() : "Not set" %></td>
                <td><%= temp.getScore3() != 0.0 ? temp.getScore3() : "" %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="studentCourseControllerURL?service=update&ssn=<%= temp.getStudentID() %>">update</a></td>
                    <td class="table-danger"><a href="studentCourseControllerURL?service=delete&ssn=<%= temp.getStudentID() %>">delete</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="studentCourseControllerURL?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH Student ID: </td>
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