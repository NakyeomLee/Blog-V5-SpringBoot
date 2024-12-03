package com.example.blog._core.util;

import com.example.blog.board.Board;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Encoding {

    public static String formatToStr(Board board){
        // when
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = sdf.format(board.getCreatedAt());
        return formattedDate;
    }
}
