package com.example.blog.user;

import com.example.blog._core.error.ex.Exception401;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    // 로그인
    public User findByUsername(String username) {
        // createQuery 사용 / jpql => :이 ?역할
        Query q = em.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);

        try {
            return (User) q.getSingleResult();

        } catch (Exception e) {
            throw new Exception401("아이디를 찾을 수 없습니다.");
        }
    }

    // 회원가입
//    public void save (User user) {
//
//    }
}