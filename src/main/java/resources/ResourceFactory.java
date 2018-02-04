/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import services.xml.XMLService;
import services.xml.sax.SaxService;

/**
 *
 * @author ivan
 */
public class ResourceFactory {
    
    private static ResourceFactory resourceFactory;
    private final XMLService xmlService;
    
    private ResourceFactory() {
        xmlService = new SaxService();
    }
    
    public static ResourceFactory instance() {
        if (resourceFactory == null) {
            resourceFactory = new ResourceFactory();
        }
        
        return resourceFactory;
    }
    
    public Resource getResource(String xmlFile) {
        return (Resource) xmlService.readXML(xmlFile);
    }
}
