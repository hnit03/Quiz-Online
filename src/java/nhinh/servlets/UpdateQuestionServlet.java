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
import nhinh.answer.AnswerDAO;
import nhinh.answer.AnswerDTO;
import nhinh.question.QuestionDAO;
import nhinh.question.QuestionDTO;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UpdateQuestionServlet.class.getName());

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
        String urlRewriting = "";
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            String questionID = request.getParameter("questionID");
            String content = request.getParameter("content");
            String[] answerContent = request.getParameterValues("answer");
            String answerCorrect = request.getParameter("answerCorrect");
            String status = request.getParameter("cboStatus");
            if (!questionID.trim().isEmpty()) {
                QuestionDAO qdao = new QuestionDAO();
                QuestionDTO oldDTO = qdao.getQuestionDTO(questionID);
                int statusID = Integer.parseInt(status);
                boolean success = qdao.updateQuestion(content, oldDTO.getCreateDate(), oldDTO.getSubjectDTO().getSubjectID(), statusID, oldDTO.getQuestionID());
                boolean type = false;
                if (success) {
                    AnswerDAO adao = new AnswerDAO();
                    for (AnswerDTO answerDTO : oldDTO.getAnswerList()) {
                        adao.deleteAnwser(answerDTO.getAnswerID());
                    }
                    for (String answer : answerContent) {
                        if (answer.equals(answerCorrect)) {
                            type = true;
                            adao.insertAnwser(questionID, answer, type);
                        } else {
                            adao.insertAnwser(questionID, answer, type);
                        }
                    }
                    urlRewriting = "manage?subjectID=" + oldDTO.getSubjectDTO().getSubjectID();
                }
            }
        } catch (SQLException ex) {
            log.error("UpdateQuestionServlet_SQL:" + ex.getMessage());
        } catch (NamingException ex) {
            log.error("UpdateQuestionServlet_Naming:" + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
