package mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import mybatis.entity.Product;

public interface ProductMapper {

    void createTable();
    void save(Product product);
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findByAnnotation(int id);
    Product findByXML(int id);

}
