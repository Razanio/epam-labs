package com.epam.courses.dao;

import com.epam.courses.domain.Addon;

import java.util.List;

public interface AccountsAddonsDao {

    List<Addon> readServices(Long id) throws DaoException;

    void addAddons(Addon service, Long id) throws DaoException;

    void deleteAddons(Long id, Long serv_id) throws DaoException;

    void changePayment(Long id, Long serv_id, Boolean status) throws DaoException;

    void deleteAccount(Long id) throws DaoException;

}
