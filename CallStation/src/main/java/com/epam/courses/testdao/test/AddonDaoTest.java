package com.epam.courses.testdao.test;

import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.AddonDao;
import com.epam.courses.dao.mysql.AddonDaoImpl;
import com.epam.courses.domain.Addon;
import com.epam.courses.testdao.DaoTest;
import com.epam.courses.testdao.TestInit;
import com.epam.courses.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddonDaoTest implements DaoTest<AddonDao> {

    @Override
    public void readTest(AddonDao dao) throws DaoException {
        Addon service = dao.read(1L);
        output(service);
    }

    @Override
    public void readAllTest(AddonDao dao) throws DaoException {
        List<Addon> services = dao.readAll();
        for(Addon service : services) {
            output(service);
        }
    }

    @Override
    public Long createTest(AddonDao dao) throws DaoException {
        Addon service = new Addon();
        service.setName("NEW-service");
        service.setCost(0.0);
        Long id = dao.create(service);
        System.out.printf("\tUser successfully added with id=%d\n", id);
        return id;
    }

    @Override
    public void updateTest(AddonDao dao, Long id) throws DaoException {
        Addon service = new Addon();
        service.setId(id);
        service.setName("CHANGED-service");
        service.setCost(24.6);
        dao.update(service);
        System.out.println("\tUser was successfully updated");
    }

    @Override
    public void deleteTest(AddonDao dao, Long id) throws DaoException {
        dao.delete(id);
        System.out.println("\tUser was successfully deleted");
    }

    @Override
    public void test() {
        TestInit.init();
        AddonDaoImpl serviceDao = new AddonDaoImpl();
        AddonDaoTest serviceDaoTest = new AddonDaoTest();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            serviceDao.setConnection(connection);
            System.out.println("UserDao.read(1L):");
            serviceDaoTest.readTest(serviceDao);
            System.out.println("UserDao.readAll():");
            serviceDaoTest.readAllTest(serviceDao);
            System.out.println("UserDao.create():");
            Long id = serviceDaoTest.createTest(serviceDao);
            System.out.println("UserDao.readAll():");
            serviceDaoTest.readAllTest(serviceDao);
            System.out.println("UserDao.update():");
            serviceDaoTest.updateTest(serviceDao, id);
            System.out.println("UserDao.readAll():");
            serviceDaoTest.readAllTest(serviceDao);
            System.out.println("UserDao.delete():");
            serviceDaoTest.deleteTest(serviceDao, id);
            System.out.println("UserDao.readAll():");
            serviceDaoTest.readAllTest(serviceDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(Addon service) {
        System.out.printf("\tid=%d, name=%s, cost=%s \n", service.getId(), service.getName(), service.getCost().toString());
    }
}
