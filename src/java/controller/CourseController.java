package controller;

import entity.Course;

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

import model.DAOCourse;

@WebServlet(name="CourseController", urlPatterns={"/CourseControllerURL"})
public class CourseController extends HttpServlet {

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
        DAOCourse dao = new DAOCourse();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Course> vector = dao.getAll("select * from Course");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Course");
            } else if (option.equals("searchname")) {
                String name = request.getParameter("name");
                Vector<Course> vector = dao.getAll("select * from Course where name = '" + name + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Course name: " + name);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/CourseManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String CID = request.getParameter("ssn");
                Vector<Course> vector = dao.getAll("select * from Course where CID= '" + CID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateCourse.jsp");
                dis.forward(request, response);
            } 
            else {
                String CID = request.getParameter("CID");
                String name = request.getParameter("name");
                String major = request.getParameter("major");
                Course s = new Course(CID, name, major);

                dao.updateCourse(s);
                response.sendRedirect("CourseControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertCourse.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.removeCourse(request.getParameter("ssn"));
            response.sendRedirect("CourseControllerURL?service=listAll");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseController at " + request.getContextPath () + "</h1>");
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
