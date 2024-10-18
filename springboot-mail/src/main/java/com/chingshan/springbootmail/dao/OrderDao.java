package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.OrderQueryParams;
import com.chingshan.springbootmail.model.Order;
import com.chingshan.springbootmail.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    Integer oauth2_createOrder(Integer userId, Integer totalAmount);


    void CreateOrderItem(Integer orderId, List<OrderItem> orderItemList);

    void oauth2_CreateOrderItem(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    Order oauth2_getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    List<OrderItem> oauth2_getOrderItemsByOrderId(Integer orderId);

    List<Order> getOrder(OrderQueryParams orderQueryParams);

    List<Order> oauth2_getOrder(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
    Integer oauth2_countOrder(OrderQueryParams orderQueryParams);
}
