package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.dao.OrderDao;
import com.chingshan.springbootmail.dao.ProductDao;
import com.chingshan.springbootmail.dto.BuyItem;
import com.chingshan.springbootmail.dto.CreateOrderRequest;
import com.chingshan.springbootmail.model.Order;
import com.chingshan.springbootmail.model.OrderItem;
import com.chingshan.springbootmail.model.Product;
import com.chingshan.springbootmail.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional // 確保資料庫同時發生or同時不發生: 確保同時新增成功or同時失敗，這樣才不會只成功一個就放入訂單
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for(BuyItem buyItem: createOrderRequest.getBuyitemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount =totalAmount + amount;

            OrderItem orderitem = new OrderItem();
            orderitem.setProductId(buyItem.getProductId());
            orderitem.setQuantity(buyItem.getQuantity());
            orderitem.setAmount(amount);

            orderItemList.add(orderitem);
        }


        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.CreateOrderItem(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderBuId(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemsList(orderItemList);
        return order;
    }
}
