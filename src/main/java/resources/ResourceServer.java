/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import services.Service;
import services.vfs.VFSService;

/**
 *
 * @author ivan
 */
public class ResourceServer implements Service {

    private final Map<String, Resource> resources;

    public ResourceServer(VFSService vfsService) {
        resources = new HashMap<>();

        Iterator iterator = vfsService.getIterator("resources/config/");
        while (iterator.hasNext()) {
            String absolutePath = iterator.next().toString();
            if (!vfsService.isDirrectory(absolutePath)) {
                resources.put(absolutePath, ResourceFactory.instance().getResource(absolutePath));
                System.out.println("Resource added: " + absolutePath);
            }
        }
    }

    public Resource getResource(String name) {
        return resources.get(name);
    }

    public String getName() {
        return ((TestResource) resources.get("resources/config/testResource.xml")).getName();
    }

    public int getAge() {
        return ((TestResource) resources.get("resources/config/testResource.xml")).getAge();
    }
}
