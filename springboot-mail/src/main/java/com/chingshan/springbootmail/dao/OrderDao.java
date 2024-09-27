package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.OrderQueryParams;
import com.chingshan.springbootmail.model.Order;
import com.chingshan.springbootmail.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void CreateOrderItem(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    List<Order> getOrder(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
