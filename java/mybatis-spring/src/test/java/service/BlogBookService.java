package service;

import java.util.List;

import entity.Blog;
import entity.Book;

public interface BlogBookService {

    void save(Blog blog, Book book, int throwable) throws Exception;
    List<Blog> findAllBlogs() throws Exception;
    List<Book> findAllBooks() throws Exception;

}
