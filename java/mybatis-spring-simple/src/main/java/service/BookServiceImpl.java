package service;

import java.util.List;

import mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Book;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Transactional
    public Integer save(Book book) throws Exception {
        bookMapper.save(book);
        return book.getId();
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
