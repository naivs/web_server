/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author ivan
 */
public class HttpServerParametersResource implements Resource {

    private final int port;

    public HttpServerParametersResource() {
        port = 8080;
    }

    public HttpServerParametersResource(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "HttpServerParametersResource{"
                + "port=" + port
                + '}';
    }
}
