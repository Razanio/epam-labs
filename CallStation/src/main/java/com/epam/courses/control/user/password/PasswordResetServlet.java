package com.epam.courses.control.user.password;



import com.epam.courses.control.BaseServlet;
import com.epam.courses.domain.User;
import com.epam.courses.service.UserService;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordResetServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(ServiceFactory factory = getFactory()) {
            UserService service = factory.getUserService();
            User user = service.findById(Long.parseLong(req.getParameter("id")));
            if(user != null) {
                service.changePassword(user.getId(), user.getPassword(), null);
            }
            resp.sendRedirect(req.getContextPath() + "/user/list.html");
        } catch(NumberFormatException e) {
            resp.sendError(400);
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
