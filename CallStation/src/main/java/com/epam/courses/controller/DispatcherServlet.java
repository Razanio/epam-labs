package com.epam.courses.controller;

import com.epam.courses.domain.User;
import com.epam.courses.util.Connector;
import com.epam.courses.util.ServiceFactory;
import com.epam.courses.util.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Action action = ActionFactory.getAction(url);
        Forward forward = null;
        if(action != null) {
            try(ServiceFactory factory = getServiceFactory()) {
                factory.getTransaction();
                action.setServiceFactory(factory);
                forward = action.execute(req, resp);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        if(forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if(forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            if(url.equals("/")){
                url = "/login";
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
