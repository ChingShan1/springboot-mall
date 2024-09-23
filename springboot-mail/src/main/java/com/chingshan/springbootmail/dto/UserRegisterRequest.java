package com.chingshan.springbootmail.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {

    // @NotBlank = 不等於NUlL和空白值
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
