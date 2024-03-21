/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
*/

package controller;

import entity.StudentDep;

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
import model.DAOStuDep;

/**
 *
 * @author admin
 */
@WebServlet(name="StudentDepController", urlPatterns={"/StudentDepControllerURL"})
public class StudentDepController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        DAOStuDep dao = new DAOStuDep();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<StudentDep> vector = dao.getAll("select * from StudentDep");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of StudentDep");
            } else if (option.equals("searchfname")) {
                String fName = request.getParameter("sid");
                Vector<StudentDep> vector = dao.getAll("select * from StudentDep where StudentID = '" + fName + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "StudentDep first name: " + fName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/StudentDepManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String SID = request.getParameter("ssn");
                Vector<StudentDep> vector = dao.getAll("select * from StudentDep where StudentID= '" + SID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateStudentDep.jsp");
                dis.forward(request, response);
            } 
            else {
                String sid = request.getParameter("StudentID");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String relate = request.getParameter("relation");;
                String address = request.getParameter("address");
                int phone = Integer.parseInt(request.getParameter("phone"));
                StudentDep s = new StudentDep(sid, fname, lname, relate, address, phone);

                dao.updateStuDepBySID(s);
                response.sendRedirect("StudentDepControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertStudentDep.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.removeDep(request.getParameter("ssn"));
            response.sendRedirect("StudentDepControllerURL?service=listAll");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentDepController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentDepController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
