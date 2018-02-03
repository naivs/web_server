package main;

import services.accountService.AccountServiceImpl;
import beans.AccountServiceController;
import beans.AccountServiceControllerMBean;
import services.dbService.DBServiceHibernate;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.HBNParametersResource;
import resources.HttpServerParametersResource;
import services.accountService.AccountService;
import services.dbService.DBService;
import services.server.HttpServer;
import services.xml.XMLService;
import services.xml.sax.SaxService;
import servlets.AdminServlet;
import servlets.RootRequestsServlet;
import servlets.MirrorRequestServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;
import servlets.WebSocketChatServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        XMLService xmlService = new SaxService();
        
        HttpServer httpServer = new HttpServer((HttpServerParametersResource) xmlService.readXML("resources/httpServerConfig.xml"));
        DBService dbService = new DBServiceHibernate((HBNParametersResource) xmlService.readXML("resources/hibernateConfig.xml"));
        dbService.printConnectInfo();
        AccountService accountService = new AccountServiceImpl(10, dbService);
        
        AccountServiceControllerMBean accountController = new AccountServiceController(accountService);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        mbs.registerMBean(accountController, new ObjectName("SessionsManager:type=AccountServiceController"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RootRequestsServlet(accountService)), "/");
        context.addServlet(new ServletHolder(new SignupServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SigninServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new MirrorRequestServlet()), "/mirror");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(new AdminServlet(accountService)), AdminServlet.SERVLET_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("resources/pages/");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        httpServer.setHandler(handlers);

        httpServer.start();
        System.out.println("Server started");
        httpServer.join();
    }
}
