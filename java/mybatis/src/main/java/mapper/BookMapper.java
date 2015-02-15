package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Book;
import entity.Book.Category;

public interface BookMapper {

    void createTable();
    void save(Book book);
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategory(@Param("category") Category category);

}
