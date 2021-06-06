package com.epam.courses.testdao;

import com.epam.courses.util.Connector;

public class TestInit {
    public static void init() {
        try {
            Connector.init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/call_station_db?useUnicode=true&characterEncoding=UTF-8", "root", "root");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
