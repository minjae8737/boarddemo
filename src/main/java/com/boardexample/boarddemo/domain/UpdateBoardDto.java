package com.boardexample.boarddemo.domain;

import lombok.Data;

@Data
public class UpdateBoardDto {
    private String title; //글제목
    private String content; //글내용
}
