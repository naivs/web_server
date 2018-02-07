package main;

import services.accountService.AccountServiceImpl;
import beans.AccountServiceController;
import beans.AccountServiceControllerMBean;
import beans.ResourceServerController;
import beans.ResourceServerControllerMBean;
import echoServer.Server;
import services.dbService.DBServiceHibernate;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import resources.HBNParametersResource;
import resources.HttpServerParametersResource;
import resources.ResourceServer;
import services.ServiceManager;
import services.accountService.AccountService;
import services.dbService.DBService;
import services.server.HttpServer;
import services.vfs.VFSImpl;
import services.vfs.VFSService;
import servlets.AdminServlet;
import servlets.MirrorRequestServlet;
import servlets.ResourceServlet;
import servlets.RootRequestsServlet;
import servlets.SigninServlet;
import servlets.SignupServlet;
import servlets.WebSocketChatServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        Thread server = new Thread(new Server(5050));
        server.start();
        
        //ServiceManager serviceManager = new ServiceManager();

        //VFSService vfsService = new VFSImpl("./");
        //serviceManager.addService(VFSService.class.getName(), vfsService);
        //ResourceServer resourceServer = new ResourceServer(vfsService);
        //serviceManager.addService(ResourceServer.class.getName(), resourceServer);
        
//        DBService dbService = new DBServiceHibernate();
//        dbService.printConnectInfo();
//        //serviceManager.addService(DBService.class.getName(), dbService);
//        AccountService accountService = new AccountServiceImpl(10, dbService);
//        //serviceManager.addService(AccountService.class.getName(), accountService);
//        
//        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//        AccountServiceControllerMBean accountController = new AccountServiceController(accountService);
//        mbs.registerMBean(accountController, new ObjectName("SessionsManager:type=AccountServiceController"));
//        ResourceServerControllerMBean resourceController = new ResourceServerController();
//        mbs.registerMBean(resourceController, new ObjectName("Admin:type=ResourceServerController"));
//
//        HttpServer httpServer = new HttpServer();
//        httpServer.addServlet(new RootRequestsServlet(accountService), "/");
//        httpServer.addServlet(new SignupServlet(accountService), "/signup");
//        httpServer.addServlet(new SigninServlet(accountService), "/signin");
//        httpServer.addServlet(new MirrorRequestServlet(), "/mirror");
//        httpServer.addServlet(new WebSocketChatServlet(), "/chat");
//        httpServer.addServlet(new AdminServlet(accountService), AdminServlet.SERVLET_URL);
//        httpServer.addServlet(new ResourceServlet(), ResourceServlet.SERVLET_URL);
//        
//        httpServer.start();
//        httpServer.join();
    }
}
