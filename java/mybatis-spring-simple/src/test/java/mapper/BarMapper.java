package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Bar;

public interface BarMapper {

    void createTable();
    void dropTable();
    void save(Bar bar);
    @Select("SELECT * FROM bar")
    List<Bar> findAll();

}
