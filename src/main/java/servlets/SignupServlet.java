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
import templater.PageGenerator;

/**
 *
 * @author Ivan Naumov
 */
public class SignupServlet extends HttpServlet {

    private final AccountService accountService;

    public SignupServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println(PageGenerator.instance().getPage("signup.html"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String login = request.getParameter("login");

        if (accountService.isLogin(login)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().println(String.format("%s is already registered!", login));
            return;
        }
        
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (email == null) {
            accountService.addNewUser(new UserProfile(login, password, login + "@default.com"));
        } else {
            accountService.addNewUser(new UserProfile(login, password, email));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Registered");
    }
}
