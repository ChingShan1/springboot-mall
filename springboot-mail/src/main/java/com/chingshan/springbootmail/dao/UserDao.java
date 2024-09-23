package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
