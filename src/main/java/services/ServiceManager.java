/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ivan
 */
public class ServiceManager {
    
    private final Map<String, Service> services = new HashMap<>();
    
    public void addService(String name, Service service) {
        services.put(name, service);
    }
    
    public Service getService(String name) {
        return services.get(name);
    }
}
