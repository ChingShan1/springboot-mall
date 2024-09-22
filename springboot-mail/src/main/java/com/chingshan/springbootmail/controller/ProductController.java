package com.chingshan.springbootmail.controller;

import com.chingshan.springbootmail.constant.ProductCategory;
import com.chingshan.springbootmail.dto.ProductRequest;
import com.chingshan.springbootmail.model.Product;
import com.chingshan.springbootmail.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search
    ){
        List<Product> ProductList = productService.getProducts(category, search);
        return ResponseEntity.status(HttpStatus.OK).body(ProductList);
    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable @Valid Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
       // 檢查product 是否存在
      Product product = productService.getProductById(productId);
      if(product == null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
        // 修改商品的數據
      productService.updateProduct(productId, productRequest);

      Product updateProduct = productService.getProductById(productId);
      return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProcduct(@PathVariable @Valid Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
