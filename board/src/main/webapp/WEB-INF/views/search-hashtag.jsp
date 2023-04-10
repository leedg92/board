<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Uno Kim">
  <title>해시태그 검색</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <link href="/css/articles/table-header.css" rel="stylesheet">
</head>

<body>
<%@ include file="../fix/header.jsp"%>

<main class="container">
  <header class="py-5 text-center">
    <h1>Hashtags</h1>
  </header>

  <section class="row">
    <div id="hashtags" class="col-9 d-flex flex-wrap justify-content-evenly">
      <div class="p-2">
        <h2 class="text-center lh-lg font-monospace"><a href="#">#java</a></h2>
      </div>
    </div>
  </section>

  <hr>

  <table class="table" id="article-table">
    <thead>
    <tr>
      <th class="title col-6"><a>제목</a></th>
      <th class="content col-4"><a>본문</a></th>
      <th class="user-id"><a>작성자</a></th>
      <th class="created-at"><a>작성일</a></th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td class="title"><a>첫글</a></td>
      <td class="content"><span class="d-inline-block text-truncate" style="max-width: 300px;">본문</span></td>
      <td class="user-id">Uno</td>
      <td class="created-at"><time>2022-01-01</time></td>
    </tr>
    <tr>
      <td>두번째글</td>
      <td>본문</td>
      <td>Uno</td>
      <td><time>2022-01-02</time></td>
    </tr>
    <tr>
      <td>세번째글</td>
      <td>본문</td>
      <td>Uno</td>
      <td><time>2022-01-03</time></td>
    </tr>
    </tbody>
  </table>

  <nav id="pagination" aria-label="Page navigation">
    <ul class="pagination justify-content-center">
      <li class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
  </nav>

</main>

<%@ include file="../fix/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>