package com.boardexample.boarddemo.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Board {

    private Long id; //글 id
    private String title; //글제목
    private String content; //글내용
    private LocalDateTime date; //날짜
    private int hits; //조회수
}
