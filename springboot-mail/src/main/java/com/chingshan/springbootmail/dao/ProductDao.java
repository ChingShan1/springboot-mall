package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


}
