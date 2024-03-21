/*
 * Click nbfs://nbhost/SystemFileSystem/Tteacherlates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Tteacherlates/JSP_Servlet/Servlet.java to edit this tteacherlate
 */

 package controller;

 import entity.Teacher;
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
 import model.DAOTeacher;
 
 /**
  *
  * @author admin
  */
 @WebServlet(name="TeacherController", urlPatterns={"/TeacherControllerURL"})
 public class TeacherController extends HttpServlet {
    
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
        DAOTeacher dao = new DAOTeacher();
        
        HttpSession session = request.getSession(true);
        
        String service = request.getParameter("service");
        if (service == null) {
        service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            
            if (option == null) {
                Vector<Teacher> vector = dao.getAll("select * from Teacher");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Teacher");
            } 
            else if (option.equals("searchfname")) {
                String fName = request.getParameter("fname");
                Vector<Teacher> vector = dao.getAll("select * from Teacher where fname = '" + fName + "'");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Teacher first name: " + fName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/TeacherManage.jsp");
            dis.forward(request, response);
        } 
        else if (service.equals("update")) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                String TID = request.getParameter("ssn");
                Vector<Teacher> vector = dao.getAll("select * from Teacher where TID= '" + TID + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateTeacher.jsp");
                dis.forward(request, response);
            } 

            else {
                String tid = request.getParameter("TID");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                boolean gender = request.getParameter("gender").equals("1") ? true : false;
                String dob = request.getParameter("dob");
                String pos = request.getParameter("position");
                String address = request.getParameter("address");
                int phone = Integer.parseInt(request.getParameter("phone"));
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int salary = Integer.parseInt(request.getParameter("salary"));
                Teacher t = new Teacher(tid, fname, lname, gender, dob, pos, address, phone, email, password, salary);

                dao.updateTeacher(t);
                response.sendRedirect("TeacherControllerURL?service=listAll");
            }
        } 
        else if (service.equals("insert")) {
            request.getRequestDispatcher("jsp/InsertTeacher.jsp").forward(request, response);
        } 
        else if (service.equals("delete")) {
            dao.removeTeacher(request.getParameter("ssn"));
            response.sendRedirect("TeacherControllerURL?service=listAll");
        }
          
        // try (PrintWriter out = response.getWriter()) {
        //     /* TODO output your page here. You may use following sample code. */
        //     out.println("<!DOCTYPE html>");
        //     out.println("<html>");
        //     out.println("<head>");
        //     out.println("<title>Servlet TeacherController</title>");  
        //     out.println("</head>");
        //     out.println("<body>");
        //     out.println("<h1>Servlet TeacherController at " + request.getContextPath () + "</h1>");
        //     out.println("</body>");
        //     out.println("</html>");
        // }
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
 