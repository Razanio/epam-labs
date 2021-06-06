package com.epam.courses.testdao;

import com.epam.courses.dao.DaoException;

public interface DaoTest<T> {
    void readTest(T dao) throws DaoException;
    void readAllTest(T dao) throws DaoException;
    Long createTest(T dao) throws DaoException;
    void updateTest(T dao, Long id) throws DaoException;
    void deleteTest(T dao, Long id) throws DaoException;
    void test();
}
