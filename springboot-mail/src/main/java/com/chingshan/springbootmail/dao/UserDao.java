package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.Role;
import com.chingshan.springbootmail.model.MemberUser;

import java.util.List;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    MemberUser getUserById(Integer userId);

    MemberUser getUserByEmail(String email);

    // 權限相關
    List<Role> getRolesByMemberId(Integer memberId);

    void addRoleForMemberId(Integer memberId, Role role);
}
