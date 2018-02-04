/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.HttpServerParametersResource;
import resources.ResourceServer;
import services.ServiceManager;
import services.accountService.AccountService;
import services.xml.XMLService;
import servlets.AdminServlet;
import servlets.MirrorRequestServlet;
import servlets.ResourceServlet;
import servlets.RootRequestsServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;
import servlets.WebSocketChatServlet;

/**
 *
 * @author ivan
 */
public class HttpServer {

    private final ServiceManager serviceManager;
    private Server server;
    private final HttpServerParametersResource parameters;

    private final AccountService accountService;

    public HttpServer(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        parameters = (HttpServerParametersResource) ((ResourceServer) serviceManager.getService(ResourceServer.class.getName())).getResource("./resources/config/httpServerConfig.xml");
        accountService = (AccountService) serviceManager.getService(AccountService.class.getName());
        init();
    }

    private void init() {
        server = new Server(parameters.getPort());

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RootRequestsServlet(accountService)), "/");
        context.addServlet(new ServletHolder(new SignupServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SigninServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new MirrorRequestServlet()), "/mirror");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(new AdminServlet(accountService)), AdminServlet.SERVLET_URL);
        context.addServlet(new ServletHolder(new ResourceServlet()), ResourceServlet.SERVLET_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("resources/pages/");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Server started");
    }

    public void join() throws Exception {
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
        System.out.println("Server stopped");
    }
}
