package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.model.Product;

public interface ProductDao {
    Product getProductById(Integer id);
}
