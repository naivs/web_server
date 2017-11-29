package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.RootRequestsServlet;
import servlets.MirrorRequestServlet;
import servlets.SessionsServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;
import servlets.UsersServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("qw"));
        accountService.addNewUser(new UserProfile("as"));
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RootRequestsServlet()), "/");
        context.addServlet(new ServletHolder(new MirrorRequestServlet()), "/mirror");
        //context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        //context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignupServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SigninServlet(accountService)), "/signin");

        //ResourceHandler resource_handler = new ResourceHandler();
        //resource_handler.setResourceBase("resources/pages");
        
        //HandlerList handlers = new HandlerList();
        //handlers.setHandlers(new Handler[]{resource_handler, context});
        
        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
