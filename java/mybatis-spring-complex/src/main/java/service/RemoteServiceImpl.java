package service;

import java.util.List;

import mapper.RemoteMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import entity.Remote;

public class RemoteServiceImpl implements RemoteService, InitializingBean {

    private RemoteMapper remoteMapper;

    public void setRemoteMapper(RemoteMapper remoteMapper) {
        this.remoteMapper = remoteMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        remoteMapper.createTable();
    }

    @Transactional("remote")
    public Integer save(Remote remote) throws Exception {
        remoteMapper.save(remote);
        return remote.getId();
    }

    @Transactional(value = "remote", readOnly = true)
    public List<Remote> findAll() throws Exception {
        return remoteMapper.findAll();
    }

    @Transactional(value = "remote", readOnly = true)
    public List<Remote> findByName(String name) throws Exception {
        return remoteMapper.findByName(name);
    }

}
