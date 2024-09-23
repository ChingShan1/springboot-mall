package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.constant.ProductCategory;
import com.chingshan.springbootmail.dto.ProductQueryParams;
import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;

import java.util.List;

public interface ProductService {

//    List<Product> getProducts(ProductCategory category, String search);
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
