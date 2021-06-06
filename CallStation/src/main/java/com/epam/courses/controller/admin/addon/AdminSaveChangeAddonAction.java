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

public class AdminSaveChangeAddonAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                String name = (String) req.getSession().getAttribute("addon_name");
                req.getSession().removeAttribute("addon_name");
                Double cost = Double.parseDouble(req.getParameter("integer")) + Double.parseDouble(req.getParameter("decimal")) / 100;
                Addon addon = factory.getAccountService().getAddonByName(name);
                factory.getAdministratorService().changeAddon(cost, addon.getId());
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/admin/addon/addons.html");
    }
}
