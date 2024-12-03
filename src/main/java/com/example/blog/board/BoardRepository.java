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

    // 241203
    public Optional<Board> findByIdJoinUserAndReply(int id) {
        // 조인 결과가 하나의 테이블 / JPQL쿼리 사용
        // fetch가 빠지면 user테이블이 null이라 콘솔 안나옴
        String sql = """ 
                select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id = :id
                """;
        Query q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);

        try { // 해당 try문은 이상한 구조로 실제 적용X. 참고만 하기
            Board board = (Board) q.getSingleResult();
            return Optional.ofNullable(board);

        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

    // 1202 Join > service 메서드 교체
    public Optional<Board> findByIdJoinUser(int id) {
        // 조인 결과가 하나의 테이블 / JPQL쿼리 사용
        // fetch가 빠지면 user테이블이 null이라 콘솔 안나옴
        String sql = """ 
                select b from Board b join fetch b.user where b.id = :id
                """;
        Query q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);

        try { // 해당 try문은 이상한 구조로 실제 적용X. 참고만 하기
            Board board = (Board) q.getSingleResult();
            return Optional.ofNullable(board);

        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

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








