package jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Product.FIND_BY_NAMED_QUERY, query = "SELECT p FROM Product p WHERE p.id = ?1")
@NamedNativeQuery(name = Product.FIND_BY_NAMED_NATIVE_QUERY, query = "SELECT * FROM Product WHERE id = ?", resultClass = Product.class)
public class Product {

    public static final String FIND_BY_NAMED_QUERY = "jpa.entity.Product.findByNamedQuery";
    public static final String FIND_BY_NAMED_NATIVE_QUERY = "jpa.entity.Product.findByNamedNativeQuery";

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

}
