package com.chingshan.springbootmail.security;

import com.chingshan.springbootmail.dao.MemberDao;

import com.chingshan.springbootmail.dao.OAuth2MemberDao;
import com.chingshan.springbootmail.model.Member;
import com.chingshan.springbootmail.model.OAuth2Member;
import com.chingshan.springbootmail.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

//    @Autowired
//    private OAuth2MemberDao oAuth2MemberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫中查詢 Member 數據
        Member member = memberDao.getMemberByEmail(username);
//        OAuth2Member oAuth2Member = oAuth2MemberDao.g

        if (member == null) {
            throw new UsernameNotFoundException("Member not found for: " + username);

        } else {

            String memberEmail = member.getEmail();
            String memberPassword = member.getPassword();

            // 權限部分
            List<Role> roleList = memberDao.getRolesByMemberId(member.getMemberId());

            List<GrantedAuthority> authorities = convertToAuthorities(roleList);

            // 轉換成 Spring Security 指定的 User 格式
            return new User(memberEmail, memberPassword, authorities);
        }
    }

    private List<GrantedAuthority> convertToAuthorities(List<Role> roleList) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }
}
