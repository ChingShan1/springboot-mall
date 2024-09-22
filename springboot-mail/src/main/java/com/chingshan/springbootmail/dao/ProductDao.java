package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
