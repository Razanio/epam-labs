package com.epam.courses.dao;

import com.epam.courses.domain.Addon;

import java.util.List;

public interface AddonDao extends Dao<Addon>{
    List<Addon> readAll() throws DaoException;

    //List<Service> readHistoryOfAccount(Long accountId) throws DaoException;
}
