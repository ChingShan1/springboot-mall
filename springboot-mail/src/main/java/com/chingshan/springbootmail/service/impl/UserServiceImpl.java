package com.chingshan.springbootmail.service.impl;

import com.chingshan.springbootmail.dao.RoleDao;
import com.chingshan.springbootmail.dao.UserDao;
import com.chingshan.springbootmail.dto.UserLoginRequest;
import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.MemberUser;
import com.chingshan.springbootmail.model.Role;
import com.chingshan.springbootmail.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    // 制式化
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊的email
        MemberUser memberUser = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(memberUser != null){
            log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        // hash 原始密碼
        String hashedPassword = passwordEncoder.encode(memberUser.getPassword());
        userRegisterRequest.setPassword(hashedPassword);
//        // 加鹽 salt
//        String salt = "salt";
//        String orginPassword = userRegisterRequest.getPassword();
//        orginPassword = orginPassword+salt;
//        // 使用MD5 生成密碼的hash值
//        String hashedPassword = DigestUtils.md5DigestAsHex(orginPassword.getBytes());


        // 為 Member 添加預設的 Role
        Role normalRole = roleDao.getRoleByName("ROLE_NORMAL_MEMBER");
        int memberId = userDao.createUser(userRegisterRequest);
        userDao.addRoleForMemberId(memberId, normalRole);

        // 創建帳號
        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public MemberUser getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public MemberUser login(UserLoginRequest userLoginRequest) {
        MemberUser memberUser = userDao.getUserByEmail(userLoginRequest.getEmail());

        // 檢查 user 是否存在
        if (memberUser == null){
            log.warn("該 email {} 已經沒有被註冊", userLoginRequest.getEmail());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // hash 原始密碼
        String HashPassword = passwordEncoder.encode(memberUser.getPassword());
//        // 使用MD5生成密碼的雜湊值
//        String salt = "salt";
//        String orginLoginPassword = userLoginRequest.getPassword();
//        orginLoginPassword = orginLoginPassword+salt;
//        String HashPassword = DigestUtils.md5DigestAsHex(orginLoginPassword.getBytes());


        // 比較密碼
        // 在java中 一定要.equals()來比較字串，不能使用 "=="
        if(memberUser.getPassword().equals(HashPassword)){
            return memberUser;
        } else{
            log.warn("該 email {} 密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
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
