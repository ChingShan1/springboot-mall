package com.chingshan.springbootmail.service;

import com.chingshan.springbootmail.dto.UserLoginRequest;
import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.MemberUser;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    MemberUser getUserById(Integer userId);

    MemberUser login(UserLoginRequest userLoginRequest);
}
