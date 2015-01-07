package service;

import java.util.List;

import entity.Child;

public interface ChildService {

    void save(Child child) throws Exception;
    List<Child> findAllChildren() throws Exception;
    List<Child> findChildrenByName(String name) throws Exception;

}
