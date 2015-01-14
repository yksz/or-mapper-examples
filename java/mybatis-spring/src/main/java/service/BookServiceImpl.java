package service;

import java.util.List;

import mapper.BookMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import entity.Book;

public class BookServiceImpl implements BookService, InitializingBean {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        bookMapper.createTable();
    }

    @Transactional
    public void save(Book book) throws Exception {
        bookMapper.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() throws Exception {
        return bookMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByTitle(String title) throws Exception {
        return bookMapper.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthor(String author) throws Exception {
        return bookMapper.findByAuthor(author);
    }

}
