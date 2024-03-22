<%-- 
    Document   : ShowCart
    Created on : Feb 23, 2024, 4:13:18 PM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.StudentCart, java.util.Enumeration" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CartController" method="">
            <table border="1">
                <caption>SHOW STUDENT CART</caption>
                <tr>
                    <th>SID</th>
                    <th>FNAME</th>
                    <th>LNAME</th>
                    <th>HOURPERDAY</th>
                    <th>FEE</th>
                    <th>SubTotal</th>
                    <th>remove</th>
                </tr>
                <% Enumeration em = session.getAttributeNames();
                   StudentCart stu;
                   double total = 0;
                   double subTotal;
                   while(em.hasMoreElements()) {
                         String key = em.nextElement().toString();
                         if(!key.equals("UserLogin")) {
                             stu = (StudentCart) session.getAttribute(key);
                             subTotal = stu.getFee() * stu.getHourPerDay();
                             total += subTotal;
                     
                     
                %>
                <tr>
                    <td><%=stu.getSID()%></td>
                    <td><%=stu.getFname()%></td>
                    <td><%=stu.getLname()%></td>
                    <td><input type="text" value="<%=stu.getHourPerDay()%>" name="<%=stu.getSID()+"hourperday"%>"></input></td>
                    <td><%=stu.getFee()%></td>
                    <td><%=subTotal%></td>
                    <td><a href="CartController?service=remove&sid=<%=stu.getSID()%>" />remove</td>
                </tr>
                <%}%>
                <%}%>
            </table>
            <input type="hidden" name="service" value="update">
            <input type="submit" name="submit" value="Update">
        </form>
        <p><a href="CartController?service=removeall">Remove All</a></p>
        <p>Total = <%=total%></p>
        <p><a href="StudentControllerURL">Display All Students</a>
        <a href="CartController?service=checkout">Check out</a></p>
    </body>
</html>
