/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhinh.question.QuestionDAO;
import nhinh.utils.Utils;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {
    
    private final int statusID = 0;
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateQuestionServlet.class.getName());
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
        Date date = new Date();
        String url = "subject";
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            String content = request.getParameter("content");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String answer4 = request.getParameter("answer4");
            String answerCorr = request.getParameter("answerCorrect");
            String subjectID = request.getParameter("subjectID");
            if (!content.trim().isEmpty() && !answer1.trim().isEmpty() && !answer2.trim().isEmpty()
                    && !answer3.trim().isEmpty() && !answer4.trim().isEmpty() && !answerCorr.trim().isEmpty()) {
                QuestionDAO dao = new QuestionDAO();
                Utils utils = new Utils();
                String createDate = utils.formatDateToString(date);
                boolean success = dao.createNewQuestion(content, answer1, answer2, answer3, answer4, answerCorr, createDate, subjectID, statusID);
                if (success) {
                    request.setAttribute("CREATE", "Create Successfully!");
                }
            }
        } catch (SQLException ex) {
            log.error("CreateQuestion_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("CreateQuestion_Naming:"+ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
