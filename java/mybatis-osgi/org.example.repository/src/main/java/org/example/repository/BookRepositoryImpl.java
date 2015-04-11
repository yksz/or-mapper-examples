package org.example.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.entity.Book;
import org.example.dao.entity.Book.Category;
import org.example.dao.mapper.BookMapper;
import org.example.datasource.SessionManager;

public class BookRepositoryImpl implements BookRepository {

    static {
        SqlSession session = SessionManager.openSession();
        Sessions.withTransaction(session, () -> {
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.createTable();
        });
    }

    @Override
    public Integer save(Book book) throws Exception {
        if (book == null)
            throw new NullPointerException("book must not be null");

        SqlSession session = SessionManager.openSession();
        Sessions.withTransaction(session, () -> {
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.save(book);
        });
        return book.getId();
    }

    @Override
    public List<Book> findAll() throws Exception {
        SqlSession session = SessionManager.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception {
        if (title == null)
            throw new NullPointerException("title must not be null");

        SqlSession session = SessionManager.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        if (author == null)
            throw new NullPointerException("author must not be null");

        SqlSession session = SessionManager.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByAuthor(author);
    }

    @Override
    public List<Book> findByCategory(Category category) throws Exception {
        if (category == null)
            throw new NullPointerException("category must not be null");

        SqlSession session = SessionManager.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        return mapper.findByCategory(category);
    }

}
