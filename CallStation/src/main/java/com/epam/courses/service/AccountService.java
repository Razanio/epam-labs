package com.epam.courses.service;

import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.service.exception.ServiceException;

import java.util.List;

public interface AccountService {

    void addAddon(Long service_id, Long currentUser_id) throws ServiceException;

    Boolean canAddAddon(Long service_id, Long currentUser_id) throws ServiceException;

    void deleteAccountAddon(Long service_id, Long currentUser_id) throws ServiceException;

    void payForTheAddon(Long service_id, Long currentUser_id) throws ServiceException;

    void topUpBalance(Double cash, Long currentUser_id) throws ServiceException;

    Addon findAddonById(Long service_id, Long currentUser_id) throws ServiceException;

    List<Addon> findAllAddons(Long currentUser_id) throws ServiceException;

    List<Addon> getAnotherAddons(Long currentUser_id) throws ServiceException;

    Addon getAddonByName(String name) throws ServiceException;

    List<Addon> getAddonsForPayment(Long current_User_id) throws ServiceException;

}
