import java.util.List;

import entity.Child;
import entity.Parent;
import service.ChildService;
import service.ChildServiceImpl;
import service.ParentService;
import service.ParentServiceImpl;

public class AssociationSample {

    public static void main(String[] args) throws Exception {
        Parent parent = new Parent();
        parent.setName("foo");
        Child child = new Child();
        child.setName("bar");
        child.setParent(parent);

        ParentService parentService = new ParentServiceImpl();
        ChildService childService = new ChildServiceImpl();
        parentService.save(parent);
        childService.save(child);
        show(parentService.findAllParents(), "findAllParents:");
        show(parentService.findParentsByName("foo"), "findParentsByName:");
        show(childService.findAllChildren(), "findAllChildren:");
        show(childService.findChildrenByName("bar"), "findChildrenByName:");
    }

    static <T> void show(List<T> objs, String msg) {
        System.out.println(msg);
        for (T obj : objs)
            System.out.println(obj);
    }

}
