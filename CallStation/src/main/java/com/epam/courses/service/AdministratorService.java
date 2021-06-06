package com.epam.courses.service;

import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.service.exception.ServiceException;

import java.util.List;

public interface AdministratorService {

    User findById(Long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;

    boolean canDelete(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    Addon getAddonById(Long addon_id) throws ServiceException;

    List<Addon> getAddons() throws ServiceException;

    void addAddon(Addon newService) throws ServiceException;

    Boolean canAddAddon(Addon newService) throws ServiceException;

    void deleteAddon(Long service_id) throws ServiceException;

    Boolean canDeleteAddon(Long service_id) throws ServiceException;

    void changeAddon(Double cost, Long service_id) throws ServiceException;

    List<Account> getAccounts() throws ServiceException;

    List<Account> getBlockAccounts() throws ServiceException;

    List<Account> getUnblockAccounts() throws ServiceException;

    Account getAccountByName(String name) throws ServiceException;

    Boolean canDeleteAccount(Long id) throws ServiceException;

    void deleteAccount(Long id) throws ServiceException;

    Boolean canBlockAccount(Long id) throws ServiceException;

    void blockAccount(Long id) throws ServiceException;

    void unBlockAccount(Long id) throws ServiceException;

    void addAccount(User user) throws ServiceException;

}
