package com.example.board.dto;

import java.time.LocalDateTime;

public record ArticleCommentUpdatedDto(
        String content
) {

    public static ArticleCommentUpdatedDto of(String content) {
        return new ArticleCommentUpdatedDto(content);
    }
}
