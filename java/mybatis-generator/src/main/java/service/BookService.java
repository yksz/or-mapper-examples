package service;

import java.util.List;

import entity.Book;

public interface BookService {

    void save(Book book) throws Exception;
    List<Book> findAll() throws Exception;

}
