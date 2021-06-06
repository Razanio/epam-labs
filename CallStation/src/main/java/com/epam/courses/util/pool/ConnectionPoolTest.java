package com.epam.courses.util.pool;


import com.epam.courses.dao.DaoException;
import com.epam.courses.domain.Account;
import com.epam.courses.domain.Addon;
import com.epam.courses.util.FactoryException;
import com.epam.courses.util.ServiceFactoryImpl;
import com.epam.courses.util.pool.exception.PoolException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPoolTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Addon> services = new ArrayList<>();
		Account account = null;

		ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl();
		ConnectionPool pool = ConnectionPool.getInstance();

		try {
			pool.init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/call_station_db?useUnicode=true&characterEncoding=UTF-8", "root", "root",5,50,15);

			services.addAll(serviceFactory.getAccountsAddonsDao().readServices(4L));
			account = serviceFactory.getAccountDao().read(3L);
			System.out.println("Account - " + account.getClient().getLogin());
			for(Addon service: services){
				System.out.println("Service - " + service.getName());
			}

		} catch (IllegalArgumentException | PoolException | FactoryException | DaoException e) {
			e.printStackTrace();
		} finally {
			serviceFactory.close();
			pool.destroy();
		}
    }

}
