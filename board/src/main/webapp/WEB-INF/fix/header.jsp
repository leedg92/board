<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Header template</title>
</head>
<body>
<sec:authentication property="principal" var="principal"/>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a id="goToThymeleafVer" href="/" class="nav-link px-2 text-secondary">thymeleaf version</a></li>
                <li><a id="home" href="/jsp" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a id="hashtag" href="/articles/search-hashtag/jsp" class="nav-link px-2 text-secondary">Hashtags</a></li>
            </ul>

            <div class="text-end">
                <sec:authorize access="isAuthenticated()">
                    <span id="username" class="text-white me-2">${principal.nickname()}</span>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <a role="button" id="login" class="btn btn-outline-light me-2" href="/login">Login</a>
                    <a role="button" id="kakao-login" class="me-2" href="/oauth2/authorization/kakao">
                        <img alt="Kakao Login" src="/images/kakao_login_medium.png">
                    </a>
                    <a role="button" id="signUp" class="btn btn-outline-light me-2" href="/users/userForm/jsp">SignUp</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a role="button" id="logout" class="btn btn-outline-light me-2" href="/logout">Logout</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</header>
</body>
</html>