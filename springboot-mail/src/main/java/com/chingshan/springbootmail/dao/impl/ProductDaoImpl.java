package com.chingshan.springbootmail.dao.impl;

import com.chingshan.springbootmail.constant.ProductCategory;
import com.chingshan.springbootmail.dao.ProductDao;
import com.chingshan.springbootmail.dto.ProductQueryParams;
import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;
import com.chingshan.springbootmail.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date " +
                "FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        if(productQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch() != null) {
            sql = sql+" AND product_name LIKE :search";
            map.put("search", "%" +productQueryParams.getSearch() + "%");
        }

        // 排序
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name ,category, image_url, price, stock, description, created_date, last_modified_date FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0 ){
            return productList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name ,category, image_url, price, stock," +
                "description, created_date, last_modified_date) "+
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description," +
                ":created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageURL());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, " +
                "image_url = :imageUrl, price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageURL());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Integer getProductCount(ProductQueryParams productQueryParams) {

        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        // 查詢條件
        // 舊寫法
//        if(productQueryParams.getCategory() != null) {
//            sql = sql + " AND category = :category";
//            map.put("category", productQueryParams.getCategory().name());
//        }
//
//        if(productQueryParams.getSearch() != null) {
//            sql = sql+" AND product_name LIKE :search";
//            map.put("search", "%" +productQueryParams.getSearch() + "%");
//        }

        sql =  addFilteringSql(sql, map, productQueryParams);


        //.queryForObject() 通常用來取count的值
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class); // 第三個參數，接返回值
        return total;
    }

    // 查詢條件
    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams) {
        if(productQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }

        if(productQueryParams.getSearch() != null) {
            sql = sql+" AND product_name LIKE :search";
            map.put("search", "%" +productQueryParams.getSearch() + "%");
        }
        return sql;
    }


    @Override
    public void updateStock(Integer productId, Integer stock) {
       String sql =  "UPDATE product SET stock = :stock, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();

        map.put("productId", productId);
        map.put("stock", stock);
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
