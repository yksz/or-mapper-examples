package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Foo;

public interface FooMapper {

    void createTable();
    void dropTable();
    void save(Foo foo);
    @Select("SELECT * FROM foo")
    List<Foo> findAll();

}
