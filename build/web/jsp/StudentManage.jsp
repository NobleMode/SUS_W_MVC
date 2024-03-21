<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Student,java.util.Vector"%>
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

        <p><a href="StudentControllerURL?service=listAll">Show All Student</a></p>
        <table class="table table-striped">
            <tr class="active">
                <th>SID</th>
                <th>FName</th>
                <th>LName</th>
                <th>Gender</th>
                <th>DOB</th>
                <th>Major</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Tutor Fees</th>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Cart</th>
                </c:if>
                
            </tr>
            <% 
            Vector<Student> vector = (Vector<Student>)request.getAttribute("data");
            for (Student temp : vector) {
             %>
            <tr>
                <td><%= temp.getSID() %></td>
                <td><%= temp.getFname() %></td>
                <td><%= temp.getLname() %></td>
                <td><%= temp.isGender() == true ? "male":"female" %></td>
                <td><%= temp.getDob() %></td>
                <td><%= temp.getMajor() %></td>
                <td><%= temp.getAddress() %></td>
                <td><%= temp.getPhone() %></td>
                <td><%= temp.getEmail() %></td>
                <td><%= temp.getFee() %></td>
                <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                    <td class="table-success"><a href="StudentControllerURL?service=update&ssn=<%= temp.getSID() %>">update</a></td>
                    <td class="table-danger"><a href="StudentControllerURL?service=delete&ssn=<%= temp.getSID() %>">delete</a></td>
                    <td class="table-info"><a href="CartController?service=addtocart&ssn=<%= temp.getSID() %>">add</a></td>
                 </c:if>
            </tr>
            <% } %>
        </table>
        <form action="StudentControllerURL?service=listAll" method="post">
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