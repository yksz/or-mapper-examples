package org.example.datasource;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionManager {

    private static SqlSessionFactory sqlSessionFactory;

    static void init(ClassLoader loader, String configFile) throws IOException {
        Resources.setDefaultClassLoader(loader);
        InputStream in = Resources.getResourceAsStream(configFile);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

}
