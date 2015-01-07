package entity.bidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

@Entity(name = "entity.bidirectional.EagerParent")
@Table(name = "BI_EAGER_PARENT")
public class EagerParent {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SUBSELECT)
    private List<EagerChild> children;

    public EagerParent() {
        children = new ArrayList<EagerChild>();
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

    public List<EagerChild> getChildren() {
        return children;
    }

    public void addChild(EagerChild child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(EagerChild child) {
        children.remove(child);
        child.setParent(null);
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
