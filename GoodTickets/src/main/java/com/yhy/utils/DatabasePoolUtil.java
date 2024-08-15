package com.yhy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabasePoolUtil {

    private final static DruidDataSource dataSource = new DruidDataSource();

    public static void init(Properties props) {
        try {
            dataSource.setUsername(props.getProperty("mysql.user"));
            dataSource.setPassword(props.getProperty("mysql.pwd"));
            dataSource.setUrl(props.getProperty("mysql.url"));
            dataSource.setDriverClassName(props.getProperty("mysql.driver"));
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
