<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">

function checkWrite(){
	
	
	if(!document.board.selectfile.value){
		alert('이미지를 선택하세요.');	
		return false;
	}

	if(!document.board.content.value){
		alert('내용을 입력하세요.');	
		return false;
	}
	
	return true
	
}

</script>
<form action="/jj/write" method="post" enctype="multipart/form-data" name="board" onsubmit="return checkWrite()">
	<h1>이미지 : </h1>
	<input type="file" name="selectfile">
	<div>
		<textarea name="content" cols="40" rows="8"></textarea>
	</div>
	<div><input type="submit" value="쓰기"></div>
</form>

</body>
</html>