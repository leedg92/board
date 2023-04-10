<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Footer template</title>
</head>
<body>
<footer class="container d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-muted">jsp/ thymeleaf 두가지의 버전이 있습니다.(상단의 텍스트를 누르면 바뀝니다)
        -내부 로직은 모두 같습니다.</p>

    <ul class="nav col-md-4 justify-content-end">
        <li class="nav-item"><a href="/jsp" class="nav-link px-2 text-muted">Home</a></li>
    </ul>
</footer>
</body>
</html>