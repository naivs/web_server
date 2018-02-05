/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logger.MyLogger;
import resources.ResourceServer;
import templater.PageGenerator;

/**
 *
 * @author ivan
 */
public class ResourceServlet extends HttpServlet {

    public static final String SERVLET_URL = "/resources";

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //return static page
        response.getWriter().println(PageGenerator.instance().getPage("resource.html"));
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        MyLogger.instance().log(MyLogger.INFO, "POST request on /resources");
        String resource = request.getParameter("path");
        
        if (resource == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            System.out.println("Requested resource: " + resource);
            MyLogger.instance().log(MyLogger.INFO, "Requested resource: " + resource);
            ResourceServer.instance().loadResource(resource);
        }
    }
}
