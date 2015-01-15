package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Book;

public interface BookMapper {

    void save(Book book);
    @Select("SELECT * FROM book")
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
