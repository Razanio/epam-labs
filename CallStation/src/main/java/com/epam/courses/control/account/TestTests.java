package com.epam.courses.control.account;

import com.epam.courses.control.BaseServlet;
import com.epam.courses.dao.DaoException;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.util.FactoryException;
import com.epam.courses.util.ServiceFactory;
import com.epam.courses.util.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestTests extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* TestInit.init();
        AccountDaoImpl accountDao = new AccountDaoImpl();
        AccountsServicesDaoImpl accountsServicesDao = new AccountsServicesDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            accountDao.setConnection(connection);
            accountsServicesDao.setConnection(connection);
            List<Account> accounts = new ArrayList<>();
            accounts = accountDao.readAll();
            List<Service> services = new ArrayList<>();
            for (Account account : accounts) {
                services = accountsServicesDao.readServices(account.getId());
                account.setServices(services);
            }
            req.setAttribute("accounts", accounts);
            //req.setAttribute("services", accounts.get(2).getServices());
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        } catch (SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }*/

        try {
            ServiceFactory serviceFactory = new ServiceFactoryImpl();
            List<Account> accounts = serviceFactory.getAccountDao().readAll();
            List<Addon> services = new ArrayList<>();
            for (Account account : accounts) {
                services = serviceFactory.getAccountsAddonsDao().readServices(account.getId());
                account.setAddons(services);
            }
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        } catch (DaoException | FactoryException e) {
            throw new ServletException(e);
        }
    }
}
