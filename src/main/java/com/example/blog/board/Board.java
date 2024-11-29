package com.example.blog.board;

import com.example.blog.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 빈 생성자 생성하지 못하게 제한(protected)
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // 하이버네이트 문법 규칙
    // 연관 관계 설정해놓은 User 객체(user_tb 테이블) 자체를 들고오는 것이 아니라
    // user_tb 테이블의 PK (user_id(필드로는 userId))를 들고옴
    // 연관 관계 설정 (User 쪽이 1이니까 ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY) // LAZY와 EAGER일때 콘솔 출력되는 쿼리 다름
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    // 풀 생성자 만들고 @Builder 달아서 빌더의 형태 잡음
    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    // update를 setter로 만들어서 활용하기로 함
    // setter는 명확한 의도가 있는것만 해놓는게 좋기 때문에
    // update를 여기다 만들어놓음
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
