<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<style type="text/css">

html { 
	overflow-y:scroll; 
	height: 100%;
}
body {
	height: 100%;
	margin:0;
    padding:0;
}
#wrapper {
	position:relative;
	height:100%;
    width: 1200px;
    margin:0 auto;
    background:green;
}
#header {
	height:10%;
	width: 1200px;
    background:#ccc;
    margin:0 auto;
}
#container{
	width: 100%;
	height:84%;
}
#footer {
	float: left;
    bottom:0;
    width:100%;
    height:6%;   
	text-align: center;

}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">



</script>

<BODY>
<div id="wrapper">
	<div id="header">
		<jsp:include page="./top.jsp" flush="false" />
	</div>
	
	<div id="container">
    	<jsp:include page="${center}" flush="false" />
	</div>
	
    <div id="footer">
    	<jsp:include page="./bottom.jsp" flush="false" />	
    </div>
</div>
</BODY>
</html>