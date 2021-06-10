package com.epam.courses.dao;

import com.epam.courses.domain.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    List<User> readAll() throws DaoException;

    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

}
