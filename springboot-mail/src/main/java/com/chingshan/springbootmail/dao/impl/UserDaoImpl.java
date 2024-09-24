package com.chingshan.springbootmail.dao.impl;

import com.chingshan.springbootmail.dao.UserDao;
import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.User;
import com.chingshan.springbootmail.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component // 讓他成為一個bean
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate NamedParameterJdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email, password, created_date, last_modified_date) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());


        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int UserId = keyHolder.getKey().intValue();
        return UserId;

    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM user WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        } else{
            return userList.get(1);
        }



    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date "+
                "FROM user WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        }else {
            return null;
        }
    }

    public org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return NamedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        NamedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
