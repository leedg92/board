<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Uno Kim">
    <title>게시글 페이지</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/css/articles/article-content.css" rel="stylesheet">
</head>

<body>

<%@ include file="../fix/header.jsp"%>
<sec:authentication property="principal" var="principal"/>
<main id="article-main" class="container">
    <header id="article-header" class="py-5 text-center">
        <h1>${article.title()}</h1>
    </header>

    <div class="row g-5">
        <section class="col-md-3 col-lg-4 order-md-last">
            <aside>
                <p><span id="nickname">${article.nickname()}</span></p>
                <p><a id="email" href="mailto:djkehh@gmail.com">${article.email()}</a></p>
                <p><time id="created-at" datetime="2022-01-01T00:00:00">${article.createdAt()}</time></p>
                <c:forEach items="${article.hashtags()}" var="hashtag" varStatus="i">
                    <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">${hashtag}</a></span></p>
                </c:forEach>
            </aside>
        </section>

        <article id="article-content" class="col-md-9 col-lg-8">
            <pre>${article.content()}</pre>
        </article>
    </div>

    <sec:authorize access="isAuthenticated()">
        <c:if test="${article.userId() == principal.username()}">
        <div class="row g-5" id="article-buttons">
            <form id="delete-article-form" action="/articles/${article.id()}/delete/jsp" method="post">
                <div class="pb-5 d-grid gap-2 d-md-block">
                    <a class="btn btn-success me-md-2" role="button" id="update-article" href="/articles/${article.id()}/form/jsp">수정</a>
                    <button class="btn btn-danger me-md-2" type="submit">삭제</button>
                </div>
            </form>
        </div>
        </c:if>
    </sec:authorize>

    <div class="row g-5">
        <section>
            <form class="row g-3 comment-form" action="/comments/new/jsp" method="post">
                <input type="hidden" class="article-id" name="articleId" value="${article.id()}">
                <div class="col-md-9 col-lg-8">
                    <label for="comment-textbox" hidden>댓글</label>
                    <textarea class="form-control comment-textbox" id="comment-textbox" name="content" placeholder="댓글 쓰기.." rows="3" required></textarea>
                </div>
                <sec:authorize access="isAuthenticated()">
                <div class="col-md-3 col-lg-4">
                    <label for="comment-submit" hidden>댓글 쓰기</label>
                    <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
                </div>
                </sec:authorize>
            </form>
            <c:forEach items="${articleComments}" var="articleComment" varStatus="i">
            <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">
                <li class="parent-comment">
                    <form class="comment-delete-form" action="/comments/${articleComment.id()}/delete/jsp" method="post">
                        <input type="hidden" class="article-id" name="articleId" value="${article.id()}">
                        <div class="row">
                                <div class="col-md-10 col-lg-9">
                                    <strong>${articleComment.userId()}</strong>
                                    <small><time>${articleComment.createdAt()}</time></small>
                                    <p class="mb-1">
                                        ${articleComment.content()}
                                    </p>
                                </div>
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${articleComment.userId() == principal.username()}">
                                <div class="col-2 mb-3 align-self-center">
                                    <button type="submit" class="btn btn-outline-danger">삭제</button>
                                    <a class="btn btn-outline-danger" role="button" id="parent-update-comment" href="/comments/${article.id()}/${articleComment.id()}/form/jsp">수정</a>
                                </div>
                                </c:if>
                            </sec:authorize>
                        </div>
                    </form>
                    <c:if test="${!empty articleComment.childComments()}">
                        <c:forEach items="${articleComment.childComments()}" var="childComment" varStatus="1">
                        <ul class="row me-0">
                            <li class="child-comment">
                                <form class="comment-delete-form" action="/comments/${childComment.id()}/delete/jsp" method="post">
                                    <input type="hidden" class="article-id" name="articleId" value="${article.id()}">
                                    <div class="row">
                                        <div class="col-md-10 col-lg-9">
                                            <strong>${childComment.nickname()}</strong>
                                            <small><time>${childComment.createdAt()}</time></small>
                                            <p class="mb-1">
                                                ${childComment.content()}
                                            </p>
                                        </div>
                                        <sec:authorize access="isAuthenticated()">
                                            <c:if test="${childComment.userId() == principal.username()}">
                                            <div class="col-2 mb-3 align-self-center">
                                                <button type="submit" class="btn btn-outline-danger">삭제</button>
                                                <a class="btn btn-outline-danger" role="button" id="child-update-comment" href="/comments/${article.id()}/${articleComment.id()}/form/jsp">수정</a>
                                            </div>
                                            </c:if>
                                        </sec:authorize>
                                    </div>
                                </form>
                            </li>
                        </ul>
                        </c:forEach>
                    </c:if>


                    <div class="row">
                        <details class="col-md-10 col-lg-9 mb-4">
                            <summary>대댓글 달기</summary>

                            <form class="comment-form" action="/comments/new/jsp" method="post">
                                <input type="hidden" class="article-id" name="articleId" value="${article.id()}">
                                <input type="hidden" class="parent-comment-id" name="parentCommentId" value="${articleComment.id()}">
                                <textarea class="form-control comment-textbox" placeholder="대댓글 쓰기.." rows="2" name="content" required></textarea>
                                <button class="form-control btn btn-primary mt-2" type="submit">쓰기</button>
                            </form>
                        </details>
                    </div>
                </li>
            </ul>
            </c:forEach>
        </section>
    </div>

    <div class="row g-5">
        <nav id="pagination" aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo; prev</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">next &raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</main>

<%@ include file="../fix/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>