package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.dao.ProductDao;
import com.chingshan.springbootmail.model.Product;
import com.chingshan.springbootmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
