package com.epam.courses.controller;

import com.epam.courses.domain.Addon;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WelcomAction extends Action{
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceFactory factory = getFactory()){
            List<Addon> addons = factory.getAdministratorService().getAddons();
            req.setAttribute("addons", addons);
            return null;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
