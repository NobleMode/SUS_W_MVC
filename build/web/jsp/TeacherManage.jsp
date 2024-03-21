<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Teacher,java.util.Vector"%>
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


    <jsp:include page="Menu.jsp" />
    <div class="body">
        <p><a href="TeacherControllerURL?service=listAll">Show All Teacher</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>TID</th>
                <th>FName</th>
                <th>LName</th>
                <th>Gender</th>
                <th>DOB</th>
                <th>Position</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Salary</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Cart</th>
                </c:if>
            </tr>
            <% 
            Vector<Teacher> vector = (Vector<Teacher>)request.getAttribute("data");
            for (Teacher temp : vector) {
             %>
            <tr>
                <td><%= temp.getTID() %></td>
                <td><%= temp.getFname() %></td>
                <td><%= temp.getLname() %></td>
                <td><%= temp.isGender() == true ? "male":"female" %></td>
                <td><%= temp.getDob() %></td>
                <td><%= temp.getPosition() %></td>
                <td><%= temp.getAddress() %></td>
                <td><%= temp.getPhone() %></td>
                <td><%= temp.getEmail() %></td>
                <td><%= temp.getSalary() %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="TeacherControllerURL?service=update&ssn=<%= temp.getTID() %>">update</a></td>
                    <td class="table-danger"><a href="TeacherControllerURL?service=delete&ssn=<%= temp.getTID() %>">delete</a></td>
                    <td class="table-info"><a href="CartController?service=cart&ssn=<%= temp.getTID() %>">Cart</a></td>
                </c:if>
            </tr>
            <% } %>
        </table>
        <form action="TeacherControllerURL?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH FNAME: </td>
                    <td><input type="text" name="fname" required></td>
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