/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import resources.ResourceServer;

/**
 *
 * @author ivan
 */
public class ResourceServerController implements ResourceServerControllerMBean {
    
    private final ResourceServer resourceServer;

    public ResourceServerController(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }
    
    @Override
    public String getName() {
        return resourceServer.getName();
    }

    @Override
    public int getAge() {
        return resourceServer.getAge();
    }
}
