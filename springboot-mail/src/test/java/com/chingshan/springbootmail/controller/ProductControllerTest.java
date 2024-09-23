package com.chingshan.springbootmail.controller;

import com.chingshan.springbootmail.constant.ProductCategory;
import com.chingshan.springbootmail.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    // 查詢商品
    @Test
    public void getProduct_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())// 印出返回結果:常用
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.productName", equalTo("蘋果（澳洲）")))
                .andExpect((ResultMatcher) jsonPath("$.category", equalTo("FOOD")))
                .andExpect((ResultMatcher) jsonPath("$.imageUrl", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.price", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.stock", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.description", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));

    }

    private Object equalTo(String s) {
        return null;
    }

    @Test
    public void getProduct_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    // 創建商品
    @Transactional
    @Test
    public void createProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageURL("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect((ResultMatcher) jsonPath("$.productName", equalTo("test food product")))
                .andExpect((ResultMatcher) jsonPath("$.category", equalTo("FOOD")))
                .andExpect((ResultMatcher) jsonPath("$.imageUrl", equalTo("http://test.com")))
                .andExpect((ResultMatcher) jsonPath("$.price", equalTo(String.valueOf(100))))
                .andExpect((ResultMatcher) jsonPath("$.stock", equalTo(String.valueOf(2))))
                .andExpect((ResultMatcher) jsonPath("$.description", nullValue()))
                .andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));


    }

    @Transactional
    @Test
    public void createProduct_illegalArgument() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 更新商品
    @Transactional
    @Test
    public void updateProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageURL("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect((ResultMatcher) jsonPath("$.productName", equalTo("test food product")))
                .andExpect((ResultMatcher) jsonPath("$.category", equalTo("FOOD")))
                .andExpect((ResultMatcher) jsonPath("$.imageUrl", equalTo("http://test.com")))
                .andExpect((ResultMatcher) jsonPath("$.price", equalTo(String.valueOf(100))))
                .andExpect((ResultMatcher) jsonPath("$.stock", equalTo(String.valueOf(2))))
                .andExpect((ResultMatcher) jsonPath("$.description", nullValue()))
                .andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Transactional
    @Test
    public void updateProduct_illegalArgument() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }

    @Transactional
    @Test
    public void updateProduct_productNotFound() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageURL("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 20000)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    // 刪除商品
    @Transactional
    @Test
    public void deleteProduct_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/{productId}", 5);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Transactional
    @Test
    public void deleteProduct_deleteNonExistingProduct() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/{productId}", 20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    // 查詢商品列表
    @Test
    public void getProducts() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.limit", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.offset", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.total", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.results", hasSize(5)));
    }

    @Test
    public void getProducts_filtering() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("search", "B")
                .param("category", "CAR");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.limit", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.offset", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.total", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.results", hasSize(2)));
    }

    @Test
    public void getProducts_sorting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("orderBy", "price")
                .param("sort", "desc");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.limit", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.offset", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.total", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.results", hasSize(5)))
                .andExpect((ResultMatcher) jsonPath("$.results[0].productId", Matchers.equalTo(6)))
                .andExpect((ResultMatcher) jsonPath("$.results[1].productId", Matchers.equalTo(5)))
                .andExpect((ResultMatcher) jsonPath("$.results[2].productId", Matchers.equalTo(7)))
                .andExpect((ResultMatcher) jsonPath("$.results[3].productId", Matchers.equalTo(4)))
                .andExpect((ResultMatcher) jsonPath("$.results[4].productId", Matchers.equalTo(2)));
    }

    @Test
    public void getProducts_pagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("limit", "2")
                .param("offset", "2");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.limit", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.offset", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.total", notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.results", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$.results[0].productId", Matchers.equalTo(5)))
                .andExpect((ResultMatcher) jsonPath("$.results[1].productId", Matchers.equalTo(4)));
    }

}