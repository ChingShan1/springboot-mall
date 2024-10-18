package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.model.OAuth2Member;

public interface OAuth2MemberDao {
    OAuth2Member getOAuth2Member(String provider, String providerId);

    OAuth2Member getOAuth2MemberId(int oauth2_member_id);

    Integer createOAuth2Member(OAuth2Member OAuth2Member);

}
