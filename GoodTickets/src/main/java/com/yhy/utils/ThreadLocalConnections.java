package com.yhy.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadLocalConnections {

    private static final ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static void set() {
        // 当前线程调用
        Connection conn = DatabasePoolUtil.connect();
        connections.set(conn);
    }

    public static Connection get() {
        return connections.get();
    }

    public static void remove() {
        connections.remove();
    }

    public static void close() {
        try {
            get().close();
            remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void setAutoCommit(boolean isAuto) {
        try {
            get().setAutoCommit(isAuto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        try {
            get().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isAutoCommit() {
        boolean autoCommit = false;
        try {
            autoCommit = get().getAutoCommit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autoCommit;
    }

}
