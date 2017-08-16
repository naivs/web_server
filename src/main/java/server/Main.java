package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) {

        Frontend frontend = new Frontend();

        Server server = new Server(8080);
        ServletContextHandler context =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        server.setHandler(context);
        context.addServlet(new ServletHolder(frontend),"/home");

        try {
            server.start();
            server.join();
        } catch(Exception ex) {
            System.err.println("Something happened wrong...");
        }
    }
}
