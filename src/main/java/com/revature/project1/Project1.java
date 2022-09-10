package com.revature.project1;

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

        webServer.addContext("/project1", docBase);
        webServer.addServlet("/project1", "UserServlet", new UserServlet()).addMapping("/users");


        webServer.start();
        webServer.getServer().await();
        System.out.println("Web application successfully started.");
    }
}
