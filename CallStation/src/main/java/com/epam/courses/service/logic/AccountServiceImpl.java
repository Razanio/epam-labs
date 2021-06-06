package com.epam.courses.service.logic;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.AccountsAddonsDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.AddonDao;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.service.AccountService;
import com.epam.courses.service.exception.ServiceException;
import com.epam.courses.service.exception.addon.AddonAlreadyExistsException;
import com.epam.courses.service.exception.addon.AddonNotExistsException;
import com.epam.courses.service.exception.user.account.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl extends BaseService implements AccountService {

    private AccountDao accountDao;
    private AddonDao addonDao;
    private AccountsAddonsDao accountsAddonsDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setAddonDao(AddonDao addonDao) {
        this.addonDao = addonDao;
    }

    public void setAccountsAddonsDao(AccountsAddonsDao accountsAddonsDao) {
        this.accountsAddonsDao = accountsAddonsDao;
    }

    @Override
    public void addAddon(Long service_id, Long currentUser_id) throws ServiceException {
        try {
            if(canAddAddon(service_id, currentUser_id)){
                accountsAddonsDao.addAddons(addonDao.read(service_id),currentUser_id);
            }
            else{
                throw new AddonAlreadyExistsException(addonDao.read(service_id).getName());
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Boolean canAddAddon(Long service_id, Long currentUser_id) throws ServiceException {
        try {
            for(Addon service: accountsAddonsDao.readServices(currentUser_id)){
                if(service.getId().equals(service_id)){
                    return false;
                }
            }
            return true;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAccountAddon(Long service_id, Long currentUser_id) throws ServiceException {
        try {
            if(!canAddAddon(service_id, currentUser_id)){
                for(int i = 0; i < accountsAddonsDao.readServices(currentUser_id).size(); i++){
                    if(accountsAddonsDao.readServices(currentUser_id).get(i).getId().equals(service_id)){
                        accountsAddonsDao.deleteAddons(currentUser_id, service_id);
                        i = accountsAddonsDao.readServices(currentUser_id).size();
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
    public void payForTheAddon(Long service_id, Long currentUser_id) throws ServiceException {
        try {
            if(accountDao.read(currentUser_id).getBalance() - addonDao.read(service_id).getCost() >= 0){
                accountsAddonsDao.changePayment(currentUser_id, service_id, true);
                Account account = accountDao.read(currentUser_id);
                Double balance = accountDao.read(currentUser_id).getBalance() - addonDao.read(service_id).getCost();
                balance = (double)Math.round(balance * 100) / 100;
                account.setBalance(balance);
                accountDao.update(account);
            }
            else{
                throw new InsufficientBalanceException();
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void topUpBalance(Double cash, Long currentUser_id) throws ServiceException {
        try {
            Account account = accountDao.read(currentUser_id);
            account.setBalance(account.getBalance() + cash);
            accountDao.update(account);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Addon findAddonById(Long service_id, Long currentUser_id) throws ServiceException {
        try {
            return addonDao.read(service_id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Addon> findAllAddons(Long currentUser_id) throws ServiceException {
        try {
            return addonDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Addon> getAnotherAddons(Long currentUser_id) throws ServiceException {
        try {
            List<Addon> anotherServices = new ArrayList<>();
            Boolean flag = true;
            for(Addon service: addonDao.readAll()) {
                for(Addon accountService: accountsAddonsDao.readServices(currentUser_id)){
                    if (service.getId().equals(accountService.getId())) {
                        flag = false;
                    }
                }
                if(flag){
                    anotherServices.add(service);
                }
                flag = true;

            }
            return anotherServices;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Addon getAddonByName(String name) throws ServiceException {
        String[] s = name.split("\\.");
        try {
            for(Addon service: addonDao.readAll()){
                if (service.getName().equals(s[0])) {
                    return service;
                }
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public List<Addon> getAddonsForPayment(Long current_User_id) throws ServiceException {
        try {
            List<Addon> servicesForPayment = new ArrayList<>();
            for(Addon service: accountsAddonsDao.readServices(current_User_id)){
                if(!service.getPayment()){
                    servicesForPayment.add(service);
                }
            }
            return servicesForPayment;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }


}
