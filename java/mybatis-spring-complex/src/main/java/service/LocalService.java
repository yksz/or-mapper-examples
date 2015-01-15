package service;

import java.util.List;

import entity.Local;

public interface LocalService {

    void save(Local local) throws Exception;
    List<Local> findAll() throws Exception;
    List<Local> findByName(String name) throws Exception;

}
