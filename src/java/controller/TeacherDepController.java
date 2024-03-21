/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
*/

package controller;

import entity.TeacherDep;

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
import model.DAOTeacherDep;

/**
 *
 * @author admin
 */
@WebServlet(name="TeacherDepController", urlPatterns={"/TeacherDepControllerURL"})
public class TeacherDepController extends HttpServlet {

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
        DAOTeacherDep dao = new DAOTeacherDep();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<TeacherDep> vector = dao.getAll("select * from TeacherDep");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of TeacherDep");
            } else if (option.equals("searchfname")) {
                String fName = request.getParameter("tid");
                Vector<TeacherDep> vector = dao.getAll("select * from TeacherDep where teacherID = '" + fName + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "TeacherDep first name: " + fName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/TeacherDepManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String TID = request.getParameter("ssn");
                Vector<TeacherDep> vector = dao.getAll("select * from TeacherDep where teacherID= '" + TID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateTeacherDep.jsp");
                dis.forward(request, response);
            } 
            else {
                String TID = request.getParameter("TeacherID");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String relate = request.getParameter("relation");;
                String address = request.getParameter("address");
                int phone = Integer.parseInt(request.getParameter("phone"));
                TeacherDep s = new TeacherDep(TID, fname, lname, relate, address, phone);

                dao.updateTeacherDepByTID(s);
                response.sendRedirect("TeacherDepControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertTeacherDep.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.removeDep(request.getParameter("ssn"));
            response.sendRedirect("TeacherDepControllerURL?service=listAll");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeacherDepController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherDepController at " + request.getContextPath () + "</h1>");
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
