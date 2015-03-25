package service;

import java.util.List;

import mapper.BookMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import entity.Book;
import entity.Book.Category;

public class BookServiceImpl implements BookService {

    private static SqlSessionFactory sqlSessionFactory = Context.getSqlSessionFactory();

    static {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.createTable();
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Integer save(Book book) throws Exception {
        if (book == null)
            throw new NullPointerException("book must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        try {
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.save(book);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return book.getId();
    }

    @Override
    public List<Book> findAll() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception {
        if (title == null)
            throw new NullPointerException("title must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        if (author == null)
            throw new NullPointerException("author must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByAuthor(author);
    }

    @Override
    public List<Book> findByCategory(Category category) throws Exception {
        if (category == null)
            throw new NullPointerException("category must not be null");

        SqlSession session = sqlSessionFactory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByCategory(category);
    }

}
