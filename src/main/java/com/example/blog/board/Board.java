package com.example.blog.board;

import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 빈 생성자 생성하지 못하게 제한(protected)
@Getter
@Table(name = "board_tb") // 테이블 명
@Entity // Board 객체를 테이블로 생성하며, 데이터베이스 테이블과 매핑하여 데이터베이스 작업(insert, update, delete 등)을 수행
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    private Integer id;
    private String title;
    private String content;

    // 241129 User 필드 추가
    // 하이버네이트 문법 규칙
    // 연관 관계 설정해놓은 User 객체(user_tb 테이블) 자체를 들고오는 것이 아니라
    // user_tb 테이블의 PK (user_id(필드로는 userId))를 들고옴
    // 연관 관계 설정 (User 쪽이 1이니까 ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY) // LAZY와 EAGER일때 콘솔 출력되는 쿼리 다름
    private User user;

    // 241203 추가
    @OneToMany(mappedBy = "board") // fk의 변수명이 뭐야?
    private List<Reply> replies;

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

    // update(글 수정)를 setter로 만들어서 활용하기로 함
    // setter는 명확한 의도가 있는것만 해놓는게 좋기 때문에
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
