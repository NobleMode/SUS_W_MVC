<%-- 
    Document   : ShowCart
    Created on : Feb 23, 2024, 4:13:18 PM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.EmployeeCart, java.util.Enumeration" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <caption>SHOW EMPLOYEE CART</caption>
            <tr>
                <th>SSN</th>
                <th>FName</th>
                <th>LName</th>
                <th>quantity</th>
                <th>Salary</th>
                <th>SubTotal</th>
                <th>remove</th>
            </tr>
            <% Enumeration em = session.getAttributeNames();
               EmployeeCart emp;
               double total = 0;
               double subTotal;
               while(em.hasMoreElements()) {
                     String key = em.nextElement().toString();
                     if(!key.equals("UserLogin")) {
                         emp = (EmployeeCart) session.getAttribute(key);
                         subTotal = emp.getSalary() * emp.getQuantity();
                         total += subTotal;
                     
                     
            %>
            <tr>
                <td><%=emp.getSSN()%></td>
                <td><%=emp.getFName()%></td>
                <td><%=emp.getLName()%></td>
                <td><input type="text" value="<%=emp.getQuantity()%>"></input></td>
                <td><%=emp.getSalary()%></td>
                <td><%=subTotal%></td>
                <td><a href="CartController?service=remove&ssn=<%=emp.getSSN()%>" />remove</td>
            </tr>
            <%}%>
            <%}%>
        </table>

        <p><a href="">Update</a></p>
        <p><a href="CartController?service=removeall">Remove All</a></p>
        <p>Total = <%=total%></p>
        <p><a href="EmployeeController">Display Employee</a></p>
    </body>
</html>
