package com.epam.courses.controller;

import com.epam.courses.util.ServiceFactory;
import com.epam.courses.util.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {
    public ServiceFactory getFactory() {
        return new ServiceFactoryImpl();
    }

    private ServiceFactory serviceFactory;

    public final ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public final void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    abstract public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
