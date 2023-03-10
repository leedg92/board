package com.example.board.service;

import com.example.board.domain.Article;
import com.example.board.domain.type.SearchType;
import com.example.board.dto.ArticleDto;
import com.example.board.dto.ArticleUpdateDto;
import com.example.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;

@DisplayName("비즈니스로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); //제목, 본문, ID, 닉네임, 해시태그
        
        // Then
        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글 조회 - 게시글 반환한다.")
    @Test
    void givenId_whenSearchingArticle_thenReturnsArticle(){
        // Given

        // When
        ArticleDto article = sut.searchArticle(1L);

        // Then
        assertThat(article).isNotNull();

    }

    @DisplayName("게시글 입력 - 게시글 생성")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){
        // Given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(),"test","titleTest","contentTest","hashtagTest");
       given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(dto);

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID와 수정 정보 입력 - 게시글 수정")
    @Test
    void givenArticleModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){
        // Given
        ArticleUpdateDto dto = ArticleUpdateDto.of("titleModifyTest","contentModifyTest","hashtagModifyTest");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, dto);

        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID 입력 - 게시글 삭제")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));

    }

}