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
import nhinh.registration.RegistrationDAO;
import nhinh.registration.RegistrationDTO;
import nhinh.registration.RegistrationError;
import nhinh.utils.SHA256;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author PC
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RegisterServlet.class.getName());
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
        String url = "register";
        int roleID = 1;
        int status = 1;
        BasicConfigurator.configure();
        try {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPassword");
            String fullname = request.getParameter("txtFullname");
            RegistrationError errors = new RegistrationError();
            RegistrationDAO dao = new RegistrationDAO();
            if (!email.trim().isEmpty() && !password.trim().isEmpty() && !confirmPassword.trim().isEmpty()
                    && !fullname.trim().isEmpty()) {
                boolean err = false;
                if (dao.checkEmailDup(email)) {
                    errors.setEmailIsExist(email + "is existed!!!");
                    err = true;
                }
                if (!confirmPassword.equals(password)) {
                    errors.setComfirmNotMatch("Confirm must match password");
                    err = true;
                }
                if (err) {
                    request.setAttribute("ERROR", errors);
                } else if (!err) {
                    SHA256 sha = new SHA256();
                    String pass = sha.bytesToHex(password);
                    RegistrationDTO dto = new RegistrationDTO(email, pass, fullname, roleID, status);
                    boolean success = dao.createNewAccount(dto);
                    if (success) {
                        url = "signin";
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("RegisterServlet_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("RegisterServlet_Naming:"+ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log.error("RegisterServlet_NoSuchAlgorithm:"+ex.getMessage());
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
