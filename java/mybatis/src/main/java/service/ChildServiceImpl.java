package service;

import java.util.List;

import mapper.ChildMapper;
import mapper.ParentMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Child;

public class ChildServiceImpl implements ChildService {

    private static SqlSessionFactory sqlSessionFactory = Context.getSqlSessionFactory();

    static {
        SqlSession session = sqlSessionFactory.openSession();
        session.getMapper(ChildMapper.class).createTable();
        session.getMapper(ParentMapper.class).createTable();
    }

    @Override
    public Integer saveChild(Child child) throws Exception {
        if (child == null)
            throw new NullPointerException("child must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        try {
            ChildMapper mapper = session.getMapper(ChildMapper.class);
            mapper.saveChild(child);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return child.getId();
    }

    @Override
    public List<Child> findAllChildren() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        ChildMapper mapper = session.getMapper(ChildMapper.class);
        return mapper.findAllChildren();
    }

    @Override
    public List<Child> findChildrenByName(String name) throws Exception {
        if (name == null)
            throw new NullPointerException("name must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        ChildMapper mapper = session.getMapper(ChildMapper.class);
        return mapper.findChildrenByName(name);
    }

}
