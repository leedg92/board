<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Uno Kim">
  <title>새 게시글 등록</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>

<body>

<%@ include file="../fix/header.jsp"%>

<div class="container">
  <header id="article-form-header" class="py-5 text-center">
    <h1>${formStatus.getDescription()}</h1>
  </header>
  <form id="article-form" action="/articles<c:if test="${!empty article.id()}">/${article.id()}</c:if>/form/jsp" method="post">

    <div class="row mb-3 justify-content-md-center">
      <label for="title" class="col-sm-2 col-lg-1 col-form-label text-sm-end">제목</label>
      <div class="col-sm-8 col-lg-9">
        <input type="text" class="form-control" id="title" name="title" value="${article.title()}" required>
      </div>
    </div>
    <div class="row mb-3 justify-content-md-center">
      <label for="content" class="col-sm-2 col-lg-1 col-form-label text-sm-end">본문</label>
      <div class="col-sm-8 col-lg-9">
        <textarea class="form-control" id="content" name="content" rows="5" required>${article.content()}</textarea>
      </div>
    </div>
<!--    <div class="row mb-4 justify-content-md-center">-->
<!--      <label for="hashtag" class="col-sm-2 col-lg-1 col-form-label text-sm-end">해시태그</label>-->
<!--      <div class="col-sm-8 col-lg-9">-->
<!--        <input type="text" class="form-control" id="hashtag" name="hashtag">-->
<!--      </div>-->
<!--    </div>-->
    <div class="row mb-5 justify-content-md-center">
      <div class="col-sm-10 d-grid gap-2 d-sm-flex justify-content-sm-end">
        <button type="submit" class="btn btn-primary" id="submit-button">저장</button>
        <button type="button" class="btn btn-secondary" id="cancel-button" onclick="history.back()">취소</button>
      </div>
    </div>
  </form>
</div>

<%@ include file="../fix/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>