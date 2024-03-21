package controller;

import entity.Major;

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

import model.DAOMajor;

@WebServlet(name="MajorController", urlPatterns={"/MajorControllerURL"})
public class MajorController extends HttpServlet {

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
        DAOMajor dao = new DAOMajor();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Major> vector = dao.getAll("select * from Major");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Major");
            } else if (option.equals("searchname")) {
                String name = request.getParameter("name");
                Vector<Major> vector = dao.getAll("select * from Major where name = '" + name + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Major name: " + name);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/MajorManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String MID = request.getParameter("ssn");
                Vector<Major> vector = dao.getAll("select * from Major where MID= '" + MID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateMajor.jsp");
                dis.forward(request, response);
            } 
            else {
                String MID = request.getParameter("MID");
                String TID = request.getParameter("teacherID");
                String name = request.getParameter("name");
                String major = request.getParameter("major");
                Major s = new Major(MID, TID, name, major);

                dao.updateMajor(s);
                response.sendRedirect("MajorControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertMajor.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.removeMajor(request.getParameter("ssn"));
            response.sendRedirect("MajorControllerURL?service=listAll");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MajorController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MajorController at " + request.getContextPath () + "</h1>");
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
