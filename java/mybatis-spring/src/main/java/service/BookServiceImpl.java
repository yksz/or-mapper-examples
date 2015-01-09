package service;

import java.util.List;

import mapper.BookMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Book;

@Service
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Transactional
    public void save(Book book) throws Exception {
        bookMapper.createTable();
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
