package com.example.blog.board;

import com.example.blog._core.util.Encoding;
import com.example.blog.user.User;
import lombok.Data;

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

        private Integer userId;
        private String username;
        boolean isOwner = false;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = Encoding.formatToStr(board);

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // lazy loading
            if(sessionUser != null) {
                this.isOwner = sessionUser.getId() == board.getUser().getId();
            }
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
