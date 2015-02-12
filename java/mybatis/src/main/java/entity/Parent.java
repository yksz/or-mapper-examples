package entity;

import java.util.List;

public class Parent {

    private Integer id;
    private String name;
    private List<Child> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Parent [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", children=");
        builder.append(children);
        builder.append("]");
        return builder.toString();
    }

}
