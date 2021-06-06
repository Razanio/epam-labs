package com.epam.courses.controller.user;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveNewPasswordAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                String oldpass = req.getParameter("oldpass");
                String newpass = req.getParameter("newpass");
                factory.getUserService().changePassword(user.getId(), oldpass,newpass);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/changepass.html");
    }
}
