package com.epam.courses.dao.mysql;

import com.epam.courses.dao.AccountsAddonsDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.domain.Addon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsAddonsDaoImpl extends BaseDaoImpl implements AccountsAddonsDao {

    @Override
    public List<Addon> readServices(Long id) throws DaoException {
        String sql = "SELECT * FROM `accounts_addons_table` LEFT JOIN `addons_table` ON accounts_addons_table.service_id = addons_table.id WHERE accounts_addons_table.account_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Addon> services = new ArrayList<>();
            while(resultSet.next()) {
                Addon service = new Addon();
                service.setId(resultSet.getLong("service_id"));
                service.setName(resultSet.getString("name"));
                service.setCost(resultSet.getDouble("cost"));
                service.setPayment(resultSet.getBoolean("payment"));
                services.add(service);
            }
            return services;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void addAddons(Addon service, Long id) throws DaoException {
        String sql = "INSERT INTO `accounts_addons_table` (`account_id`, `service_id`, `payment`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.setLong(2, service.getId());
            statement.setBoolean(3, false);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }


    @Override
    public void deleteAddons(Long id, Long serv_id) throws DaoException {
        String sql = "DELETE FROM `accounts_addons_table` WHERE `account_id` = ? AND `service_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.setLong(2, serv_id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void changePayment(Long id, Long serv_id, Boolean status) throws DaoException {
        String sql = "UPDATE `accounts_addons_table` SET `payment` = ? WHERE `account_id` = ? AND `service_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1,status);
            statement.setLong(2, id);
            statement.setLong(3, serv_id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void deleteAccount(Long id) throws DaoException {
        String sql = "DELETE FROM `accounts_addons_table` WHERE `account_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
