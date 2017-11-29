package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //sign up
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        if (login.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        UserProfile userProfile = new UserProfile(login, password, String.format("%s@gmail.com", login));
        accountService.addNewUser(userProfile);
        
        Gson gson = new Gson();
        String json = gson.toJson(userProfile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //change profile
    @Override
    public void doPut(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    @Override
    public void doDelete(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}
