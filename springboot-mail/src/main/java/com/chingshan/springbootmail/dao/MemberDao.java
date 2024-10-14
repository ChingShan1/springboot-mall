package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.model.Member;
import com.chingshan.springbootmail.model.Role;

import java.util.List;

public interface MemberDao {

    // 基本 Member 操作
    Member getMemberByEmail(String email);

    Integer createMember(Member member);

    // 權限相關
    List<Role> getRolesByMemberId(Integer memberId);

    void addRoleForMemberId(Integer memberId, Role role);

    void removeRoleFromMemberId(Integer memberId, Role role);
}
