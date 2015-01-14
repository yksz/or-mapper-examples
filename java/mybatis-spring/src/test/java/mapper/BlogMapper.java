package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Blog;

public interface BlogMapper {

    void createTable();
    void save(Blog blog);
    @Select("SELECT * FROM blog")
    List<Blog> findAll();
    List<Blog> findByTitle(String title);
    List<Blog> findByAuthor(String author);

}
