package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public void delete(int id){
        em.createQuery("delete from Board b where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void save(Board board){
        // 비영속
        em.persist(board);
        // 동기화 완료 (영속화됨)
    }

    // 홈 버튼 눌렀을 때 => 글 목록 보이는 화면
    public List<Board> findAll(){
        return em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }

    // Optional로 null 처리
    public Optional<Board> findById(int id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }
}








