package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Local;

public interface LocalMapper {

    void createTable();
    void dropTable();
    void save(Local local);
    @Select("SELECT * FROM local")
    List<Local> findAll();
    List<Local> findByName(String name);

}
