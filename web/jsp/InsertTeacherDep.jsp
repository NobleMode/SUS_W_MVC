
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Teacher, model.DAOTeacher, entity.TeacherDep, model.DAOTeacherDep, java.util.Vector" %>
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
        <h1>INSERT NEW Teacher</h1>

        <jsp:include page="Menu.jsp"/>
        <form action="" method="post">
            <table>
                <tr>
                    <td>Teacher Name</td>
                    <td>
                        <select name="id">
                            <% DAOTeacher dao = new DAOTeacher();
                                Vector<Teacher> v = dao.getAll("select * from Teacher"); %>
                            <c:forEach items="<%=v%>" var="teacher">
                                <option value="${teacher.getTID()}">${teacher.getFname()} ${teacher.getLname()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" required></td>
                </tr>

                <tr>
                    <td>Relation</td>
                    <td><input type="text" name="Relation" required></td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" required></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="number" id="phone" name="Phone" pattern="[0-9]{10}" required></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>


            </table>
        </form>
        <%
            if(request.getParameter("submit") != null) {

                String fname = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase();
                String lname = request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase();


                DAOTeacherDep daoTD = new DAOTeacherDep();
                TeacherDep td = new TeacherDep(request.getParameter("id"), fname, lname, request.getParameter("Relation"), request.getParameter("Address"), Integer.parseInt(request.getParameter("Phone")));
                daoTD.addTeacherDep(td);
                response.sendRedirect("TeacherControllerURL");
            }
        %>                    
    </body>
</html>
