package com.epam.courses.dao;

import com.epam.courses.domain.Account;
import com.epam.courses.domain.User;

import java.util.List;

public interface AccountDao extends Dao<Account> {
    List<Account> readAll() throws DaoException;

}
