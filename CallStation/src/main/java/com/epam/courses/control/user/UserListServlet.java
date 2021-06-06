package com.epam.courses.control.user;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.User;
import com.epam.courses.service.UserService;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(ServiceFactory factory = getFactory()) {
            UserService service = factory.getUserService();
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
