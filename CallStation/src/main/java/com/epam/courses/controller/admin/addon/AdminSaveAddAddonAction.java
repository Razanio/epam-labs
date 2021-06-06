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

public class AdminSaveAddAddonAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            try (ServiceFactory factory = getFactory()) {
                User user = (User) session.getAttribute("session_user");
                String name = req.getParameter("name");
                Double cost = Double.parseDouble(req.getParameter("integer")) + Double.parseDouble(req.getParameter("decimal"))/100;
                Addon addon = new Addon();
                addon.setName(name);
                addon.setCost(cost);
                if(factory.getAdministratorService().canAddAddon(addon)) {
                    factory.getAdministratorService().addAddon(addon);
                }
                else{
                    return new Forward("/admin/addon/addaddon.html?message=login.message.incorrect.password");
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/admin/addon/addons.html");
    }
}
