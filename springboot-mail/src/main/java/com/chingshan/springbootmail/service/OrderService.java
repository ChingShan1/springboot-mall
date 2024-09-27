package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.dto.CreateOrderRequest;
import com.chingshan.springbootmail.dto.OrderQueryParams;
import com.chingshan.springbootmail.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderBuId(Integer orderId);

    List<Order> getOrder(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

}
