<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>${sessionScope.id} 님 환영합니다.</h1>
<button onclick="location='logout'">로그아웃</button>


<button onclick="location='/jj/writeFrom'">글쓰기</button>
<button onclick="location='/jj/boardList'">게시판</button>
</body>
</html>