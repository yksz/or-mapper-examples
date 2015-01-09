package service;

import java.util.List;

import entity.Product;

public interface ProductService {

    void save(Product product) throws Exception;
    List<Product> findAll() throws Exception;
    List<Product> findByName(String name) throws Exception;
    List<Product> findByPrice(int price) throws Exception;

}
