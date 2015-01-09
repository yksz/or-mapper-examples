package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import entity.Product;

public interface ProductMapper {

    void createTable();
    void save(Product product);
    @Select("SELECT * FROM product")
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByPrice(int price);

}
