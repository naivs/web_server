/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import accounts.UserAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.accountService.AccountService;
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
            email = login + "@default.com";
        }
        accountService.addNewUser(login, password, email);

        String sessionId = request.getSession().getId();
        UserAccount userAccount = accountService.getUserBySessionId(sessionId);
        if (userAccount != null) {
            String currentLogin = userAccount.getLogin();
            if (currentLogin.equals("root")) {
                response.getWriter().println(PageGenerator.instance().getPage("admin.html"));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Registered");
    }
}
