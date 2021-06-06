package com.epam.courses.controller.user;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePasswordAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String rep = null;
        if (session != null) {
            try {
                User user = (User) session.getAttribute("session_user");
                req.setAttribute("user", user);
                if(user.getRole().equals(Role.ADMIN)){
                    rep = "/admin/adminview.html";
                }
                else if(user.getRole().equals(Role.SUBSCRIBER)){
                    rep = "/account/accountview.html";
                }
                req.setAttribute("page", rep);
                System.out.println(rep);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
