
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Course, model.DAOCourse, entity.Major, model.DAOMajor, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>
        function restrictInput(inputElement) {
            inputElement.addEventListener('input', function(event) {
                if (inputElement.value.length > 5) {
                    inputElement.value = inputElement.value.slice(0, 5);
                }
            });
        }
        window.onload = function() {
            var phoneInput = document.getElementById('id');
            restrictInput(phoneInput);
        };
        </script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1>INSERT NEW Student</h1>

        <%
         Vector<Course> result = (Vector<Course>) request.getAttribute("data");
         Course c = result.get(0);
         DAOMajor m = new DAOMajor();
         Vector<Major> vMajor = m.getAll("select * from Major");
         DAOCourse dc = new DAOCourse();
         Vector<Course> vCourse = dc.getAll("select * from Course");
        %>
        <form action="" method="post">
            <table>
                <tr>
                    <td>Course ID</td>
                    <td><input type="number" name="CID" id="id" value="<%=c.getCID()%>" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=c.getName()%>" required></td>
                </tr>

                <tr>
                    <td>Major</td>
                    <td>
                        <select name="Major">
                            <% for(Major major : vMajor) {%>
                            <option value="<%=major.getMID()%>" <%=(c.getMajor().equals(major.getMID())? "selected":"")%>><%=major.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>


            </table>
        </form>
        <%
            if(request.getParameter("submit") != null) {

                DAOCourse daoCourse = new DAOCourse();
                Course stu = new Course(request.getParameter("CID"), request.getParameter("Name"), request.getParameter("Major"));
                daoCourse.addCourse(stu);
                response.sendRedirect("StudentControllerURL");
            }
        %>                    
    </body>
</html>
