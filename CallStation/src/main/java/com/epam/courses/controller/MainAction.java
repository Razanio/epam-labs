package com.epam.courses.controller;

import com.epam.courses.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainAction extends Action{
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute("session_user");
            if(user != null){
                switch (user.getRole()){
                    case ADMIN:
                        return new Forward("/admin/adminview.html");
                    case SUBSCRIBER:
                        return new Forward("/account/accountview.html");
                }
            }
        }
        return new Forward("/login.html");
    }
}
