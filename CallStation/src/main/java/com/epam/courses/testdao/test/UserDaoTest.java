package com.epam.courses.testdao.test;

import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.UserDao;
import com.epam.courses.dao.mysql.UserDaoImpl;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.testdao.DaoTest;
import com.epam.courses.testdao.TestInit;
import com.epam.courses.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest implements DaoTest<UserDao> {

    @Override
    public void readTest(UserDao userDao) throws DaoException {
        User user = userDao.read(1L);
        output(user);
    }

    @Override
    public void readAllTest(UserDao userDao) throws DaoException {
        List<User> users = userDao.readAll();
        for(User user : users) {
            output(user);
        }
    }

    @Override
    public Long createTest(UserDao userDao) throws DaoException {
        User user = new User();
        user.setLogin("NEW-user");
        user.setPassword("NEW-password");
        user.setRole(Role.SUBSCRIBER);
        Long id = userDao.create(user);
        System.out.printf("\tUser successfully added with id=%d\n", id);
        return id;
    }

    @Override
    public void updateTest(UserDao userDao, Long id) throws DaoException {
        User user = new User();
        user.setId(id);
        user.setLogin("CHANGED-user");
        user.setPassword("CHANGED-password");
        user.setRole(Role.SUBSCRIBER);
        userDao.update(user);
        System.out.println("\tUser was successfully updated");
    }

    @Override
    public void deleteTest(UserDao userDao, Long id) throws DaoException {
        userDao.delete(id);
        System.out.println("\tUser was successfully deleted");
    }

    @Override
    public void test() {
        TestInit.init();
        UserDaoImpl userDao = new UserDaoImpl();
        UserDaoTest userDaoTest = new UserDaoTest();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);
            /*System.out.println("UserDao.read(1L):");
            userDaoTest.readTest(userDao);
            System.out.println("UserDao.readAll():");
            userDaoTest.readAllTest(userDao);
            System.out.println("UserDao.create():");
            Long id = userDaoTest.createTest(userDao);
            System.out.println("UserDao.readAll():");
            userDaoTest.readAllTest(userDao);
            System.out.println("UserDao.update():");
            userDaoTest.updateTest(userDao, id);*/
            System.out.println("UserDao.readAll():");
            userDaoTest.readAllTest(userDao);
            System.out.println("UserDao.delete():");
            //userDaoTest.deleteTest(userDao, id);
            userDaoTest.deleteTest(userDao, 1L);
            System.out.println("UserDao.readAll():");
            userDaoTest.readAllTest(userDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(User user) {
        System.out.printf("\tid=%d, login=%s, password=%s, role=%s\n", user.getId(), user.getLogin(), user.getPassword(), user.getRole().toString());
    }
}
