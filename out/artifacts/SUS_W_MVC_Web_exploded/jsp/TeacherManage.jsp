<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Teacher,java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" type="text/css" href=".../web/css/bootstrap/bootstrap.css">
    <script src=".../web/js/bootstrap/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href=".../web/css/page/teacher_manage.css">
</head>
<body>
    <div class="title"> SSU University </div>

    <div class="body">
        <table class="table">
            <caption>List all Teacher</caption>
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
            </tr>
            <% 
            Vector<Teacher> vector = (Vector<Teacher>)request.getAttribute("data");
            for (Teacher temp : vector) {
             %>
            <tr>
                <td><%= temp.getTID() %></td>
                <td><%= temp.getFname() %></td>
                <td><%= temp.getLname() %></td>
                <td><%= temp.isGender() %></td>
                <td><%= temp.getDob() %></td>
                <td><%= temp.getPosition() %></td>
                <td><%= temp.getAddress() %></td>
                <td><%= temp.getPhone() %></td>
                <td><%= temp.getEmail() %></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>