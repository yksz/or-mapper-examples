package service;

import java.util.List;

import entity.Book;

public interface BookService {

    Long save(Book book) throws Exception;
    List<Book> findAll() throws Exception;
    List<Book> findByTitle(String title) throws Exception;
    List<Book> findByAuthor(String author) throws Exception;

}
