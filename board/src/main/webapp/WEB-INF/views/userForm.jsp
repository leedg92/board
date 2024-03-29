<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <h1>회원가입</h1>
  </header>

  <form id="user-form" action="/users/userForm/jsp" method="post">
    <label for="userId">Username:</label>
    <input type="text" id="userId" name="userId"><br><br>

    <label for="userPassword">Password:</label>
    <input type="password" id="userPassword" name="userPassword"><br><br>

    <label for="confirm-password">Confirm Password:</label>
    <input type="password" id="confirm-password" name="confirm-password"><br><br>

    <label for="nickname">nickname:</label>
    <input type="text" id="nickname" name="nickname"><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email"><br><br>

    <label for="memo">memo:</label>
    <input type="text" id="memo" name="memo"><br><br>

<!--    <input type="time" id="createdAt" name="createdAt" value="2022-01-01" hidden>-->
<!--    <input type="hidden" id="createdBy" name="createdBy"  value="asdasda">-->
<!--    <input type="time" id="modifiedAt" name="modifiedAt" value="2022-01-01" hidden>-->
<!--    <input type="hidden" id="modifiedBy" name="modifiedBy"  value="asdaasd">-->



    <input type="submit" id="submit-button" value="Submit" onclick="Validate()">
  </form>
</div>

<%@ include file="../fix/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script type="text/javascript">
  function Validate() {
    var p1 = document.getElementById('password').value;
    var p2 = document.getElementById('confirm-password').value;
    if(document.getElementById('userid').value == ""
            && document.getElementById('password').value == ""
            && document.getElementById('confirm-password').value == ""
            && document.getElementById('nickname').value == ""
            && document.getElementById('email').value == ""
            && document.getElementById('memo').value == ""){
      alert("모든 항목을 입력하세요");
      return false;
    }else if( p1 != p2 ) {
      alert("비밀번호가 일치 하지 않습니다");
      return false;
    }else{
      return true;
    }

  }
</script>
</body>
</html>