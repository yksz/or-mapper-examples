package service;

import java.util.List;

import mapper.BookMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Book;

public class BookServiceImpl implements BookService {

    private static SqlSessionFactory sqlSessionFactory = Context.getSqlSessionFactory();

    @Override
    public void save(Book book) throws Exception {
        if (book == null)
            throw new NullPointerException("book must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.insert(book);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> findAll() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.selectAll();
    }

}
