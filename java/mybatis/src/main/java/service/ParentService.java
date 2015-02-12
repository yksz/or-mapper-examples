package service;

import java.util.List;

import entity.Parent;

public interface ParentService {

    Integer saveParent(Parent parent) throws Exception;
    List<Parent> findAllParents() throws Exception;
    List<Parent> findParentsByName(String name) throws Exception;

}
