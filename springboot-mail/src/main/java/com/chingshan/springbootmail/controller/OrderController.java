package com.chingshan.springbootmail.controller;


import com.chingshan.springbootmail.dto.CreateOrderRequest;
import com.chingshan.springbootmail.dto.OrderQueryParams;
import com.chingshan.springbootmail.model.Order;
import com.chingshan.springbootmail.service.OrderService;
import com.chingshan.springbootmail.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                              @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderBuId(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @PostMapping("/google-users/{oauth2_id}/orders")
    public ResponseEntity<?> google_createOrder(@PathVariable Integer oauth2_id,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(oauth2_id, createOrderRequest);

        Order order = orderService.getOrderBuId(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);


    }


    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0)Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        List<Order> orderList = orderService.getOrder(orderQueryParams);

        Integer count = orderService.countOrder(orderQueryParams);

        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);


    }



}
