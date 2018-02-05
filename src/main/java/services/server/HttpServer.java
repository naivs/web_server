/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.server;

import javax.servlet.http.HttpServlet;
import logger.MyLogger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.HttpServerParametersResource;
import resources.ResourceServer;

/**
 *
 * @author ivan
 */
public class HttpServer {

    private final HttpServerParametersResource parameters;

    private final Server server;
    private final ServletContextHandler context;
    private final ResourceHandler resource_handler;

    public HttpServer() {
        this.parameters = (HttpServerParametersResource) ResourceServer.instance().getResource("resources/config/httpServerConfig.xml");
        server = new Server(parameters.getPort());

        context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("resources/pages/");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);
    }

    public void addServlet(HttpServlet servlet, String request) {
        context.addServlet(new ServletHolder(servlet), request);
    }

    public void start() throws Exception {
        server.setStopAtShutdown(true);
        server.start();
        System.out.println("Server started");
        MyLogger.instance().log(MyLogger.INFO, "Server started");
    }

    public void join() throws Exception {
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
        System.out.println("Server stopped");
    }
}
