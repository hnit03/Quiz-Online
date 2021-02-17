package nhinh.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.apache.log4j.BasicConfigurator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author PC
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ServletContextListener.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String filename = "indexPage.txt";
        ServletContext context = sce.getServletContext();
        Map<String, String> indexPage = new HashMap<>();
        String path = context.getRealPath("/");
        File f = new File(path + "WEB-INF\\" + filename);
        FileReader fr;
        BasicConfigurator.configure();
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            StringTokenizer stk = null;
            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line, "=");
                String key = stk.nextToken();
                String value = stk.nextToken();
                indexPage.put(key.trim(), value.trim());
                context.setAttribute("PAGE", indexPage);
            }

        } catch (FileNotFoundException ex) {
            log.error("ServletContextListener_FileNotFound:"+ex.getMessage());
        } catch (IOException ex) {
            log.error("ServletContextListener_IOException:"+ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
