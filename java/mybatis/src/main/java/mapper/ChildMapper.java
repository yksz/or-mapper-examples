package mapper;

import java.util.List;

import entity.Child;

public interface ChildMapper {

    void createTable();
    Integer saveChild(Child child);
    List<Child> findAllChildren();
    List<Child> findChildrenByName(String name);

}
