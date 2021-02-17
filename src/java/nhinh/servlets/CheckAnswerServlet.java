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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.history.HistoryDAO;
import nhinh.historydetails.HistoryDetailsDAO;
import nhinh.historydetails.HistoryDetailsDTO;
import nhinh.question.QuestionDAO;
import nhinh.question.QuestionDTO;
import nhinh.utils.Utils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckAnswerServlet", urlPatterns = {"/CheckAnswerServlet"})
public class CheckAnswerServlet extends HttpServlet {

    private Logger log = Logger.getLogger(CheckAnswerServlet.class.getName());
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
        Date date = new Date();
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);

            String subjectID = request.getParameter("subjectID");
            String[] questionIDStr = request.getParameterValues("questionID");
            List<String> answer = new ArrayList<>();
            for (String questionID : questionIDStr) {
                String answerChosen = request.getParameter("answer" + questionID);
                answer.add(answerChosen);
            }

            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> list = new ArrayList<>();
            Map<QuestionDTO, HistoryDetailsDTO> result = new LinkedHashMap<>();
            for (String questionID : questionIDStr) {
                QuestionDTO dto = dao.getQuestionDTO(questionID);
                list.add(dto);
            }

            int total = 0;
            String email = "";

            if (session != null) {
                total = (int) session.getAttribute("NUM_QUESTION");
                email = (String) session.getAttribute("EMAIL");
            }
            float totalPoint = 0;

            int numOfCorrect = 0;

            Utils utils = new Utils();
            String createDate = utils.formatDateTimeToString(date);
            HistoryDAO hdao = new HistoryDAO();

            hdao.insertHistory(email, subjectID, numOfCorrect, totalPoint, createDate);

            String historyID = hdao.getHistoryID(email, subjectID, createDate);

            HistoryDetailsDAO hddao = new HistoryDetailsDAO();

            int count = 0;
            String correct = "Incorrect";

            while (count < answer.size()) {
                if (answer.get(count) != null) {
                    if (answer.get(count).equals(list.get(count).getAnswerCorrect())) {
                        correct = "Correct";
                        numOfCorrect++;
                    }
                }
                hddao.insertHistoryDetails(historyID, questionIDStr[count], answer.get(count), correct);
                count++;
            }

            totalPoint = (float) ((numOfCorrect / (1.0 * total)) * 10);
            hdao.updateHistory(historyID, numOfCorrect, totalPoint);

            for (QuestionDTO qdto : list) {
                HistoryDetailsDTO hddto = hddao.getHistoryDetails(historyID, qdto.getQuestionID());
                result.put(qdto, hddto);
            }

            request.setAttribute("NUM_OF_CORRECT", numOfCorrect);
            request.setAttribute("TOTAL_POINT", totalPoint);
            request.setAttribute("RESULT", result);
        } catch (SQLException ex) {
            log.error("CheckAnswer_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("CheckAnswer_Naming:"+ex.getMessage());
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
