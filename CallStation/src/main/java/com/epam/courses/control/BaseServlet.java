package com.epam.courses.control;

import com.epam.courses.util.ServiceFactoryImpl;
import com.epam.courses.util.ServiceFactory;

import javax.servlet.http.HttpServlet;

abstract public class BaseServlet extends HttpServlet {
    public ServiceFactory getFactory() {
        return new ServiceFactoryImpl();
    }
}
