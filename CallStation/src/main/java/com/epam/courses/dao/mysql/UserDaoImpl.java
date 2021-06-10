package com.epam.courses.dao.mysql;

import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.UserDao;
import com.epam.courses.domain.Role;
import com.epam.courses.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `role` FROM `users_table` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `password`, `role` FROM `users_table` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `role` FROM `users_table` WHERE `login` = ? AND `password` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `users_table`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `users_table` (`login`, `password`, `role`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `users_table` SET `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `users_table` WHERE `id` = ?";
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
