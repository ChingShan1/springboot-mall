package com.chingshan.springbootmail.dao;

import com.chingshan.springbootmail.model.Role;

public interface RoleDao {
    Role getRoleByName(String roleName);
}
