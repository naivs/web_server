/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import accounts.AccountService;
import accounts.UserAccount;
import com.google.gson.Gson;
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
public class SigninServlet extends HttpServlet {

    private final AccountService accountService;

    public SigninServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //check active session
        if(accountService.isLogin(request.getSession().getId())) {
            response.getWriter().println("You are already loggin in!");
            response.sendRedirect("/");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().println(PageGenerator.instance().getPage("signin.html"));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //sign in
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserAccount userAccount = accountService.getUserByLogin(login);
        if (userAccount != null && password.equals(userAccount.getPassword())) {
            response.setContentType("text/html;charset=utf-8");
            accountService.addSession(request.getSession().getId(), userAccount.getLogin());
            
            Gson gson = new Gson();
            String json = gson.toJson(userAccount);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.getWriter().println("Authorized: " + login);
            response.sendRedirect("/");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("Unauthorized");
    }
    
    //sign out
    @Override
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        
        String sessionId = request.getSession().getId();
        UserAccount account = accountService.getUserBySessionId(sessionId);
        if (account == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
