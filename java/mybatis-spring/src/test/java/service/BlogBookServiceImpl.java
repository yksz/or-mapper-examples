package service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.BlogMapper;
import mapper.BookMapper;
import entity.Blog;
import entity.Book;

@Service
public class BlogBookServiceImpl implements BlogBookService, InitializingBean {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        blogMapper.createTable();
        bookMapper.createTable();
    }

    @Transactional
    public void save(Blog blog, Book book, int throwable) throws Exception {
        blogMapper.save(blog);
        bookMapper.save(book);
        switch (throwable) {
        case 1:
            throw new RuntimeException();
        case 2:
            throw new Exception();
        }
    }

    @Transactional(readOnly = true)
    public List<Blog> findAllBlogs() throws Exception {
        return blogMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() throws Exception {
        return bookMapper.findAll();
    }

}
