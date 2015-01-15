package service;

import java.util.List;

import entity.Remote;

public interface RemoteService {

    void save(Remote remote) throws Exception;
    List<Remote> findAll() throws Exception;
    List<Remote> findByName(String name) throws Exception;

}
