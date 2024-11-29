package com.example.blog.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        public Board toEntity(){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {

        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

}
