package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.model.OAuth2Member;
import com.chingshan.springbootmail.model.Role;

public interface OAuth2MemberDao {
    OAuth2Member getOAuth2Member(String provider, String providerId);

    OAuth2Member getOAuth2MemberId(int oauth2_member_id);

    OAuth2Member getOAuth2MemberId_int(OAuth2Member OAuth2Member);

    Integer createOAuth2Member(OAuth2Member OAuth2Member);



    void oauth2_addRoleForMemberId(Integer oauth2_member_id, Role role);
}
