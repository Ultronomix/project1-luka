package com.revature.project1;

import com.revature.project1.auth.AuthService;
import com.revature.project1.auth.AuthServlet;
import com.revature.project1.users.User;
import com.revature.project1.users.UserDAO;
import com.revature.project1.users.UserServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Project1 {
    public static void main(String[] args) throws LifecycleException {
        String docBase = System.getProperty(("java.io.tmpdir"));
        Tomcat webServer = new Tomcat();

        webServer.setBaseDir(docBase);
        webServer.setPort(8080);
        webServer.getConnector();

        UserDAO userDAO = new UserDAO();
        AuthService authService = new AuthService(userDAO);
        UserServlet userServlet = new UserServlet(userDAO);
        AuthServlet authServlet = new AuthServlet(authService);

        final String rootContext = "/project1";
        webServer.addContext(rootContext, docBase);
        webServer.addServlet(rootContext, "UserServlet", userServlet).addMapping("/users");
        webServer.addServlet(rootContext, "AuthServlet", userServlet).addMapping("/auth");


        webServer.start();
        webServer.getServer().await();
    }
}
