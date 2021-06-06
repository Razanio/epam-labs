package com.epam.courses.util;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.AccountsAddonsDao;
import com.epam.courses.dao.AddonDao;
import com.epam.courses.dao.UserDao;
import com.epam.courses.service.*;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {

    AccountDao getAccountDao() throws FactoryException;

    AccountsAddonsDao getAccountsAddonsDao() throws FactoryException;

    AddonDao getAddonDao() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    AccountService getAccountService() throws FactoryException;

    AdministratorService getAdministratorService() throws FactoryException;

    UserService getUserService() throws FactoryException;

    Transaction getTransaction() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
