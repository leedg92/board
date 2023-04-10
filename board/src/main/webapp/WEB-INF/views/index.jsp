<%@ page import="com.example.board.dto.response.ArticleResponse" %>
<%@ page import="com.example.board.domain.constant.*" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Uno Kim">
    <title>게시판 페이지</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/css/search-bar.css" rel="stylesheet">
    <link href="/css/articles/table-header.css" rel="stylesheet">
</head>

<body>
<%@ include file="../fix/header.jsp"%>
<%--<header id="header">--%>
<%--    헤더 삽입부--%>
<%--    <hr>--%>
<%--</header>--%>

<main class="container">

    <div class="row">
        <div class="card card-margin search-form">
            <div class="card-body p-0">
                <form id="search-form" action="/articles/jsp">
                    <div class="row">
                        <div class="col-12">
                            <div class="row no-gutters">
                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                    <label for="search-type" hidden>검색 유형</label>
                                    <select class="form-control" id="search-type" name="searchType" >
                                        <c:forEach items="${searchTypes}" var="paramSearchType" varStatus="i">
                                        <option value="${paramSearchType}" <c:if test="${searchType == paramSearchType}">selected</c:if>>${paramSearchType.getDescription()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-8 col-md-6 col-sm-12 p-0">
                                    <label for="search-value" hidden>검색어</label>
                                    <input type="text" placeholder="검색어..." class="form-control" id="search-value" name="searchValue">
                                </div>
                                <div class="col-lg-1 col-md-3 col-sm-12 p-0">
                                    <button type="submit" class="btn btn-base">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search">
                                            <circle cx="11" cy="11" r="8"></circle>
                                            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row">
        <table class="table" id="article-table">
            <thead>

            <tr>
                ${articles.sort.getOrderFor('title')}
<%--                <c:if test="${empty articles.sort.getOrderFor('title')}">${articles.sort.getOrderFor('title').direction.name}</c:if>--%>
<%--                <c:if test="${!empty articles.sort.getOrderFor('title')}">ASC</c:if>--%>
                <th class="title col-6"><a href="/articles/jsp?page=${articles.number}&sort=title<c:if test="${empty articles.sort.getOrderFor('title') || articles.sort.getOrderFor('title') eq 'title: ASC'}">,desc</c:if>&searchType=${searchType}&searchValue=">제목</a></th>
                <th class="hashtag col-2"><a href="/articles/jsp?page=${articles.number}&sort=hashtags<c:if test="${empty articles.sort.getOrderFor('hashtags') || articles.sort.getOrderFor('hashtags') eq 'hashtags: ASC'}">,desc</c:if>&searchType=${searchType}&searchValue=">해시태그</a></th>
                <th class="user-id"><a href="/articles/jsp?page=${articles.number}&sort=userAccount.userId<c:if test="${empty articles.sort.getOrderFor('userAccount.userId') || articles.sort.getOrderFor('userAccount.userId') eq 'userAccount.userId: ASC'}">,desc</c:if>&searchType=${searchType}&searchValue=">작성자</a></th>
                <th class="created-at"><a href="/articles/jsp?page=${articles.number}&sort=createdAt<c:if test="${empty articles.sort.getOrderFor('createdAt') || articles.sort.getOrderFor('createdAt') eq 'createdAt: ASC'}">,desc</c:if>&searchType=${searchType}&searchValue=">작성일</a></th>
<%--                <th class="hashtag col-2"><a>해시태그</a></th>--%>
<%--                <th class="user-id"><a>작성자</a></th>--%>
<%--                <th class="created-at"><a>작성일</a></th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articlesList}" var="article" varStatus="i">
                <tr>

                    <td class="title"><a href="/articles/${article.id()}/jsp"><c:out value="${article.title()}"/></a></td>
                    <td class="hashtag">
                    <c:forEach items="${article.hashtags()}" var="hashtag" varStatus="i">
                        <span class="badge text-bg-secondary mx-1"><a class="text-reset" href="/articles/jsp?searchType=${searchTypeHashtag}&searchValue=${hashtag}">#<c:out value="${hashtag}"/></a></span>
                    </c:forEach>
                    </td>
                    <td class="user-id"><c:out value="${article.nickname()}"/></td>
                    <td class="created-at"><time tem><c:out value="${article.createdAt()}"/></time></td>

                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
    <sec:authorize access="isAuthenticated()">
    <div class="row">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a class="btn btn-primary me-md-2" role="button" id="write-article" href="/articles/form/jsp">글쓰기</a>
        </div>
    </div>
    </sec:authorize>
    <c:set var="totalNumber" value="${articles.getTotalPages()}" />
    <c:set var="currentNumber" value="${articles.getNumber()}" />
    <%
        int totalNumber = (Integer) pageContext.getAttribute("totalNumber");
        int currentNumber = (Integer) pageContext.getAttribute("currentNumber");
    %>
    <div class="row">
        <nav id="pagination" aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link<%=currentNumber <= 0 ? " disabled" : ""%>" href="/articles/jsp?page=${currentNumber -1}">Previous</a></li>
                <c:forEach items="${paginationBarNumbers}" var="pageNumber" varStatus="i">
                    <c:set var="pageNumber" value="${pageNumber}" />
                    <% int pageNumber = (Integer)pageContext.getAttribute("pageNumber"); %>
                        <li class="page-item"><a class="page-link<%=pageNumber == currentNumber ? " disabled" : ""%>" href="/articles/jsp?page=${pageNumber}">${pageNumber + 1}</a></li>
                </c:forEach>
                <li class="page-item"><a class="page-link<%=currentNumber >= totalNumber ? " disabled" : ""%>" href="/articles/jsp?page=${currentNumber + 1}">Next</a></li>
            </ul>
        </nav>
    </div>
</main>

<%--<footer id="footer">--%>
<%--    <hr>--%>
<%--    푸터 삽입부--%>
<%--</footer>--%>
<%@ include file="../fix/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>