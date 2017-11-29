/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ivan Naumov
 */
public class SigninServlet extends HttpServlet {
    
    private final AccountService accountService;

    public SigninServlet(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
            String login = request.getParameter("login");
            //String password = request.getParameter("pass");
            
            UserProfile userProfile = accountService.getUserByLogin(login);
            if(userProfile != null) {
                //if(password.equals(userProfile.getLogin())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("Authorized: " + login);
                    return;
                //}
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
    }
}
