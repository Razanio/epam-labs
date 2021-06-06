package com.epam.courses.controller.admin.account;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminAddAccountAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User) session.getAttribute("session_user");
            req.setAttribute("user", user);
        }
        return null;
    }
}