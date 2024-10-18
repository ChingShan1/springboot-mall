package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.dao.OAuth2MemberDao;
import com.chingshan.springbootmail.dao.OrderDao;
import com.chingshan.springbootmail.dao.ProductDao;
import com.chingshan.springbootmail.dao.UserDao;
import com.chingshan.springbootmail.dto.BuyItem;
import com.chingshan.springbootmail.dto.CreateOrderRequest;
import com.chingshan.springbootmail.dto.OrderQueryParams;
import com.chingshan.springbootmail.model.*;
import com.chingshan.springbootmail.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OAuth2MemberDao oAuth2MemberDao;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Transactional // 確保資料庫同時發生or同時不發生: 確保同時新增成功or同時失敗，這樣才不會只成功一個就放入訂單
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        // 檢查 User 是否存在
//        MemberUser memberUser = userDao.getUserById(userId);
        OAuth2Member oAuth2Member = oAuth2MemberDao.getOAuth2MemberId(userId);

//        if (memberUser == null && oAuth2Member == null) {
//            log.warn("該 userId {} 不存在", userId);
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
        if (oAuth2Member == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for(BuyItem buyItem: createOrderRequest.getBuyitemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            // 檢查 Product 是否存在，庫存是否足夠
            if(product == null){
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 庫存數量不夠，無法購買， 剩餘庫存 {}, 欲購買數量 {}"
                    , buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            // 扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() -  buyItem.getQuantity());



            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount =totalAmount + amount;

            // 轉換BuyItem to OrderItem
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

    @Override
    public List<Order> getOrder(OrderQueryParams orderQueryParams) {

        List<Order> orderList = orderDao.getOrder(orderQueryParams);

        for(Order order: orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemsList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }
}
