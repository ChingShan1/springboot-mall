package com.chingshan.springbootmail.dao.impl;

import com.chingshan.springbootmail.dao.OAuth2MemberDao;
import com.chingshan.springbootmail.model.OAuth2Member;
import com.chingshan.springbootmail.model.Role;
import com.chingshan.springbootmail.rowmapper.OAuth2MemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2MemberDaoImpl implements OAuth2MemberDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private OAuth2MemberRowMapper oAuth2MemberRowMapper;

    @Override
    public OAuth2Member getOAuth2Member(String provider, String providerId) {
        String sql = """
                SELECT oauth2_member_id, provider, provider_id, name, email, access_token, expires_at
                FROM oauth2_member
                WHERE provider = :provider AND provider_id = :providerId
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("provider", provider);
        map.put("providerId", providerId);

        List<OAuth2Member> oAuth2MemberList = namedParameterJdbcTemplate.query(sql, map, oAuth2MemberRowMapper);

        if (oAuth2MemberList.size() > 0) {
            return oAuth2MemberList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public OAuth2Member getOAuth2MemberId(int oauth2_member_id) {
        String sql = """
                SELECT oauth2_member_id, provider, provider_id, name, email, access_token, expires_at
                FROM oauth2_member
                WHERE oauth2_member_id = :oauth2_member_id
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("oauth2_member_id", oauth2_member_id);

        List<OAuth2Member> oAuth2MemberList = namedParameterJdbcTemplate.query(sql, map, oAuth2MemberRowMapper);

        if (oAuth2MemberList.size() > 0) {
            return oAuth2MemberList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public OAuth2Member getOAuth2MemberId_int(OAuth2Member OAuth2Member) {
        String sql = """
                SELECT oauth2_member_id, provider, provider_id, name, email, access_token, expires_at
                FROM oauth2_member
                WHERE provider = :provider AND provider_id = :providerId
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("provider", OAuth2Member.getProvider());
        map.put("providerId", OAuth2Member.getProviderId());

        List<OAuth2Member> oAuth2MemberList = namedParameterJdbcTemplate.query(sql, map, oAuth2MemberRowMapper);

        if (oAuth2MemberList.size() > 0) {
            return oAuth2MemberList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Integer createOAuth2Member(OAuth2Member oAuth2Member) {
        String sql = """
                INSERT INTO oauth2_member(provider, provider_id, name, email, access_token, expires_at)
                VALUES (:provider, :providerId, :name, :email, :accessToken, :expiresAt)
                """;

        Map<String, Object> map = new HashMap<>();
        map.put("provider", oAuth2Member.getProvider());
        map.put("providerId", oAuth2Member.getProviderId());
        map.put("name", oAuth2Member.getName());
        map.put("email", oAuth2Member.getEmail());
        map.put("accessToken", oAuth2Member.getAccessToken());
        map.put("expiresAt", oAuth2Member.getExpiresAt());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int oauth2MemberId = keyHolder.getKey().intValue();

        return oauth2MemberId;
    }



    @Override
    public void oauth2_addRoleForMemberId(Integer oauth2_member_id, Role role) {
        String sql = "INSERT INTO oauth2_user_has_role(member_id, role_id) VALUES (:memberId, :roleId)";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", oauth2_member_id);
        map.put("roleId", role.getRoleId());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
