/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.listener;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import nhinh.category.CategoryDAO;
import nhinh.category.CategoryDTO;
import org.apache.log4j.BasicConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author PC
 */
public class CategoryListener implements ServletContextListener {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CategoryListener.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicConfigurator.configure();
        try {
            ServletContext sc =sce.getServletContext();
            CategoryDAO dao = new CategoryDAO();
            dao.getListOfCategory();
            List<CategoryDTO> list = dao.getCategoryList();
            sc.setAttribute("CATEGORY", list);
        } catch (SQLException ex) {
            log.error("CategoryListener_SQL:"+ex.getMessage());
        } catch (NamingException ex) {
            log.error("CategoryListener_Naming:"+ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
