/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.accountService.AccountService;

/**
 *
 * @author Ivan Naumov
 */
public class AdminServlet extends HttpServlet {
    
    public final static String SERVLET_URL = "/admin";
    private final AccountService accountService;
    
    public AdminServlet(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        int sessionsLimit = accountService.getSessionsLimit();
        response.getWriter().println(sessionsLimit);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    @Override
    public void doPost(HttpServletRequest request, 
            HttpServletResponse response) {
        
    }
}
