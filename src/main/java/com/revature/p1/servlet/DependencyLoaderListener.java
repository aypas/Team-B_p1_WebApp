package com.revature.p1.servlet;

import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.services.BankUserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * This class is tied to the startup and shutdown of tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BankUserDAO bankUserDAO = new BankUserDAO();
        BankUserService bankUserService = new BankUserService(bankUserDAO);

        AuthServlet authServlet = new AuthServlet(bankUserService);
        BankUserServlet bankUserServlet= new BankUserServlet(bankUserService);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("BankUserServlet", bankUserServlet).addMapping("/users/*");
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
