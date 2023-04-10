package com.example.board.controller;

import com.example.board.domain.constant.FormStatus;
import com.example.board.domain.constant.SearchType;
import com.example.board.dto.request.ArticleRequest;
import com.example.board.dto.response.ArticleResponse;
import com.example.board.dto.response.ArticleWithCommentsResponse;
import com.example.board.dto.security.BoardPrincipal;
import com.example.board.service.ArticleService;
import com.example.board.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping("/th")
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        System.out.println("SearchType ::::: " + searchType);
        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        map.addAttribute("searchType",searchType);
        map.addAttribute("searchTypeHashtag",SearchType.HASHTAG);

        return "articles/index";
    }

    @GetMapping("/{articleId}/th")
    public String article(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());
        map.addAttribute("searchTypeHashtag",SearchType.HASHTAG);

        return "articles/detail";
    }

    @GetMapping("/search-hashtag/th")
    public String searchArticleHashtag(
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashtags = articleService.getHashtags();

        map.addAttribute("articles", articles);
        map.addAttribute("hashtags", hashtags);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchType", SearchType.HASHTAG);

        return "articles/search-hashtag";
    }

    @GetMapping("/form/th")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.ACREATE);

        return "articles/form";
    }

    @PostMapping ("/form/th")
    public String postNewArticle(ArticleRequest articleRequest,
                                 @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/th";
    }

    @GetMapping("/{articleId}/form/th")
    public String updateArticleForm(@PathVariable Long articleId, ModelMap map) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.AUPDATE);

        return "articles/form";
    }

    @PostMapping ("/{articleId}/form/th")
    public String updateArticle(@PathVariable Long articleId,
                                ArticleRequest articleRequest,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleId + "/th";
    }

    @PostMapping ("/{articleId}/delete/th")
    public String deleteArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleService.deleteArticle(articleId, boardPrincipal.getUsername());

        return "redirect:/articles/th";
    }

    @GetMapping("/jsp")
    public String jspArticles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<ArticleResponse> articlesList = articles.getContent();
        System.out.println("aaaaa ::: " + searchType);

        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        map.addAttribute("test", "hello, world!");
        map.addAttribute("articles", articles);
        map.addAttribute("articlesList", articlesList);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        map.addAttribute("searchType",searchType);
        map.addAttribute("searchTypeHashtag",SearchType.HASHTAG);

        return "index";
    }

    @GetMapping("/{articleId}/jsp")
    public String jspArticle(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());
        map.addAttribute("searchTypeHashtag",SearchType.HASHTAG);

        return "detail";
    }

    @GetMapping("/search-hashtag/jsp")
    public String jspSearchArticleHashtag(
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashtags = articleService.getHashtags();

        map.addAttribute("articles", articles);
        map.addAttribute("hashtags", hashtags);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchType", SearchType.HASHTAG);

        return "search-hashtag";
    }

    @GetMapping("/form/jsp")
    public String jspArticleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.ACREATE);

        return "form";
    }

    @PostMapping ("/form/jsp")
    public String jspPostNewArticle(ArticleRequest articleRequest,
                                 @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/jsp";
    }

    @GetMapping("/{articleId}/form/jsp")
    public String jspUpdateArticleForm(@PathVariable Long articleId, ModelMap map) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.AUPDATE);

        return "form";
    }

    @PostMapping ("/{articleId}/form/jsp")
    public String jspUpdateArticle(@PathVariable Long articleId,
                                ArticleRequest articleRequest,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleId + "/jsp";
    }

    @PostMapping ("/{articleId}/delete/jsp")
    public String jspDeleteArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        System.out.println("jsp deleteCall");
        articleService.deleteArticle(articleId, boardPrincipal.getUsername());

        return "redirect:/articles/jsp";
    }

}