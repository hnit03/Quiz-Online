/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.question.QuestionDAO;
import nhinh.question.QuestionDTO;
import nhinh.subject.SubjectDAO;
import nhinh.subject.SubjectDTO;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "TakeQuizServlet", urlPatterns = {"/TakeQuizServlet"})
public class TakeQuizServlet extends HttpServlet {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TakeQuizServlet.class.getName());
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
        String url = "quiz";
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            String subjectID = request.getParameter("subjectID");
            QuestionDAO qdao =new QuestionDAO();
            SubjectDAO sdao = new SubjectDAO();
            SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
            qdao.getAllQuestionToTakeQuiz(subjectID);
            int numberOfQuestion = qdao.getNumberOfQuestion(subjectID);
            
            List<QuestionDTO> list = qdao.getQuestionList();
            request.setAttribute("QUESTION_LIST", list);
            request.setAttribute("SUBJECT", sdto);
            HttpSession session = request.getSession();
            
            
            session.setAttribute("NUM_QUESTION", numberOfQuestion);
            request.setAttribute("SUBJECTID", subjectID);
        } catch (SQLException ex) {
            log.error("TakeQuizServlet_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("TakeQuizServlet_Naming:"+ex.getMessage());
        }finally{
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
