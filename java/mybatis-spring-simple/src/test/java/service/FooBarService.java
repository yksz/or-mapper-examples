package service;

import java.util.List;

import entity.Bar;
import entity.Foo;

public interface FooBarService {

    void save(Foo foo, Bar bar, Exception exception) throws Exception;
    List<Foo> findFoo() throws Exception;
    List<Bar> findBar() throws Exception;

}
