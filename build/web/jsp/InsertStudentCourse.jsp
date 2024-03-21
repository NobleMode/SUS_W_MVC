
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Student, model.DAOStudent, entity.studentCourse, model.DAOStuCourse,entity.Course, model.DAOCourse, java.util.Vector" %>
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
        <form action="" method="post">
            <table>
                <tr>
                    <td>Student Name</td>
                    <td>
                        <select name="id">
                            <% DAOStudent dao = new DAOStudent();
                                Vector<Student> v = dao.getAll("select * from Student"); %>
                            <c:forEach items="<%=v%>" var="stu">
                                <option value="${stu.getSID()}">${stu.getFname()} ${stu.getLname()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Course 1</td>
                    <td>
                        <select name="course1">
                            <% DAOCourse daoC1 = new DAOCourse();
                                Vector<Course> c1 = daoC1.getAll("select * from Course"); %>
                            <c:forEach items="<%=c1%>" var="c1">
                                <option value="${c1.getCID()}">${c1.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Score 1</td>
                    <td><input type="number" name="score1" required></td>
                </tr>

                <tr>
                    <td>Course 2</td>
                    <td>
                        <select name="course2">
                            <% DAOCourse daoC2 = new DAOCourse();
                                Vector<Course> c2 = daoC2.getAll("select * from Course"); %>
                            <c:forEach items="<%=c2%>" var="c2">
                                <option value="${c2.getCID()}">${c2.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Score 2</td>
                    <td><input type="number" name="score2" required></td>
                </tr>

                <tr>
                    <td>Course 3</td>
                    <td>
                        <select name="course3">
                            <% DAOCourse daoC3 = new DAOCourse();
                                Vector<Course> c3 = daoC3.getAll("select * from Course"); %>
                            <c:forEach items="<%=c3%>" var="c3">
                                <option value="${c3.getCID()}">${c3.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Score 3</td>
                    <td><input type="number" name="score3" required></td>
                </tr>


            </table>
        </form>
        <%
            if(request.getParameter("submit") != null) {

                DAOStuCourse daosc = new DAOStuCourse();

                studentCourse sc = new studentCourse(request.getParameter("id"), request.getParameter("course1"), request.getParameter("score1") != null ? Float.parseFloat(request.getParameter("score1")) : 0.0, request.getParameter("course2"), Float.parseFloat(request.getParameter("score2")), request.getParameter("course3"), Float.parseFloat(request.getParameter("score3")));

                daosc.addStudentCourse(sc);
                response.sendRedirect("StudentCourseControllerURL");
            }
        %>                    
    </body>
</html>
