package entity.unidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "entity.unidirectional.LazyParent")
@Table(name = "UNI_LAZY_PARENT")
public class LazyParent {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<LazyChild> children;

    public LazyParent() {
        children = new ArrayList<LazyChild>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LazyChild> getChildren() {
        return children;
    }

    public void addChild(LazyChild child) {
        children.add(child);
    }

    public void removeChild(LazyChild child) {
        children.remove(child);
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
