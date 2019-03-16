<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">

var isUse = false;

	function joinCheck(){
		
		if(!document.userInfo.id.value){
			alert('아이디입력해라');	
			return false;
		}
		
		if(!isUse){
			alert('중복체크해라');	
			return false;
		}
		
		
		if(!document.userInfo.pw.value){
			alert('비밀번호 입력해라');
			return false;
		}
		
	}
	
	function idChange(){
		isUse = false;
	}
	
	function idDupCheck(){
		
		
		
		if(!document.userInfo.id.value){
			alert('아이디입력해라');
			return false;
		}
		
		$.ajax({
            type : "GET", 
            url : "/idDupCheck",
            data : {id : document.userInfo.id.value},
            success : function(data){
            	if(data == "true"){
            		document.getElementById("isUse").innerText='사용가능';
            		isUse = true;
            	}else{
            		document.getElementById("isUse").innerText='사용불가';
            		isUse = false;
            	}
            },
			error : function(){
	            alert("통신실패!!!!");
	        },
             
        });
		
	}
</script>
<center>
	<h1>
		회원가입해라
	</h1>
	<form action="/join" method="post" name="userInfo" onsubmit="return joinCheck()">
		<div>ID : <input type="text" name="id" onchange="idChange()"><label id="isUse"></label><button type="button" onclick="idDupCheck()" >중복체크</button></br></div>
        <div>PW: <input type="text" name="pw" value=""></br></div>
        <div><input type="submit" value="회원가입"></div>
	</form>
	
</center>
</body>
</html>