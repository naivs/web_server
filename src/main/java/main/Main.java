package main;

import services.accountService.AccountServiceImpl;
import beans.AccountServiceController;
import beans.AccountServiceControllerMBean;
import beans.ResourceServerController;
import services.dbService.DBServiceHibernate;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import resources.HBNParametersResource;
import resources.ResourceServer;
import services.ServiceManager;
import services.accountService.AccountService;
import services.dbService.DBService;
import services.server.HttpServer;
import services.vfs.VFSImpl;
import services.vfs.VFSService;

public class Main {

    public static void main(String[] args) throws Exception {

        ServiceManager serviceManager = new ServiceManager();

        VFSService vfsService = new VFSImpl("./");
        serviceManager.addService(VFSService.class.getName(), vfsService);
        ResourceServer resourceServer = new ResourceServer(vfsService);
        serviceManager.addService(ResourceServer.class.getName(), resourceServer);
        
//        XMLService xmlService = new SaxService();
//        serviceManager.addService(XMLService.class.getName(), xmlService);
        DBService dbService = new DBServiceHibernate((HBNParametersResource) resourceServer.getResource("./resources/config/hibernateConfig.xml"));
        dbService.printConnectInfo();
        serviceManager.addService(DBService.class.getName(), dbService);
        AccountService accountService = new AccountServiceImpl(10, dbService);
        serviceManager.addService(AccountService.class.getName(), accountService);
        
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        AccountServiceControllerMBean accountController = new AccountServiceController(accountService);
        mbs.registerMBean(accountController, new ObjectName("SessionsManager:type=AccountServiceController"));
        ResourceServerController resourceController = new ResourceServerController(resourceServer);
        mbs.registerMBean(resourceController, new ObjectName("Admin:type=ResourceServerController"));

        HttpServer httpServer = new HttpServer(serviceManager);
        httpServer.start();
        httpServer.join();
    }
}
