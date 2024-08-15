package com.yhy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class MyBatisSqlSessionFactory  {
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        // 初始化 SqlSessionFactory
        InputStream inputStream = MyBatisSqlSessionFactory.class.getResourceAsStream("/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 获取当前线程的 SqlSession
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        // 从 ThreadLocal 中获取 SqlSession
        SqlSession sqlSession = threadLocal.get();

        if (sqlSession == null) {
            // 如果当前线程还没有 SqlSession，就创建一个新的，并放入 ThreadLocal 中
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }

        return sqlSession;
    }

    /**
     * 关闭当前线程的 SqlSession 并清除 ThreadLocal
     */
    public static void closeSqlSession() {
        // 从 ThreadLocal 中获取 SqlSession
        SqlSession sqlSession = threadLocal.get();

        if (sqlSession != null) {
            // 关闭 SqlSession 并清除 ThreadLocal
            sqlSession.close();
            threadLocal.remove();
        }
    }
    //提交
    public static void commitSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    //回滚
    public static void rollbackSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            // 出现异常，回滚事务
            sqlSession.rollback();
        }
    }
}

