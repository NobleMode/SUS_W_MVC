<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Bill,java.util.Vector"%>
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
<div class="title"> SSU University - Bill Page </div>

<div class="body">

    <p><a href="index.jsp">Home</a></p>

    <p><a href="BillControllerURL?service=listAll">Show All Bill</a></p>
    <table class="table table-striped">
        <tr class="active">
            <th>SID</th>
            <th>TID</th>
            <th>TutorFee</th>
            <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                <th>Delete</th>
            </c:if>
        </tr>
        <%
            Vector<Bill> vector = (Vector<Bill>)request.getAttribute("data");
            for (Bill temp : vector) {
        %>
        <tr>
            <td><%= temp.getStudentid()%></td>
            <td><%= temp.getTeacherid()%></td>
            <td><%= temp.getTutorfee()%></td>
            <c:if test="${sessionScope.UserLogin.isIsAdmin()}">
                <td class="table-danger"><a href="CartControllerURL?service=delete&ssn=<%= temp.getStudentid() %>">delete</a></td>
            </c:if>
        </tr>
        <% } %>
    </table>
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