package service;

import java.util.List;

import mapper.ProductMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import entity.Product;

public class ProductServiceImpl implements ProductService, InitializingBean {

    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        productMapper.createTable();
    }

    @Transactional("remote")
    public void save(Product Product) throws Exception {
        productMapper.save(Product);
    }

    @Transactional(value = "remote", readOnly = true)
    public List<Product> findAll() throws Exception {
        return productMapper.findAll();
    }

    @Transactional(value = "remote", readOnly = true)
    public List<Product> findByName(String name) throws Exception {
        return productMapper.findByName(name);
    }

    @Transactional(value = "remote", readOnly = true)
    public List<Product> findByPrice(int price) throws Exception {
        return productMapper.findByPrice(price);
    }

}
