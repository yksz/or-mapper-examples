package mybatis

import groovy.lang.Closure;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.entity.Product
import mybatis.mapper.ProductMapper;

class MyBatisPerformanceTest {

    private static final RECORD_NUM = 10000
    private static final SHOWS = false
    private static final MYBATIS_CONFIG_FILE = "mybatis/mybatis-config.xml"

    private static SqlSessionFactory sqlSessionFactory;

    static {
        def input = Resources.getResourceAsStream(MYBATIS_CONFIG_FILE);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
    }

    static def query(Closure closure, String msg) {
        def session = sqlSessionFactory.openSession()
        try {
            println msg
            long start = System.currentTimeMillis()
            closure(session)
            long stop = System.currentTimeMillis()
            println "${stop - start}[ms]\n"
        } finally {
            session.close()
        }
    }

    static def createTable(SqlSession session) {
        def mapper = session.getMapper(ProductMapper.class)
        mapper.createTable()
        for (i in 1..RECORD_NUM) {
            def p = new Product()
            p.setName("p${i}")
            mapper.save(p)
        }
        session.commit()
    }

    static def selectByAnnotation(SqlSession session) {
        for (i in 1..RECORD_NUM) {
            def mapper = session.getMapper(ProductMapper.class)
            def p = mapper.findByAnnotation(i)
            show p
        }
    }

    static def selectByXML(SqlSession session) {
        for (i in 1..RECORD_NUM) {
            def mapper = session.getMapper(ProductMapper.class)
            def p = mapper.findByXML(i)
            show p
        }
    }

    static def show(obj) {
        if (SHOWS)
            println obj
    }

    static main(args) {
        query({ createTable(it) }, 'createTable:')

        query({ selectByAnnotation(it) }, 'selectByAnnotation:')
        query({ selectByXML(it) }, 'selectByXML:')
    }

}
