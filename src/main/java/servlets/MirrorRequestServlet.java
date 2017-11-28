/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ivan Naumov
 */
public class MirrorRequestServlet extends HttpServlet {
    
    private String key;
    
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)  throws IOException  {
        key = request.getParameter("key");
        if (key == null || key.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        response.getWriter().println(key);
    }
    
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        
    }
}
