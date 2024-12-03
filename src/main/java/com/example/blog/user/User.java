package com.example.blog.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// Builder 문법
// @Builder는 @AllArgsConstructor가 있어야 사용 가능
// 컬렉션은 빌더 생성 불가 => 포함돼있으면 빌더에서 제외
// 풀 생성자 생성해놓고 @Builder 달아서 빌더 만듦 => 풀생성자 어노테이션 지우기

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 디폴트 생성자 생성하지 못하게 제한(protected)
@Table(name = "user_tb") // 테이블명
@Getter // private에 접근 하려면 getter 필요
@Entity // 엔티티, 이거 달아놔야 테이블 생성 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Integer id; // Null일것도 감안해서 Integer

    @Column(unique = true, nullable = false) // 컬럼제약조건 unique(index 알아서 만들어질것), not null
    private String username; // 유저 아이디는 username으로 약속되어있음, id 아님
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;
    
    // 풀 생성자 만들고 @Builder 달아서 빌더의 형태 잡음
    @Builder
    public User(Integer id, String password, String username, String email, Timestamp createdAt) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
}