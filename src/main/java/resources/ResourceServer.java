/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import logger.MyLogger;
import services.Service;
import services.vfs.VFSImpl;
import services.vfs.VFSService;
import services.xml.XMLService;
import services.xml.sax.SaxService;

/**
 *
 * @author ivan
 */
public class ResourceServer implements Service {
            
    private static ResourceServer instance;
    private final Map<String, Resource> resources;
    private final XMLService xmlService;

    private ResourceServer(VFSService vfsService) {
        resources = new HashMap<>();
        xmlService = new SaxService();
        
        Iterator iterator = vfsService.getIterator("resources/config/");
        while (iterator.hasNext()) {
            String path = iterator.next().toString();
            if (!vfsService.isDirrectory(path)) {
                resources.put(path, (Resource) xmlService.readXML(path));
                System.out.println("Resource added: " + path);
            }
        }
    }

    public static ResourceServer instance() {
        if (instance == null) {
            instance = new ResourceServer(new VFSImpl(""));
        }
        return instance;
    }
    
    public Resource getResource(String name) {
        MyLogger.instance().log(MyLogger.INFO, "Trying to get resource: " + name);
        return resources.get(name);
    }
    
    public void loadResource(String path) {
        resources.put(path, (Resource) xmlService.readXML(path));
        System.out.println("Resource loaded: " + path);
        MyLogger.instance().log(MyLogger.INFO, "Resource loaded: " + path);
    }
}
