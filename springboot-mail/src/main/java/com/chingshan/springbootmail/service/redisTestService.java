package com.chingshan.springbootmail.service;

public interface redisTestService {
     void saveUser(String userId, String user);

     Object getUser(String userId);

     void deleteUser(String userId);
}
