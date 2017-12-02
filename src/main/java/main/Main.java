package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBServiceImpl;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.DBService;
import servlets.RootRequestsServlet;
import servlets.MirrorRequestServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();
        
        AccountService accountService = new AccountService(dbService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RootRequestsServlet(accountService)), "/");
        context.addServlet(new ServletHolder(new SignupServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SigninServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new MirrorRequestServlet()), "/mirror");

//        ResourceHandler resource_handler = new ResourceHandler();
//        resource_handler.setResourceBase("resources/pages/");
//
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
