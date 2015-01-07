package dao;

import java.util.List;

import entity.Book;

public interface BookDao extends Dao<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
