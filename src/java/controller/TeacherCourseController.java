/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
*/

package controller;

import entity.teacherCourseManage;

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
import model.DAOTeachCourse;

/**
 *
 * @author admin
 */
@WebServlet(name="TeacherCourseController", urlPatterns={"/TeacherCourseControllerURL"})
public class TeacherCourseController extends HttpServlet {

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
        DAOTeachCourse dao = new DAOTeachCourse();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<teacherCourseManage> vector = dao.getAll("select * from teacherCourseManage");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of TeacherCourse");
            } else if (option.equals("searchfname")) {
                String sid = request.getParameter("tid");
                Vector<teacherCourseManage> vector = dao.getAll("select * from teacherCourseManage where teacherID = '" + sid + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "TeacherCourse ID: " + sid);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/TeacherCourseManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String TeacherID = request.getParameter("tid");
                Vector<teacherCourseManage> vector = dao.getAll("select * from teacherCourseManage where teacherID= '" + TeacherID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateTeacherCourse.jsp");
                dis.forward(request, response);
            } 
            else {
                String tid = request.getParameter("TeacherID");
                String course1 = request.getParameter("courseID1");
                String course2 = request.getParameter("courseID2");
                String course3 = request.getParameter("courseID3");

                teacherCourseManage s = new teacherCourseManage(tid, course1, course2, course3);

                dao.updateTeachCourse(s);
                response.sendRedirect("TeacherCourseControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertTeacherCourse.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.deleteTeacherCourse((request.getParameter("TID")));
            response.sendRedirect("TeacherCourseControllerURL?service=listAll");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeacherCourseController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherCourseController at " + request.getContextPath () + "</h1>");
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
