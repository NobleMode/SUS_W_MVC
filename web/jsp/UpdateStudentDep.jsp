
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Student, model.DAOStudent, entity.StudentDep, model.DAOStuDep, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>

    <script>
        function restrictInput(inputElement) {
            inputElement.addEventListener('input', function(event) {
                if (inputElement.value.length > 10) {
                    inputElement.value = inputElement.value.slice(0, 10);
                }
            });
        }
        window.onload = function() {
            var phoneInput = document.getElementById('phone');
            restrictInput(phoneInput);
        };
    </script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<h1>INSERT NEW Student</h1>

<jsp:include page="Menu.jsp"/>
<% Vector<StudentDep> result = (Vector<StudentDep>) request.getAttribute("data");
    StudentDep stu = result.get(0);
    DAOStuDep sd = new DAOStuDep();
    Vector<StudentDep> stuVec = sd.getAll("select * from StudentDep");
    DAOStudent daoStu = new DAOStudent();
    Vector<Student> sVec = daoStu.getAll("select * from Student");

%>
<form action="" method="post">
    <table>
        <tr>
            <td>Student Name</td>
            <td>
                <select name="StudentID">
                    <% for(Student stud : sVec) {%>
                    <option readonly value="<%=stud.getSID()%>" <%=(stu.getStudentID().equals(stud.getSID())? "selected":"disabled")%>><%=stud.getFname()%> <%=stud.getLname()%></option>
                    <%}%>
                </select>
            </td>
        </tr>

        <tr>
            <td>FName</td>
            <td><input type="text" name="fname" value="<%=stu.getFname() != null ? stu.getFname() : null%>" required></td>
        </tr>
        <tr>
            <td>LName</td>
            <td><input type="text" name="lname" value="<%=stu.getLname() != null ? stu.getLname() : null%>" required></td>
        </tr>

        <tr>
            <td>Relation</td>
            <td><input type="text" name="relation" value="<%=stu.getRelation()%>" required></td>
        </tr>

        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value="<%=stu.getAddress()%>" required></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="number" id="phone" name="phone" pattern="[0-9]{10}" value="<%=stu.getPhone()%>" required></td>
        </tr>

        <tr>
            <td><input type="submit" name="submit" value="submit"></td>
            <td><input type="reset" value="clear"></td>
            <input type="hidden" name="service" value="update">
        </tr>


    </table>
</form>
<%--<%--%>
<%--    if(request.getParameter("submit") != null) {--%>

<%--        StudentDep st = new StudentDep(request.getParameter("id"), request.getParameter("FName"), request.getParameter("LName"), request.getParameter("Relation"), request.getParameter("Address"), Integer.parseInt(request.getParameter("Phone")));--%>
<%--        response.sendRedirect("StudentControllerURL");--%>
<%--    }--%>
<%--%>--%>
</body>
</html>
