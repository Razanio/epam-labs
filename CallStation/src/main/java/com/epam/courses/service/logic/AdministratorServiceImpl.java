package com.epam.courses.service.logic;

import com.epam.courses.dao.*;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.Addon;
import com.epam.courses.domain.User;
import com.epam.courses.service.AdministratorService;
import com.epam.courses.service.UserService;
import com.epam.courses.service.exception.ServiceException;
import com.epam.courses.service.exception.user.UserCannotBeDeletedException;
import com.epam.courses.service.exception.user.UserLoginNotUniqueException;
import com.epam.courses.service.exception.user.UserNotExistsException;
import com.epam.courses.service.exception.user.UserPasswordIncorrectException;
import com.epam.courses.service.exception.addon.AddonAlreadyExistsException;
import com.epam.courses.service.exception.addon.AddonNotExistsException;

import java.util.ArrayList;
import java.util.List;

public class AdministratorServiceImpl extends BaseService implements AdministratorService {
    private UserDao userDao;
    private AccountDao accountDao;
    private AddonDao addonDao;
    private AccountsAddonsDao accountsAddonsDao;
    private String defaultPassword;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setAddonDao(AddonDao addonDao) {
        this.addonDao = addonDao;
    }

    public void setAccountsAddonsDao(AccountsAddonsDao accountsAddonsDao) {
        this.accountsAddonsDao = accountsAddonsDao;
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
    public boolean canDelete(Long id) throws ServiceException {
        try {
            if(userDao.read(id).getRole().equals(Role.ADMIN)){
                return false;
            }
            else{
                return true;
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
          if(canDelete(id)){
              userDao.delete(id);
              accountDao.delete(id);
              accountsAddonsDao.deleteAccount(id);
          }
          else{
              throw new UserCannotBeDeletedException();
          }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Addon getAddonById(Long addon_id) throws ServiceException {
        try {
            for(Addon service: addonDao.readAll()){
                if (service.getId().equals(addon_id)) {
                    return service;
                }
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public List<Addon> getAddons() throws ServiceException {
        try {
            return addonDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAddon(Addon newService) throws ServiceException {
        try {
            if(canAddAddon(newService)){
                addonDao.create(newService);
            }
            else{
                throw new AddonAlreadyExistsException(newService.getName());
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canAddAddon(Addon addon) throws ServiceException {
        try {
            if(!addon.getName().isBlank()) {
                for (Addon service : addonDao.readAll()) {
                    if (service.getName().equals(addon.getName())) {
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAddon(Long service_id) throws ServiceException {
        try {
            if(canDeleteAddon(service_id)){
                for(int i = 0; i < addonDao.readAll().size(); i++){
                    if(addonDao.readAll().get(i).getId().equals(service_id)){
                        addonDao.delete(service_id);
                        i = addonDao.readAll().size();
                    }
                }
            }
            else{
                throw new AddonNotExistsException();
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canDeleteAddon(Long service_id) throws ServiceException {
        try {
            for(Addon service: addonDao.readAll()){
                if(service.getId().equals(service_id)){
                    return true;
                }
            }
            return false;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeAddon(Double cost, Long service_id) throws ServiceException {
        try {
            if(canDeleteAddon(service_id)){
                Addon service = addonDao.read(service_id);
                service.setCost(cost);
                addonDao.update(service);
            }
            else{
                throw new AddonNotExistsException();
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getAccounts() throws ServiceException {
        try {
          return accountDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getBlockAccounts() throws ServiceException {
        try {
            List<Account> blockAccounts = new ArrayList<>();
            for(Account account: accountDao.readAll()){
                if(!account.getStatus()){
                    blockAccounts.add(account);
                }
            }
            return blockAccounts;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Account> getUnblockAccounts() throws ServiceException {
        try {
            List<Account> unblockAccounts = new ArrayList<>();
            for(Account account: accountDao.readAll()){
                if(account.getStatus()){
                    unblockAccounts.add(account);
                }
            }
            return unblockAccounts;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Account getAccountByName(String name) throws ServiceException {
        try {
            for(Account account: accountDao.readAll()){
                if(account.getClient().getLogin().equals(name)){
                    return account;
                }
            }
            return null;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canDeleteAccount(Long id) throws ServiceException {
        try {
            for(Account account: accountDao.readAll()){
                if(account.getId().equals(id)){
                    return true;
                }
            }
            return false;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAccount(Long id) throws ServiceException {
        try {
            if(canDeleteAccount(id)){
                //accountDao.delete(id);
                userDao.delete(id);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canBlockAccount(Long id) throws ServiceException {
        try {
            Account account = accountDao.read(id);
            return account.getStatus();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void blockAccount(Long id) throws ServiceException {
        try {
            Account account = accountDao.read(id);
            account.setStatus(false);
            accountDao.update(account);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unBlockAccount(Long id) throws ServiceException {
        try {
            Account account = accountDao.read(id);
            account.setStatus(true);
            accountDao.update(account);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAccount(User user) throws ServiceException {
        try {
            Account account = new Account();
            account.setClient(user);
            accountDao.create(account);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
