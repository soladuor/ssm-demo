package com.sola.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 获取和关闭会话的工具类
 */
public class MapperTools {
    private static final SqlSessionFactory sqlSessionFactory;
    private static final ThreadLocal<SqlSession> threadLocal;

    // 初始化SqlSessionFactory和连接池，只需要执行一次（使用静态代码块）
    static {
        try {
            // 读取mybatis-config.xml文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建线程池
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取会话
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get(); // 拿到线程共享的SqlSession对象
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();// 创建会话
            threadLocal.set(sqlSession); // 将会话与当前线程绑定
        }
        return sqlSession;
    }

    /**
     * 获取mapper
     */
    public static <T> T getMapper(Class<T> tClass) {
        return getSqlSession().getMapper(tClass);
    }

    /**
     * 关闭会话
     */
    public static void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get(); // 得到当前线程中的SqlSession
        if (sqlSession != null) {
            sqlSession.close(); // 关闭会话
            threadLocal.remove(); // 从ThreadLocal中移除当前线程已关闭的SqlSession对象
        }
    }
}
