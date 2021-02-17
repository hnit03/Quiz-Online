/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhinh.subject.SubjectDAO;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "CreateNewSubjectServlet", urlPatterns = {"/CreateNewSubjectServlet"})
public class CreateNewSubjectServlet extends HttpServlet {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateNewSubjectServlet.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "error";
        int statusID = 0;
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */    
            String subjectName = request.getParameter("subjectName");
            String minute = request.getParameter("minute");
            String second = request.getParameter("second");
            String categoryID = request.getParameter("cboCategory");
            if (second.trim().isEmpty()) {
                second = "00";
            }
            if (!subjectName.trim().isEmpty()&&!minute.trim().isEmpty()&&!categoryID.trim().isEmpty()) {
                String time = minute + ":" + second;
                SubjectDAO sdao = new SubjectDAO();
                boolean success = sdao.createNewSubject(subjectName, time,statusID,categoryID);
                if (success) {
                    url = "subject";
                }
            }
            
        } catch (SQLException ex) {
            log.error("CreateNewSubject_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("CreateNewSubject_Naming:"+ex.getMessage());
        }finally{
            response.sendRedirect(url);
            out.close();
        }
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
