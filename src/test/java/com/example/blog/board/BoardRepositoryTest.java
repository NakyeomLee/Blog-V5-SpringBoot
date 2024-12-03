package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(BoardRepository.class) // BoardRepository 클래스를 사용
@DataJpaTest // DB 관련된 자원들을 메모리(IoC)에 올림(JPA 관련 컴포넌트만 초기화)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdJoinUserAndReply_test() {
        int id = 1;

        boardRepository.findByIdJoinUserAndReply(id);
    }

    @Test
    public void findByfindByIdJoinUser_test() {
        int id = 1;

        boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test() {
        // given
        Integer id = 1;

        // when
        Optional<Board> boardOP = boardRepository.findById(id);
//        Board board = boardOP.get();

        // eye
//        System.out.println("Lazy Loading 직전");
//        // Board는 manyToOne 연관 관계에 의해 userId만 들고있고 userName은 없는 상태
//        // userName을 달라고 하면 연관 관계 속성이 Lazy지만
//        // userName을 select해서 줌
//        // 결론은 레이지로딩은 getter를 달라고할때 실행됨
//        String username = board.getUser().getUsername();
//        System.out.println("Lazy Loading 직후");
    }

    @Test
    public void findAll_test(){
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        System.out.println();

        // eye
        for(Board board : boardList){
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============");
        }
    }
}
