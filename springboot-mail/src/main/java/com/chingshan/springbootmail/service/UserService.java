package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

}
