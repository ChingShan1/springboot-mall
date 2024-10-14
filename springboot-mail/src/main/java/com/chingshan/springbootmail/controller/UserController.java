package com.chingshan.springbootmail.controller;

import com.chingshan.springbootmail.dao.UserDao;
import com.chingshan.springbootmail.dto.UserLoginRequest;
import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.MemberUser;
import com.chingshan.springbootmail.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    //email和密碼 隱私資訊需要用request body來傳遞
    @PostMapping("users/register")
    public ResponseEntity<MemberUser> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        Integer userId =  userService.register(userRegisterRequest);
        MemberUser memberUser = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberUser);
    }

    @PostMapping("users/login")
    public ResponseEntity<MemberUser> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        MemberUser memberUser = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(memberUser);

    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
//    @PostMapping("/register")
//    public String register(@RequestBody Member member) {
//        // 省略參數檢查 (ex: email 是否被註冊過)
//
//        // hash 原始密碼
//        String hashedPassword = passwordEncoder.encode(member.getPassword());
//        member.setPassword(hashedPassword);
//
//        // 在資料庫中插入 Member 數據
//        Integer memberId = userDao.createUser(member);
//
//        // 為 Member 添加預設的 Role
//        Role normalRole = roleDao.getRoleByName("ROLE_NORMAL_MEMBER");
//        memberDao.addRoleForMemberId(memberId, normalRole);
//
//        return "註冊成功";
//    }
}
