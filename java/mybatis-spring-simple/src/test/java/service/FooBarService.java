package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.FooMapper;
import mapper.BarMapper;
import entity.Foo;
import entity.Bar;

@Service
public class FooBarService {

    @Autowired
    private FooMapper fooMapper;
    @Autowired
    private BarMapper barMapper;

    @Transactional
    public void save(Foo foo, Bar bar, Exception exception) throws Exception {
        fooMapper.save(foo);
        barMapper.save(bar);
        if (exception != null)
            throw exception;
    }

    @Transactional(readOnly = true)
    public List<Foo> findFoo() throws Exception {
        return fooMapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Bar> findBar() throws Exception {
        return barMapper.findAll();
    }

}
