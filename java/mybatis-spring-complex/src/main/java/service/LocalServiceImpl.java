package service;

import java.util.List;

import mapper.LocalMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import entity.Local;

public class LocalServiceImpl implements LocalService, InitializingBean {

    private LocalMapper localMapper;

    public void setLocalMapper(LocalMapper localMapper) {
        this.localMapper = localMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        localMapper.createTable();
    }

    @Transactional
    public void save(Local local) throws Exception {
        localMapper.save(local);
    }

    @Transactional(readOnly = true)
    public List<Local> findAll() throws Exception {
        return localMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Local> findByName(String name) throws Exception {
        return localMapper.findByName(name);
    }

}
