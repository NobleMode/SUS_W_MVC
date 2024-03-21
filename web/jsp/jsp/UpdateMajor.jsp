
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Teacher, model.DAOTeacher, entity.Major, model.DAOMajor, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1>INSERT NEW Major</h1>

        <%
            Vector<Major> r = (Vector<Major>) request.getAttribute("data");
            Major m = r.get(0);
            DAOMajor dm = new DAOMajor();
            Vector<Major> vM = dm.getAll("select * from Major");
            DAOTeacher dao = new DAOTeacher();
            Vector<Teacher> v = dao.getAll("select * from Teacher");
        %>
        <form action="" method="post">
            <table>
                <tr>
                    <td>Major ID</td>
                    <td><input type="text" name="MID" value="<%=m.getMID()%>" required></td>
                </tr>

                <tr>
                    <td>Managed Teacher</td>
                    <td>
                        <select name="teacherID">
                            <%  %>
                            <c:forEach items="<%=v%>" var="teacher">
                                <option value="${teacher.getTID()}">${teacher.getFname()} ${teacher.getLname()}</option>
                            </c:forEach>

                            <% for(Teacher t : v) {%>
                            <option value="<%=t.getTID()%>" <%=(m.getTeacherID().equals(t.getTID())? "selected":"")%>><%=t.getFname() + t.getLname()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=m.getName()%>" required></td>
                </tr>

                <tr>
                    <td>Desc</td>
                    <td><textarea id="w3review" name="desc" rows="4" cols="50"><%=m.getDescription()%></textarea></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>


            </table>
        </form>
        <%
            if(request.getParameter("submit") != null) {

                DAOMajor daoCourse = new DAOMajor();
                Major stu = new Major(request.getParameter("MID"), request.getParameter("teacherID"),request.getParameter("Name"), request.getParameter("desc"));
                daoCourse.addMajor(stu);
                response.sendRedirect("MajorControllerURL");
            }
        %>                    
    </body>
</html>
