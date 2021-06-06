package com.epam.courses.controller.admin.addon;

import com.epam.courses.controller.Action;
import com.epam.courses.controller.Forward;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminChangeAddonAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                String name = req.getParameter("changeaddon");
                Addon addon = factory.getAccountService().getAddonByName(name);
                new Forward("/admin/addon/changeaddon.html");
                req.setAttribute("user", user);
                req.setAttribute("name", addon.getName());
                req.getSession().setAttribute("addon_name", name);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
