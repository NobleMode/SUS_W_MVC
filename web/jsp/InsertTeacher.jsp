<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Teacher, model.DAOTeacher, java.util.Vector" %>
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
        <form action="" method="post">
            <table>
                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" required></td>
                </tr>

                <tr>
                    <td>Gender</td>
                    <td><input type="radio" name="Sex" checked value="1">Male
                        <input type="radio" name="Sex" value="0">Female</td>
                </tr>

                <tr>
                    <td>DOB (YYYY-MM-DD)</td>
                    <td><input type="text" name="DOB" required></td>
                </tr>

                <tr>
                    <td>Major</td>
                    <td>
                        <select name="Pos">
                            <option value="Tech Assist">Tech Assist</option>
                            <option value="Teacher">Teacher</option>
                            <option value="Subsitute">Subsitute</option>
                            <option value="Professor">Professor</option>
                            <option value="VP">Vice President</option>
                            <option value="President">President</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" required></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" id="phone" name="Phone" pattern="[0-9]{10}" required></td>
                </tr>

                <tr>
                    <td>Salary</td>
                    <td><input type="number" name="Salary" required></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>


            </table>
        </form>
        <%
            if(request.getParameter("submit") != null) {

                String id = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(0,1).toUpperCase()
                            + request.getParameter("DOB").substring(2, 4) + request.getParameter("Phone").substring(6,10) ;

                String email = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase() 
                                + request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase()
                                + "@staff.ssu.edu.com";

                String password = request.getParameter("FName") + request.getParameter("DOB").substring(0, 4);

                String fname = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase();
                String lname = request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase();

                DAOTeacher daoStu = new DAOTeacher();
                Teacher stu = new Teacher(id, fname, lname, 
                            request.getParameter("Sex").equals("1") ? true : false, request.getParameter("DOB"), request.getParameter("Pos"),  
                            request.getParameter("Address"), Integer.parseInt(request.getParameter("Phone")), email, password,
                            Integer.parseInt(request.getParameter("Salary")));
                daoStu.addTeacher(stu);
                response.sendRedirect("TeacherControllerURL");
            }
        %>                      
    </body>
</html>
