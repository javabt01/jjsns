
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<center>
	<h1>
		로그인해라
	</h1>
	<form action="/login" method="post">
		<div>ID : <input type="text" name="id" value="${id}"></br></div>
        <div>PW: <input type="password" name="pw" value=""></br></div>
        <div><input type="checkbox" name="auto" >자동로그인</<input><input type="submit" value="로그인">  <button type="button" onclick="location='join'">회원가입</button></div>
	</form>
	<P>${loginMsg}</P>
</center>



</body>
</html>
