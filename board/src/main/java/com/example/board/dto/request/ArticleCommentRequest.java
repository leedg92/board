package com.example.board.dto.request;

import com.example.board.dto.ArticleCommentDto;
import com.example.board.dto.UserAccountDto;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.board.domain.ArticleComment} entity
 */

public record ArticleCommentRequest(Long commentId,Long articleId, String content) {

    public static ArticleCommentRequest of(Long commentId,Long articleId, String content) {
        return new ArticleCommentRequest( commentId ,articleId,content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto){
        return ArticleCommentDto.of( commentId, articleId, userAccountDto, content);
    }
}