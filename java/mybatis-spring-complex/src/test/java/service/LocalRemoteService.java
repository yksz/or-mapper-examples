package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import mapper.LocalMapper;
import mapper.RemoteMapper;
import entity.Local;
import entity.Remote;

public class LocalRemoteService {

    private LocalMapper localMapper;
    private RemoteMapper remoteMapper;

    public void setLocalMapper(LocalMapper localMapper) {
        this.localMapper = localMapper;
    }

    public void setRemoteMapper(RemoteMapper remoteMapper) {
        this.remoteMapper = remoteMapper;
    }

    @Transactional
    public void save(Local local, Remote remote, Exception exception) throws Exception {
        localMapper.save(local);
        remoteMapper.save(remote);
        if (exception != null)
            throw exception;
    }

    @Transactional(readOnly = true)
    public List<Local> findLocal() throws Exception {
        return localMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Remote> findRemote() throws Exception {
        return remoteMapper.findAll();
    }

}
