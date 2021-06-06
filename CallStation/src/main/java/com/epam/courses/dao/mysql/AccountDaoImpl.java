package com.epam.courses.dao.mysql;

import com.epam.courses.dao.AccountDao;
import com.epam.courses.dao.DaoException;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
    @Override
    public Account read(Long id) throws DaoException {
        String sql = "SELECT * FROM `accounts_table` LEFT JOIN `users_table` ON accounts_table.client_id = users_table.id WHERE accounts_table.client_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Account account = null;
            User user = null;
            if(resultSet.next()) {
                account = new Account();
                user = new User();
                user.setId(resultSet.getLong("client_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                account.setId(id);
                account.setClient(user);
                account.setBalance(resultSet.getDouble("balance"));
                account.setStatus(resultSet.getBoolean("status"));
            }
            return account;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Account> readAll() throws DaoException {
        String sql = "SELECT * FROM `accounts_table` LEFT JOIN `users_table` ON accounts_table.client_id = users_table.id";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()) {
                Account account = new Account();
                User user = new User();
                user.setId(resultSet.getLong("client_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                account.setId(resultSet.getLong("client_id"));
                account.setClient(user);
                account.setBalance(resultSet.getDouble("balance"));
                account.setStatus(resultSet.getBoolean("status"));
                accounts.add(account);
            }
            return accounts;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Account account) throws DaoException {
        String sql = "INSERT INTO `accounts_table` (`client_id`, `balance`, `status`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(account.getClient() != null) {
                statement.setLong(1, account.getClient().getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            account.setBalance(0.0);
            statement.setDouble(2, account.getBalance());
            statement.setBoolean(3, true);
            statement.executeUpdate();
            return account.getClient().getId();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(Account account) throws DaoException {
        String sql = "UPDATE `accounts_table` SET  `balance` = ?, `status` = ? WHERE `client_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setDouble(1, account.getBalance());
            statement.setBoolean(2, account.getStatus());
            statement.setLong(3, account.getClient().getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `accounts_table` WHERE `client_id` = ?";
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
