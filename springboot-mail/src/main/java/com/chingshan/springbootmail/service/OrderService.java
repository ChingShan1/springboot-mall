package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.dto.CreateOrderRequest;
import com.chingshan.springbootmail.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderBuId(Integer orderId);

}
