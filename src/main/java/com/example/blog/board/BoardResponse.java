package com.example.blog.board;

import com.example.blog._core.util.Encoding;
import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class BoardResponse {

    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = Encoding.formatToStr(board);
        }
    }

    // 글 상세보기
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        private Integer userId; // 게시글을 작성한 userId
        private String username;
        private boolean isOwner = false;

        private List<ReplyDTO> replies;

        // 내부 DTO(Class)는 public 붙이지 않기 - 외부에서 모르고 꺼내쓸 수 있기때문
        @Data
        class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성한 userId
            private String username; // 댓글 작성한 username(아이디)

            // 생성자
            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
            }
        }

        // 생성자
        public DetailDTO(Board board, User sessionUser) { // sessionUser는 권한 확인을 위해
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = Encoding.formatToStr(board);

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // lazy loading
            if(sessionUser != null) {
                this.isOwner = sessionUser.getId() == board.getUser().getId();
            }
            this.replies = board.getReplies().stream().map(r -> new ReplyDTO(r)).toList();

        }
    }

    @Data
    public static class DTO {
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
