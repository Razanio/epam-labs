package com.epam.courses.service;

import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    User findById(Long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;

    User login(String login, String password) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;

    boolean canDelete(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    List<User> getFreeUsers() throws ServiceException;

    Boolean canAddUser(String name) throws ServiceException;

    Long addUser(User user) throws ServiceException;

    void setDefaultPassword(Long id) throws ServiceException;

}
