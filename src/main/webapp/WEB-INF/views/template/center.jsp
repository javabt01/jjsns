<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
#left {
	float: left; 
	width: 10%;
	background:yellow;
}
#center {
	float: left; 
	width: 50%;
}
#right {
	float: left; 
	width: 40%;
	background:red;
}

.imgs {
	width: 100%;
    height: auto;
}
.board{
	border: 1px solid;
	padding: 50px 0px 50px 0px;
}
#Progress_Loading{
	text-align: center;
}

</style>
<script type="text/javascript">

var curPage = 1;
var ajaxing = false;

$(document).ready(function(){
   $('#Progress_Loading').hide(); //첫 시작시 로딩바를 숨겨준다.
})
.ajaxStart(function(){
	ajaxing=true;
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
})
.ajaxStop(function(){
	$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
	ajaxing = false;
});

window.onload = function(){ 
	 if($(window).scrollTop() == $(document).height() - $(window).height()){ 
	      getBoardList();
	   } 
}

$(window).scroll(function(){ 
   if($(window).scrollTop() == $(document).height() - $(window).height()){ 
      getBoardList();
   } 
});



function getBoardList(){
	
	if(ajaxing) return;
	//alert(curPage);
	
	$.ajax({
        type : "GET", 
        url : "/jj/boardList",
        dataType : "JSON",
        data : {curPage : curPage},
        success : function(data){
        	
        	for(var i = 0; i < data.boardList.length; i++){
        		//var img = "<img src=\"/upload/"+boardList[i].img+"\">";
        		var board ="<div class=\"board\">"+ 
	        					"<div class=\"writer\">"+data.boardList[i].id+"</div>"+
	        					"<img class=\"imgs\" src=\"/upload/"+data.boardList[i].img+"\"><br>"+
	        				    "<div class=\"content\">"+data.boardList[i].content+"</div>"+
        				    "</div>";
	        				
        		$('#boardList').append(board);
        	}
        	curPage++;
        },
		error : function(){
            alert("통신실패!!!!");
        },
         
    });
}

</script>
<div id="left">
	<h1></h1>
</div>

<div id="center">
	<div id="boardList">
	</div>
	<div id="Progress_Loading">
		<div><img src="/upload/loading.gif"/></div>
	</div>
</div>

<div id="right">
	<div id="rightDiv">광고창 Right</div>
</div>

