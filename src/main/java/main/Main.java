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
import services.accountService.AccountService;
import services.dbService.DBService;
import servlets.AdminServlet;
import servlets.RootRequestsServlet;
import servlets.MirrorRequestServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;
import servlets.WebSocketChatServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        int port;
        
        if(args.length == 0) {
            System.out.println("Server will start on default port: 8080");
            port = 8080;
            System.out.println(Arrays.toString(args));
        } else {
            System.out.println("Server will start on port: " + args[0]);
            port = Integer.parseInt(args[0]);
        }
        
        Server server = new Server(port);
        DBService dbService = new DBServiceHibernate();
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
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
