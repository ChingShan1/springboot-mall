package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.constant.ProductCategory;
import com.chingshan.springbootmail.dao.ProductDao;
import com.chingshan.springbootmail.dto.ProductQueryParams;
import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;
import com.chingshan.springbootmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

//    @Override
//    public List<Product> getProducts(ProductCategory category, String search) {
//        return productDao.getProducts(category, search);
//    }


    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    @Override
    public Integer getProductCount(ProductQueryParams productQueryParams) {
        return productDao.getProductCount(productQueryParams);

    }
}
