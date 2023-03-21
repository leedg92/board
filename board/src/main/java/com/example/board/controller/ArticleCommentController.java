package com.example.board.controller;

import com.example.board.domain.constant.FormStatus;
import com.example.board.dto.UserAccountDto;
import com.example.board.dto.request.ArticleCommentRequest;
import com.example.board.dto.request.ArticleRequest;
import com.example.board.dto.response.ArticleCommentResponse;
import com.example.board.dto.response.ArticleResponse;
import com.example.board.dto.security.BoardPrincipal;
import com.example.board.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest,
                                        @AuthenticationPrincipal BoardPrincipal boardPrincipal){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));


        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @GetMapping("/{articleId}/{commentId}/form")
    public String updateArticleCommentForm(@PathVariable("articleId") Long articleId,
                                           @PathVariable("commentId") Long commentId,
                                           ModelMap map) {
        ArticleCommentResponse articleComment = ArticleCommentResponse.from(articleCommentService.getArticleComment(commentId));


        System.out.println("commentId :: " + articleComment.id());
        System.out.println("articleId ::::::: " + articleId);
        map.addAttribute("articleComment", articleComment);
        map.addAttribute("articleId", articleId);
        map.addAttribute("formStatus", FormStatus.CUPDATE);

        return "articles/commentForm";
    }

    @PostMapping ("/{articleId}/{commentId}/form")
    public String updateArticleComment(@PathVariable("commentId") Long commentId,
                                       @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                       ArticleCommentRequest articleCommentRequest) {


        System.out.println("commentId ::::::: " + commentId);
        System.out.println("articleId ::::::: " + articleCommentRequest);

        articleCommentService.updateArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId,
                                       @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                       Long articleId){
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }
}
