
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Student, model.DAOStudent, entity.Major, model.DAOMajor, java.util.Vector" %>
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
        <h1>UPDATE NEW Student</h1>


        <jsp:include page="Menu.jsp"/>
        <% Vector<Student> result = (Vector<Student>)request.getAttribute("data");
          Student stu=result.get(0);
          DAOMajor daoMajor = new DAOMajor();
          Vector<Major> mVec = daoMajor.getAll("select * from Major");
          DAOStudent daoStu = new DAOStudent();
          Vector<Student> stuVec = daoStu.getAll("select * from Student");
                                   
        %>
        <form action="StudentControllerURL" method="post">
            <table>
                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" value="<%=stu.getFname()%>" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" value="<%=stu.getLname()%>" required></td>
                </tr>

                <tr>
                    <td>Gender</td>
                    <td><input type="radio" name="Sex"  value="1" <%=(stu.isGender()==true? "checked":"")%>>Male
                    <input type="radio" name="Sex" value="0" <%=(stu.isGender()==false? "checked":"")%>>Female</td>
                </tr>

                <tr>
                    <td>DOB (YYYY-MM-DD)</td>
                    <td><input type="text" name="DOB" value="<%=stu.getDob()%>" required></td>
                </tr>

                <tr>
                    <td>Major</td>
                    <td>
                        <select name="Major">
                            <% for(Major major : mVec) {%>
                            <option value="<%=major.getMID()%>" <%=(stu.getMajor().equals(major.getMID())? "selected":"")%>><%=major.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" value="<%=stu.getAddress()%>" required></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" id="phone" name="Phone" pattern="[0-9]{10}" value="<%=stu.getPhone()%>" required></td>
                </tr>

                <tr>
                    <td>Fee</td>
                    <td><input type="number" name="Fee" value="<%=stu.getFee()%>" required></td>
                </tr>
                
                <td><input type="text" name="SID" value="<%=stu.getSID()%>" readonly required></td>
                <td><input type="text" name="Email" value="<%=stu.getEmail()%>" readonly required></td>
                <td><input type="text" name="Password" value="<%=stu.getPassword()%>" readonly required></td>

                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <input type="hidden" name="service" value="update">
                </tr>
            </table>
        </form>
        <%-- <%
            if(request.getParameter("submit") != null) {

                String id = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(0,1).toUpperCase()
                            + request.getParameter("DOB").substring(2, 4) + request.getParameter("Phone").substring(6,10) ;

                String email = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase() 
                                + request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase()
                                + "@stu.ssu.edu.com";

                String password = request.getParameter("FName") + request.getParameter("DOB").substring(0, 4);

                String fname = request.getParameter("FName").substring(0,1).toUpperCase() + request.getParameter("FName").substring(1).toLowerCase();
                String lname = request.getParameter("LName").substring(0,1).toUpperCase() + request.getParameter("LName").substring(1).toLowerCase();

                DAOStudent daoStu = new DAOStudent();
                Student stu = new Student(id, fname, lname, 
                            request.getParameter("Sex").equals("1") ? true : false, request.getParameter("DOB"), request.getParameter("Major"),  
                            request.getParameter("Address"), Integer.parseInt(request.getParameter("Phone")), email, password,
                            Integer.parseInt(request.getParameter("Fee")));
                daoStu.addStudent(stu);
                response.sendRedirect("StudentControllerURL");
            }
        %>                     --%>
    </body>
</html>
