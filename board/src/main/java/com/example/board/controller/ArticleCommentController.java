package com.example.board.controller;

import com.example.board.domain.constant.FormStatus;
import com.example.board.dto.UserAccountDto;
import com.example.board.dto.request.ArticleCommentRequest;
import com.example.board.dto.request.ArticleRequest;
import com.example.board.dto.response.ArticleCommentResponse;
import com.example.board.dto.response.ArticleResponse;
import com.example.board.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
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
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest){
        //TODO : 인증 정보를 넣어줘야 한다.
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(
                UserAccountDto.of("uno","pw","uno@email.com",null, null)
        ));


        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @GetMapping("/{articleId}/{commentId}/form")
    public String updateArticleCommentForm(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId, ModelMap map) {
        ArticleCommentResponse articleComment = ArticleCommentResponse.from(articleCommentService.getArticleComment(commentId));


        System.out.println("commentId :: " + articleComment.id());
        System.out.println("articleId ::::::: " + articleId);
        map.addAttribute("articleComment", articleComment);
        map.addAttribute("articleId", articleId);
        map.addAttribute("formStatus", FormStatus.CUPDATE);

        return "articles/commentForm";
    }

    @PostMapping ("/{articleId}/{commentId}/form")
    public String updateArticleComment(@PathVariable("commentId") Long commentId, ArticleCommentRequest articleCommentRequest) {


        System.out.println("commentId ::::::: " + commentId);
//        System.out.println("articleId ::::::: " + articleId);
        System.out.println("articleId ::::::: " + articleCommentRequest);

        // TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.updateArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "uno", "asdf1234", "uno@mail.com", null, null)
        ));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId){
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
}
