package com.example.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article;       //댓글에 해당하는 게시글
    private String content;       //내용

    private LocalDateTime createAt; //생성일시
    private String createdBy;       //생성자
    private LocalDateTime modifiedAt;   //수정일시
    private String modifiedBy;      //수정자
}
