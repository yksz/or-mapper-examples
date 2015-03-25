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
        Sessions.withTransaction(session, () -> {
            session.getMapper(ParentMapper.class).createTable();
            session.getMapper(ChildMapper.class).createTable();
            return null;
        });
    }

    @Override
    public Integer saveParent(Parent parent) throws Exception {
        if (parent == null)
            throw new NullPointerException("parent must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        Sessions.withTransaction(session, () -> {
            ParentMapper mapper = session.getMapper(ParentMapper.class);
            mapper.saveParent(parent);
            return null;
        });
        return parent.getId();
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
