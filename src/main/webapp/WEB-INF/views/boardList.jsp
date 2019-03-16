<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
</head>
<body>
<h1>게시판</h1>
<table border="1">
	<tr>
		<td>seq</td>
		<td>제목</td>
		<td>Date</td>
	</tr>
	<c:forEach var="row" items="${boardList}">
		<tr>
		<td>${row.seq}</td>
		<td><a href="/jj/readBoard?seq=${row.seq}&curPage=${boardPager.curPage}">${row.title}</a></td>
		<td>${row.reg_date}</td>
		</tr>
	</c:forEach>
</table>
<c:if test="${boardPager.curBlock > 1}">
	<a href="/jj/boardList?curPage=1">[처음]</a>
</c:if>
<c:if test="${boardPager.curBlock > 1}">
	<a href="/jj/boardList?curPage=${boardPager.prevPage}">[이전]</a>
</c:if>
<c:forEach var="num" begin="${boardPager.blockBegin}" end="${boardPager.blockEnd}">
	<c:choose>
		<c:when test="${num == boardPager.curPage}">
			<span style="color:red">${num}</span>
		</c:when>
		<c:otherwise>
			<a href="/jj/boardList?curPage=${num}">[${num}]</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${boardPager.curBlock < boardPager.totalBlock}">
	<a href="/jj/boardList?curPage=${boardPager.nextPage}">[다음]</a>
</c:if>
<c:if test="${boardPager.curBlock < boardPager.totalBlock}">
	<a href="/jj/boardList?curPage=${boardPager.totalPage}">[끝]</a>
</c:if><br/>
<button onclick="location='/jj/writeFrom'">글쓰기</button>
</body>
</html>