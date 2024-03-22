/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.Student;
import entity.StudentCart;
import entity.Teacher;
import entity.UserLogin;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOBill;
import model.DAOStudent;
import model.DAOTeacher;

/**
 *
 * @author Duc Minh
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        DAOStudent dao = new DAOStudent();
        String service = request.getParameter("service");

        if (service.equals("addtocart")) {
            String sid = request.getParameter("sid");

            StudentCart stuCart = (StudentCart) session.getAttribute(sid);
            if (stuCart == null) {
                stuCart = new StudentCart();
                Vector<Student> vector = dao.getAll("select * from student where sid= '" + sid + "'");
                Student stu = vector.get(0);
                stuCart.setSID(stu.getSID());
                stuCart.setFname(stu.getFname());
                stuCart.setLname(stu.getLname());
                stuCart.setFee(stu.getFee());
                stuCart.setHourPerDay(1);
                session.setAttribute(sid, stuCart);
            } else {
                stuCart.setHourPerDay(stuCart.getHourPerDay() + 1);
                session.setAttribute(sid, stuCart);
            }
            response.sendRedirect("StudentControllerURL");

        } else if (service.equals("update")) {
            Enumeration<String> allNames = session.getAttributeNames();

            while (allNames.hasMoreElements()) {
                String key = allNames.nextElement();
                if (!key.equals("UserLogin")) {
                    StudentCart stuCart = (StudentCart) session.getAttribute(key);
                    int hourPerDay = Integer.parseInt(request.getParameter(key + "hourperday"));
                    if (hourPerDay > 0) {
                        stuCart.setHourPerDay(hourPerDay);
                    } else if (hourPerDay == 0) {
                        session.removeAttribute(key);
                    }
                    session.setAttribute(key, stuCart);
                }
            }
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("showcart")) {
            RequestDispatcher dis = request.getRequestDispatcher("jsp/ShowCart.jsp");
            dis.forward(request, response);
        } else if (service.equals("remove")) {
            String key = request.getParameter("sid");
            session.removeAttribute(key);
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("removeall")) {
            Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (!key.equals("UserLogin")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("checkout")) {
            DAOBill daoBill = new DAOBill();
            DAOTeacher daoTeacher = new DAOTeacher();
            UserLogin user = (UserLogin) session.getAttribute("UserLogin");
            String gmail = user.getUserName();
            
            Vector<Teacher> teacher = daoTeacher.getAll("select * from Teacher where email = '"+gmail+"'");
            
            Enumeration<String> allNames = session.getAttributeNames();
            while (allNames.hasMoreElements()) {
                String key = allNames.nextElement();
                if (!key.equals("UserLogin")) {
                    StudentCart stuCart = (StudentCart) session.getAttribute(key);
                    Vector<Bill> billVec = daoBill.getAll("select * from bill where teacherid = '" + teacher.get(0).getTID() + "' AND studentid = '" + stuCart.getSID()+ "'");
                    if (!billVec.isEmpty()) {
                        Bill bill = new Bill(teacher.get(0).getTID(), stuCart.getSID(), stuCart.getFee());
                        daoBill.updateBill(bill);
                    } else {
                        Bill bill = new Bill(teacher.get(0).getTID(), stuCart.getSID(), stuCart.getFee());
                        daoBill.addBill(bill);
                    }
                }
            }
            response.sendRedirect("CartController?service=showcart");
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CartController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
