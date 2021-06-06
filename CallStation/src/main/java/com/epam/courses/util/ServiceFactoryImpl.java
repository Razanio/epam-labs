package com.epam.courses.util;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.AccountsAddonsDao;
import com.epam.courses.dao.AddonDao;
import com.epam.courses.dao.UserDao;
import com.epam.courses.dao.mysql.AccountDaoImpl;
import com.epam.courses.dao.mysql.AccountsAddonsDaoImpl;
import com.epam.courses.dao.mysql.AddonDaoImpl;
import com.epam.courses.dao.mysql.UserDaoImpl;
import com.epam.courses.service.*;
import com.epam.courses.service.logic.*;
import com.epam.courses.util.pool.ConnectionPool;
import com.epam.courses.util.pool.exception.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiceFactory {

    private Connection connection;

    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public AccountDao getAccountDao() throws FactoryException {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        accountDao.setConnection(getConnection());
        return accountDao;
    }

    @Override
    public AccountsAddonsDao getAccountsAddonsDao() throws FactoryException {
        AccountsAddonsDaoImpl accountsAddonsDao = new AccountsAddonsDaoImpl();
        accountsAddonsDao.setConnection(getConnection());
        return accountsAddonsDao;
    }

    @Override
    public AddonDao getAddonDao() throws FactoryException {
        AddonDaoImpl addonDao = new AddonDaoImpl();
        addonDao.setConnection(getConnection());
        return addonDao;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setTransaction(getTransaction());
        userService.setAccountDao(getAccountDao());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public AccountService getAccountService() throws FactoryException {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.setAccountDao(getAccountDao());
        accountService.setAddonDao(getAddonDao());
        accountService.setAccountsAddonsDao(getAccountsAddonsDao());
        accountService.setTransaction(getTransaction());
        return accountService;
    }

    @Override
    public AdministratorService getAdministratorService() throws FactoryException {
        AdministratorServiceImpl administratorService = new AdministratorServiceImpl();
        administratorService.setAddonDao(getAddonDao());
        administratorService.setAccountsAddonsDao(getAccountsAddonsDao());
        administratorService.setAccountDao(getAccountDao());
        administratorService.setUserDao(getUserDao());
        administratorService.setTransaction(getTransaction());
        return administratorService;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null)
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (PoolException | SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }

    @Override
    public void close() {
        try {
            ConnectionPool.getInstance().freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
