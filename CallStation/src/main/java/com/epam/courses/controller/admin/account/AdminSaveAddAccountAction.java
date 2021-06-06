package com.epam.courses.controller.admin.account;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSaveAddAccountAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = new User();
                String name = req.getParameter("name");
                user.setLogin(name);
                user.setRole(Role.SUBSCRIBER);
                if(factory.getUserService().canAddUser(name)) {
                    Long newId = factory.getUserService().addUser(user);
                    user.setId(newId);
                    factory.getAdministratorService().addAccount(user);
                }
                else{
                    return new Forward("/admin/account/addaccount.html?message=login.message.incorrect.password");
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/admin/account/accounts.html");
    }
}
