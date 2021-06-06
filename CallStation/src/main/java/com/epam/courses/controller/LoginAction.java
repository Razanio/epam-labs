package com.epam.courses.controller;

import com.epam.courses.domain.User;
import com.epam.courses.service.UserService;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginAction extends Action{
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.isBlank() && password != null) {
            try {
                ServiceFactory factory = getFactory();
                UserService userService = factory.getUserService();
                User user = userService.login(login, password);
                if(user != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    return new Forward("/main.html");
                }
                else{
                  return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
