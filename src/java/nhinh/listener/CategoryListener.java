/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.listener;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import nhinh.category.CategoryDAO;
import nhinh.category.CategoryDTO;

/**
 * Web application lifecycle listener.
 *
 * @author PC
 */
public class CategoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc =sce.getServletContext();
            CategoryDAO dao = new CategoryDAO();
            dao.getListOfCategory();
            List<CategoryDTO> list = dao.getCategoryList();
            sc.setAttribute("CATEGORY", list);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CategoryListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
