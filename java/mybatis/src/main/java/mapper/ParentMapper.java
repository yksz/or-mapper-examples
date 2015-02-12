package mapper;

import java.util.List;

import entity.Parent;

public interface ParentMapper {

    void createTable();
    Integer saveParent(Parent parent);
    List<Parent> findAllParents();
    List<Parent> findParentsByName(String name);

}
