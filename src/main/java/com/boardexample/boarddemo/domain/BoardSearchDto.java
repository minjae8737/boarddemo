package com.boardexample.boarddemo.domain;

import lombok.Data;

@Data
public class BoardSearchDto {
    private String searchWord; //검색어
    private SearchType searchType; //검색타입
}
