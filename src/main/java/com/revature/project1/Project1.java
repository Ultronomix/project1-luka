package com.revature.project1;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Project1 {
    public static void main(String[] args) throws LifecycleException {
        String docBase = System.getProperty(("java.io.tmpdir"));
        Tomcat webServer = new Tomcat();
        webServer.setBaseDir(docBase);
        webServer.setPort(5000);
        webServer.getConnector();

        webServer.addContext("", docBase);

        webServer.start();
        webServer.getServer().await();
        System.out.println("Web application successfully started.");
    }
}
