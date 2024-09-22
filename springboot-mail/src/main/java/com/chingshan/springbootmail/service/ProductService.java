package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
