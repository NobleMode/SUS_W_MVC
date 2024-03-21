
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
        <h1>UPDATE NEW Teacher</h1>


        <jsp:include page="Menu.jsp"/>
        <% Vector<Teacher> result = (Vector<Teacher>)request.getAttribute("data");
          Teacher teach=result.get(0);
          DAOTeacher daoteach = new DAOTeacher();
          Vector<Teacher> teachVec = daoteach.getAll("select * from Teacher");
                                   
        %>
        <form action="" method="post">
            <table>
                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" value="<%=teach.getFname()%>" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" value="<%=teach.getLname()%>" required></td>
                </tr>

                <tr>
                    <td>Gender</td>
                    <td><input type="radio" name="Sex"  value="1" <%=(teach.isGender()==true? "checked":"")%>>Male
                    <input type="radio" name="Sex" value="0" <%=(teach.isGender()==false? "checked":"")%>>Female</td>
                </tr>

                <tr>
                    <td>DOB (YYYY-MM-DD)</td>
                    <td><input type="text" name="DOB" value="<%=teach.getDob()%>" required></td>
                </tr>

                <tr>
                    <td>Pos</td>
                    <td>
                        <select name="Pos">
                            <option value="Tech Assist" <%=(teach.getPosition()=="Tech Assist"? "selected":"")%>>Tech Assist</option>
                            <option value="Teacher" <%=(teach.getPosition()=="Teacher"? "selected":"")%>>Teacher</option>
                            <option value="Subsitute" <%=(teach.getPosition()=="Subsitute"? "selected":"")%>>Subsitute</option>
                            <option value="Professor" <%=(teach.getPosition()=="Professor"? "selected":"")%>>Professor</option>
                            <option value="VP" <%=(teach.getPosition()=="VP"? "selected":"")%>>Vice President</option>
                            <option value="President" <%=(teach.getPosition()=="President"? "selected":"")%>>President</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" value="<%=teach.getAddress()%>" required></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" id="phone" name="Phone" pattern="[0-9]{10}" value="<%=teach.getPhone()%>" required></td>
                </tr>

                <tr>
                    <td>Salary</td>
                    <td><input type="number" name="Salary" value="<%=teach.getSalary()%>" required></td>
                </tr>

                <td><input type="text" name="TID" value="<%=teach.getTID()%>" readonly required></td>
                <td><input type="text" name="Email" value="<%=teach.getEmail()%>" readonly required></td>
                <td><input type="text" name="Password" value="<%=teach.getPassword()%>" readonly required></td>


                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>

                <td>
                    <input type="hidden" name="service" value="update">
                </td>
            </table>
        </form>
        <%-- <%
            if(request.getParameter("submit") != null) {

                String id = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(0,1).toUpperCase()
                            + request.getParameter("DOB").substring(2, 4) + request.getParameter("Phone").substring(6,10) ;

                String email = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase() 
                                + request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase()
                                + "@teach.ssu.edu.com";

                String password = request.getParameter("FName") + request.getParameter("DOB").substring(0, 4);

                String fname = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase();
                String lname = request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase();

                DAOTeacher daoteach = new DAOTeacher();
                Teacher teach = new Teacher(id, fname, lname, 
                            request.getParameter("Sex").equals("1") ? true : false, request.getParameter("DOB"), request.getParameter("Major"),  
                            request.getParameter("Address"), Integer.parseInt(request.getParameter("Phone")), email, password,
                            Integer.parseInt(request.getParameter("Fee")));
                daoteach.addTeacher(teach);
                response.sendRedirect("TeacherControllerURL");
            }
        %>                     --%>
    </body>
</html>
