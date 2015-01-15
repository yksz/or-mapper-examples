package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Remote;

public interface RemoteMapper {

    void createTable();
    void dropTable();
    void save(Remote remote);
    @Select("SELECT * FROM remote")
    List<Remote> findAll();
    List<Remote> findByName(String name);

}
