<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 읽기</title>
</head>
<body>
<h1>제목 : ${boardDto.title}</h1>
<textarea readonly="readonly" cols="40" rows="8">
${boardDto.content}
</textarea>
<br/>
<button onclick="location='/jj/boardList?curPage=${curPage}'">[목록]</button>
</body>
</html>