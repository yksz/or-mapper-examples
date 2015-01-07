package service;

import java.util.List;

import mapper.ChildMapper;
import mapper.ParentMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Parent;

public class ParentServiceImpl implements ParentService {

    private static SqlSessionFactory sqlSessionFactory = Context.getSqlSessionFactory();

    static {
        SqlSession session = sqlSessionFactory.openSession();
        session.getMapper(ParentMapper.class).createTable();
        session.getMapper(ChildMapper.class).createTable();
    }

    @Override
    public void save(Parent parent) throws Exception {
        if (parent == null)
            throw new NullPointerException("parent must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        try {
            ParentMapper mapper = session.getMapper(ParentMapper.class);
            mapper.save(parent);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Parent> findAllParents() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        ParentMapper mapper = session.getMapper(ParentMapper.class);
        return mapper.findAllParents();
    }

    @Override
    public List<Parent> findParentsByName(String name) throws Exception {
        if (name == null)
            throw new NullPointerException("name must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        ParentMapper mapper = session.getMapper(ParentMapper.class);
        return mapper.findParentsByName(name);
    }

}
