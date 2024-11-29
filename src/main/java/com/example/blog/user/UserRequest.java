package com.example.blog.user;

import lombok.Data;

public class UserRequest {

    // DTO는 불필요한 데이터 빼고 필요한 데이터만

    // 로그인에 쓸 LoginDTO
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    // 회원가입
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }
}