package com.epam.courses.service.logic;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.UserDao;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;
import com.epam.courses.service.UserService;
import com.epam.courses.service.exception.ServiceException;
import com.epam.courses.service.exception.user.UserLoginNotUniqueException;
import com.epam.courses.service.exception.user.UserNotExistsException;
import com.epam.courses.service.exception.user.UserPasswordIncorrectException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private AccountDao accountDao;
    private String defaultPassword;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if(userDao.readByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        try{
            return userDao.readByLoginAndPassword(login, password);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.read(userId);
            if(user != null) {
                if(user.getPassword().equals(oldPassword)) {
                    if(newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getFreeUsers() throws ServiceException {
        List<User> freeUser = new ArrayList<>();
        Boolean flag = true;
        try {
            for(User user: userDao.readAll()){
                if(user.getRole().equals(Role.SUBSCRIBER)){
                    for(Account account: accountDao.readAll()){
                        if(user.getId().equals(account.getClient().getId())){
                            flag = false;
                        }
                    }
                    if(flag) {
                        freeUser.add(user);
                    }
                    flag = true;
                }
            }
            return freeUser;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canAddUser(String name) throws ServiceException {
        try {
            for(User user: userDao.readAll()){
                if(user.getLogin().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))){
                    return false;
                }
            }
            return true;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long addUser(User user) throws ServiceException {
        try {
            if(canAddUser(user.getLogin())){
                user.setPassword(defaultPassword);
                return userDao.create(user);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public void setDefaultPassword(Long id) throws ServiceException {
        try {
            User user = userDao.read(id);
            user.setPassword(defaultPassword);
            userDao.update(user);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
