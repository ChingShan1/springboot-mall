package com.chingshan.springbootmail.dao.impl;

import com.chingshan.springbootmail.dao.UserDao;
import com.chingshan.springbootmail.dto.UserRegisterRequest;
import com.chingshan.springbootmail.model.Role;
import com.chingshan.springbootmail.model.MemberUser;
import com.chingshan.springbootmail.rowmapper.RoleRowMapper;
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

    @Autowired
    private RoleRowMapper roleRowMapper;

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
    public MemberUser getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM user WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<MemberUser> memberUserList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(memberUserList.size() > 0){
            return memberUserList.get(0);
        } else{
            return memberUserList.get(1);
        }



    }

    @Override
    public MemberUser getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date "+
                "FROM user WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<MemberUser> memberUserList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(memberUserList.size() > 0){
            return memberUserList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<Role> getRolesByMemberId(Integer memberId) {
        String sql = """
                SELECT role.role_id, role.role_name FROM role
                    JOIN member_has_role ON role.role_id = member_has_role.role_id
                    WHERE member_has_role.member_id = :memberId
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);

        List<Role> roleList = namedParameterJdbcTemplate.query(sql, map, roleRowMapper);

        return roleList;
    }

    @Override
    public void addRoleForMemberId(Integer memberId, Role role) {
        String sql = "INSERT INTO member_has_role(member_id, role_id) VALUES (:memberId, :roleId)";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("roleId", role.getRoleId());

        namedParameterJdbcTemplate.update(sql, map);
    }

    public org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return NamedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        NamedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
