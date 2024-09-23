package com.chingshan.springbootmail.rowmapper;

import com.chingshan.springbootmail.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet re, int i) throws SQLException {
        User user = new User();
        user.setUser_id(re.getInt("user_id"));
        user.setMail(re.getString("email"));
        user.setPassword(re.getString("password"));
        user.setCreatedDate(re.getTimestamp("created_date"));
        user.setLastModifiedDate(re.getTimestamp("last_modified_date"));
        return user;
    }
}
