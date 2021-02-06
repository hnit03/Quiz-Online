/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckAnswerServlet", urlPatterns = {"/CheckAnswerServlet"})
public class CheckAnswerServlet extends HttpServlet {

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
        String url = "result";
        try {
            /* TODO output your page here. You may use following sample code. */
            String[] questionIDStr = request.getParameterValues("questionID");

            List<String> answer = new ArrayList<>();
            List<String> answerChosenList = new ArrayList<>();
            for (String questionID : questionIDStr) {
                String answerChosen = request.getParameter("answer" + questionID);
                answer.add(answerChosen);
                answerChosenList.add(answerChosen+questionID);
            }
            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> list = new ArrayList<>();
            Map<QuestionDTO, String> result = new LinkedHashMap<>();
            for (String questionID : questionIDStr) {
                QuestionDTO dto = dao.getQuestionDTO(questionID);
                list.add(dto);
            }
            String correct = "Incorrect";

            int count = 0;
            int numOfCorrect = 0;
            while (count < answer.size()) {
                if (answer.get(count) != null) {
                    if (answer.get(count).equals(list.get(count).getAnswerCorrect())) {
                        correct = "Correct";
                        numOfCorrect++;
                    }
                }

                result.put(list.get(count), correct);
                count++;
            }
            int total = 0;
            HttpSession session = request.getSession(false);
            if (session!=null) {
                total = (int) session.getAttribute("NUM_QUESTION");
            }
            float totalPoint = (float) ((numOfCorrect/(1.0*total)) * 10);
            request.setAttribute("NUM_OF_CORRECT", numOfCorrect);
            request.setAttribute("TOTAL_POINT", totalPoint);
            request.setAttribute("ANSWER_CHOOSE", answerChosenList);
            request.setAttribute("RESULT", result);
        } catch (SQLException ex) {
            Logger.getLogger(CheckAnswerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckAnswerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
