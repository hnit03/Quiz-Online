/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhinh.registration.RegistrationDAO;
import nhinh.role.RoleDAO;
import nhinh.utils.SHA256;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginServlet.class.getName());
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
        String url = "signin";
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            RegistrationDAO dao = new RegistrationDAO();
            SHA256 sha = new SHA256();
            String pw = sha.bytesToHex(password);
            int roleID = dao.checkLogin(email, pw);
            if (roleID == -1) {
                request.setAttribute("LOGIN_FAILED", "Invalid userID or password.");
            } else {
                RoleDAO rdao = new RoleDAO();
                HttpSession session = request.getSession();
                session.setAttribute("EMAIL", email);
                session.setAttribute("ROLE", rdao.getRoleName(roleID));
                String fullname = dao.getFullname(email);
                session.setAttribute("FULLNAME", fullname);
                url = "subject";
            }
        } catch (SQLException ex) {
            log.error("Login_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("Login_Naming:"+ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log.error("Login_NoSuch:"+ex.getMessage());
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
