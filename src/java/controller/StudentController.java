/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Student;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOStudent;

/**
 *
 * @author admin
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentControllerURL"})
public class StudentController extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        DAOStudent dao = new DAOStudent();

        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Student> vector = dao.getAll("select * from Student");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Student");
            } else if (option.equals("searchfname")) {
                String fName = request.getParameter("fname");
                Vector<Student> vector = dao.getAll("select * from Student where fname = '" + fName + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Student first name: " + fName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/StudentManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String SID = request.getParameter("ssn");
                Vector<Student> vector = dao.getAll("select * from Student where SID= '" + SID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateStudent.jsp");
                dis.forward(request, response);
            } else {
                String sid = request.getParameter("SID");
                String fname = request.getParameter("FName");
                String lname = request.getParameter("LName");
                boolean gender = request.getParameter("Sex").equals("1") ? true : false;
                String dob = request.getParameter("DOB");
                String major = request.getParameter("Major");
                String address = request.getParameter("Address");
                int phone = Integer.parseInt(request.getParameter("Phone"));
                String email = request.getParameter("Email");
                String password = request.getParameter("Password");
                int fee = Integer.parseInt(request.getParameter("Fee"));
                Student s = new Student(sid, fname, lname, gender, dob, major, address, phone, email, password, fee);

                dao.updateStudent(s);
                response.sendRedirect("StudentControllerURL?service=listAll");

            }
        } else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertStudent.jsp").forward(request, response);
        } else if (service.equals("delete")) {
            dao.removeStudent(request.getParameter("ssn"));
            response.sendRedirect("StudentControllerURL?service=listAll");
        }

        // try (PrintWriter out = response.getWriter()) {
        //     /* TODO output your page here. You may use following sample code. */
        //     out.println("<!DOCTYPE html>");
        //     out.println("<html>");
        //     out.println("<head>");
        //     out.println("<title>Servlet StudentController</title>");  
        //     out.println("</head>");
        //     out.println("<body>");
        //     out.println("<h1>Servlet StudentController at " + request.getContextPath () + "</h1>");
        //     out.println("</body>");
        //     out.println("</html>");
        // }
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
