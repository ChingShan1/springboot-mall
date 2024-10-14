package com.chingshan.springbootmail.rowmapper;

import com.chingshan.springbootmail.model.MemberUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<MemberUser> {

    public MemberUser mapRow(ResultSet re, int i) throws SQLException {
        MemberUser memberUser = new MemberUser();
        memberUser.setUser_id(re.getInt("user_id"));
        memberUser.setMail(re.getString("email"));
        memberUser.setPassword(re.getString("password"));
        memberUser.setCreatedDate(re.getTimestamp("created_date"));
        memberUser.setLastModifiedDate(re.getTimestamp("last_modified_date"));
        return memberUser;
    }
}
