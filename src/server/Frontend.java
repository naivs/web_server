package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Frontend extends HttpServlet {

    private String login = "";
    private String password = "";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        login = req.getParameter("login");
        password = req.getParameter("pass");

        resp.getWriter().write("Hello " + login + "!!!");
    }
}
