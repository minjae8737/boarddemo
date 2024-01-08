package com.boardexample.boarddemo.repository;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Board {

    private Long id; //글 id
    private String title; //글제목
    private String content; //글내용
    private LocalDate date; //날짜
    private int hits; //조회수
}
