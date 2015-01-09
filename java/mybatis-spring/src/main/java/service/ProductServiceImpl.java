package service;

import java.util.List;

import mapper.ProductMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Transactional("remote")
    public void save(Product Product) throws Exception {
        productMapper.createTable();
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
