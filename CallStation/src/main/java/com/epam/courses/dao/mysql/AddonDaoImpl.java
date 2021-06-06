package com.epam.courses.dao.mysql;

import com.epam.courses.dao.DaoException;
import com.epam.courses.dao.AddonDao;
import com.epam.courses.domain.Addon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddonDaoImpl extends BaseDaoImpl implements AddonDao {

    @Override
    public Addon read(Long id) throws DaoException {
        String sql = "SELECT `name`, `cost` FROM `addons_table` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Addon service = null;
            if(resultSet.next()) {
                service = new Addon();
                service.setId(id);
                service.setName(resultSet.getString("name"));
                service.setCost(resultSet.getDouble("cost"));
            }
            return service;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ resultSet.close(); } catch(Exception e) {}
            try{ statement.close(); } catch(Exception e) {}
        }
    }


    @Override
    public List<Addon> readAll() throws DaoException {
        String sql = "SELECT `id`, `name`, `cost` FROM `addons_table`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Addon> services = new ArrayList<>();
            while(resultSet.next()) {
                Addon service = new Addon();
                service.setId(resultSet.getLong("id"));
                service.setName(resultSet.getString("name"));
                service.setCost(resultSet.getDouble("cost"));
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
    public Long create(Addon service) throws DaoException {
        String sql = "INSERT INTO `addons_table` (`name`, `cost`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getCost());
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
    public void update(Addon service) throws DaoException {
        String sql = "UPDATE `addons_table` SET `name` = ?, `cost` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getCost());
            statement.setLong(3, service.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `addons_table` WHERE `id` = ?";
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
