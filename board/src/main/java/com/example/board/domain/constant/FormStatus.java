package com.example.board.domain.constant;


import lombok.Getter;

public enum FormStatus {
    ACREATE("게시글 저장", false),
    AUPDATE("게시글 수정", true),
    CUPDATE("댓글 수정", true);

    @Getter private final String description;
    @Getter private final Boolean update;

    FormStatus(String description, Boolean update) {
        this.description = description;
        this.update = update;
    }

}
