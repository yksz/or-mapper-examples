package org.example.repository;

import java.util.List;

import org.example.dao.entity.Book;
import org.example.dao.entity.Book.Category;

public interface BookRepository {

    Integer save(Book book) throws Exception;
    List<Book> findAll() throws Exception;
    List<Book> findByTitle(String title) throws Exception;
    List<Book> findByAuthor(String author) throws Exception;
    List<Book> findByCategory(Category category) throws Exception;

}
