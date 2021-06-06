package com.epam.courses.control.user;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.service.UserService;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        user.setLogin(req.getParameter("login"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
        if(user.getLogin() != null && user.getRole() != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                service.save(user);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }
}
