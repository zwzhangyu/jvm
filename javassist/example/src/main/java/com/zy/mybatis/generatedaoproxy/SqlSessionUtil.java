package com.zy.mybatis.generatedaoproxy;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class SqlSessionUtil {

    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static ThreadLocal<SqlSession> t = new ThreadLocal<>();

    public static SqlSession openSession() {
        SqlSession session = t.get();
        if (session == null) {
            session = sqlSessionFactory.openSession(true);
            t.set(session);
        }
        return session;
    }


    public static void main(String[] args) {
        SqlSession sqlSession = openSession();
        sqlSession.insert("com.zy.mybatis.generatedaoproxy.EmployeeDao.insert", new Employee("323", "444"));
    }
}
