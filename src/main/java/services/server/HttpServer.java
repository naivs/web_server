/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.server;

import org.eclipse.jetty.server.Server;
import resources.HttpServerParametersResource;

/**
 *
 * @author ivan
 */
public class HttpServer extends Server {
    
    private final HttpServerParametersResource parameters;
    
    public HttpServer(HttpServerParametersResource parameters) {
        super(parameters.getPort());
        this.parameters = parameters;
    }
}
