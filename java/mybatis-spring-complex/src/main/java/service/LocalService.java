package service;

import java.util.List;

import entity.Local;

public interface LocalService {

    Integer save(Local local) throws Exception;
    List<Local> findAll() throws Exception;
    List<Local> findByName(String name) throws Exception;

}
