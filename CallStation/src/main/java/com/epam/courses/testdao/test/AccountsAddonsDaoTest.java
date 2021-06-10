package com.epam.courses.testdao.test;

import com.epam.courses.dao.AccountsAddonsDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.mysql.AccountDaoImpl;
import com.epam.courses.dao.mysql.AccountsAddonsDaoImpl;
import com.epam.courses.dao.mysql.AddonDaoImpl;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.testdao.TestInit;
import com.epam.courses.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountsAddonsDaoTest {

    public void readAllTest(AccountsAddonsDao dao) throws DaoException{
        TestInit.init();
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            accountDao.setConnection(connection);
            List<Account> accounts = accountDao.readAll();
            for(Account account : accounts) {
                List<Addon> services = dao.readServices(account.getClient().getId());
                output(services, account);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    public void readTest(AccountsAddonsDao dao, Long id) throws DaoException {
        TestInit.init();
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            accountDao.setConnection(connection);

            Account account = accountDao.read(id);
            List<Addon> services = dao.readServices(account.getId());
            output(services, account);

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    public void addTest(AccountsAddonsDao dao, Long id, Long serv_id) throws DaoException{
        TestInit.init();
        AddonDaoImpl serviceDao = new AddonDaoImpl();
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            serviceDao.setConnection(connection);
            accountDao.setConnection(connection);

            Addon service = serviceDao.read(serv_id);
            Account account = accountDao.read(id);

            dao.addAddons(service, account.getClient().getId());

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    public void deleteTest(AccountsAddonsDao dao, Long id, Long serv_id) throws DaoException {
       dao.deleteAddons(id,serv_id);
    }

    public void deleteAccountTest(AccountsAddonsDao dao, Long id) throws DaoException{
        dao.deleteAccount(id);
    }


    public void test() {
        TestInit.init();
        AccountsAddonsDaoImpl accountsServicesDao = new AccountsAddonsDaoImpl();
        AccountsAddonsDaoTest accountsServicesDaoTest = new AccountsAddonsDaoTest();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            accountsServicesDao.setConnection(connection);
            System.out.println("AccountsAddonsDao.read(4L):");
            accountsServicesDaoTest.readTest(accountsServicesDao , 4L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
            System.out.println("AccountsAddonsDao.add(4L in 1L):");
            addTest(accountsServicesDao, 1L, 4L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
            System.out.println("AccountsAddonsDao.delete(4L in 1L):");
            accountsServicesDaoTest.deleteTest(accountsServicesDao, 1L, 4L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
            System.out.println("AccountsAddonsDao.add(4L in 1L):");
            addTest(accountsServicesDao, 1L, 4L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
            System.out.println("AccountsAddonsDao.delete(1L):");
            accountsServicesDaoTest.deleteAccountTest(accountsServicesDao, 1L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
            System.out.println("AccountsAddonsDao.add(4L in 2L) backup:");
            addTest(accountsServicesDao, 1L, 2L);
            System.out.println("AccountsAddonsDao.readAll():");
            readAllTest(accountsServicesDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(List<Addon> services, Account account) {
        for(Addon service: services)
            System.out.printf("\tid=%d, client = %s, name=%s, cost=%s, payment=%s \n", account.getClient().getId(), account.getClient().getLogin(),  service.getName(), service.getCost().toString(), service.getPayment().toString());
    }
}
