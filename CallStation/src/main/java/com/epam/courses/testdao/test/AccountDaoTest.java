package com.epam.courses.testdao.test;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.mysql.AccountDaoImpl;
import com.epam.courses.dao.mysql.UserDaoImpl;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.testdao.DaoTest;
import com.epam.courses.testdao.TestInit;
import com.epam.courses.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoTest implements DaoTest<AccountDao> {

    @Override
    public void readTest(AccountDao dao) throws DaoException {
        Account account = dao.read(1L);
        output(account);
    }

    @Override
    public void readAllTest(AccountDao dao) throws DaoException {
        List<Account> accounts = dao.readAll();
        for(Account account : accounts) {
            output(account);
        }
    }

    @Override
    public Long createTest(AccountDao dao) throws DaoException {
        Long id = null;
        TestInit.init();
        UserDaoImpl userDao = new UserDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);

            User user = new User();
            user.setLogin("vasya");
            user.setPassword("2234");
            user.setRole(Role.SUBSCRIBER);
            Long client_id =userDao.create(user);
            user.setId(client_id);

            Account account = new Account();
            account.setClient(user);
            account.setBalance(20.0);
            account.setStatus(true);
            id = dao.create(account);

            System.out.printf("\tUser successfully added with id=%d\n", id);

        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
        return id;
    }

    @Override
    public void updateTest(AccountDao dao, Long id) throws DaoException {
        Account account = dao.read(id);
        account.setBalance(20.0);
        account.setStatus(false);
        dao.update(account);
        System.out.println("\tUser was successfully updated");
    }

    @Override
    public void deleteTest(AccountDao dao, Long id) throws DaoException {
        TestInit.init();
        UserDaoImpl userDao = new UserDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);

            Account account = dao.read(id);
            Long client_id = account.getClient().getId();

            dao.delete(id);
            userDao.delete(client_id);
            System.out.println("\tUser was successfully deleted");

        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void test() {
        TestInit.init();
        AccountDaoImpl accountDao = new AccountDaoImpl();
        AccountDaoTest accountDaoTest = new AccountDaoTest();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            accountDao.setConnection(connection);
            System.out.println("UserDao.read(1L):");
            accountDaoTest.readTest(accountDao);
            System.out.println("UserDao.readAll():");
            accountDaoTest.readAllTest(accountDao);
            System.out.println("UserDao.create():");
            Long id = accountDaoTest.createTest(accountDao);
            System.out.println("UserDao.readAll():");
            accountDaoTest.readAllTest(accountDao);
            System.out.println("UserDao.update():");
            accountDaoTest.updateTest(accountDao, id);
            System.out.println("UserDao.readAll():");
            accountDaoTest.readAllTest(accountDao);
            System.out.println("UserDao.delete():");
            accountDaoTest.deleteTest(accountDao, id);
            System.out.println("UserDao.readAll():");
            accountDaoTest.readAllTest(accountDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(Account account) {
        System.out.printf("\tclient_id=%s, balance=%s, status=%s\n", account.getClient().getId(), account.getBalance().toString(), account.getStatus().toString());
    }
}
