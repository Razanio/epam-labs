package com.epam.courses.controller;

import com.epam.courses.util.pool.ConnectionPool;
import com.epam.courses.util.pool.exception.PoolException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationStartListener implements ServletContextListener {
    //private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("StartInit");
        ServletContext context = event.getServletContext();
        String jdbcDriver   = context.getInitParameter("jdbc-driver");
        String jdbcUrl      = context.getInitParameter("jdbc-url");
        String jdbcUsername = context.getInitParameter("jdbc-username");
        String jdbcPassword = context.getInitParameter("jdbc-password");
        try {
            ConnectionPool.getInstance().init(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, 5, 30, 30);
        } catch (PoolException e) {
            e.printStackTrace();
        }
        //logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}", jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
    }
}
