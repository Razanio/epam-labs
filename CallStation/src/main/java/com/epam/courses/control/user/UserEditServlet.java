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

public class UserEditServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);
                boolean userCanBeDeleted = service.canDelete(id);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}
