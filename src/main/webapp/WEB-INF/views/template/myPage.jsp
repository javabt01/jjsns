<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">

#myPageDiv{
	background:#ccc;
	width:80%;
	height:100%;
	margin:auto;
}
#profile{
	background:white;
	height:35%;
}
#profileImgDiv{
	float:left;
	width:35%;
	height:100%;
}
#profileImg{
	width: 150px;
    height: 150px;
    position:relative;
    top:25%;
    left:25%;
}
#profileInfo{
	float:left;
	width:65%;
}
.infos{
	float:left;
	margin-top:10px;
	margin-left:10px;
}

ul.tabs{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tabs li{
  background: none;
  color: #222;
  display: inline-block;
  padding: 10px 15px;
  cursor: pointer;
}
 
ul.tabs li.current{
  background: #ededed;
  color: #222;
}
 
.tab-content{
  display: none;
  background: #ededed;
  padding: 15px;
}
 
.tab-content.current{
  display: inherit;
}
#tabMenu{
	text-align:center;
}
.boardImg{
	margin-top:10px;
	margin-left:10px;
	width:300px;
	height:300px;
}
</style>
<script type="text/javascript">
var curPage = 1;
var ajaxing = false;
var totalBlock = false;

$(document).ready(function(){
  
	$('#Progress_Loading').hide(); //첫 시작시 로딩바를 숨겨준다.
	
	  $('ul.tabs li').click(function(){
	    var tab_id = $(this).attr('data-tab');
	 
	    $('ul.tabs li').removeClass('current');
	    $('.tab-content').removeClass('current');
	 
	    $(this).addClass('current');
	    $("#"+tab_id).addClass('current');
	  })
 
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
		 getBoardList1();
	 }
}

$(window).scroll(function(){ 
  if($(window).scrollTop() == $(document).height() - $(window).height()){ 
	 	getBoardList1();
  } 
});

function getBoardList1(){
	if(ajaxing || totalBlock) return;
	
	$.ajax({
        type : "GET", 
        url : "/jj/userBoardList",
        dataType : "JSON",
        data : {curPage : curPage},
        success : function(data){
        	for(var i = 0; i < data.boardList.length; i++){
        		var board ="<img class=\"boardImg\" src=\"/upload/"+data.boardList[i].img+"\">";
        		$('#boardList').append(board);
        	}
        	curPage++;
        	if(data.boardPager.curPage >= data.boardPager.totalPage){
        		totalBlock = true;
        	}
        },
		error : function(){
            alert("통신실패!!!!");
        },
         
    });
}

	
	
</script>
<div id="myPageDiv">
	<div id="profile">
		<div id="profileImgDiv">
			<img id="profileImg" src="/resources/profile.jpg">
		</div>
		<div id="profileInfo">
			<div><div id="name" class="infos">${member.id}</div>
			<div class="infos"><button id="btnModifyProfile">프로필 편집</button></div></div>
			<br><br>
			<div id="profileInfoDiv" style="border:1px solid red">
				<div id="board" class="infos">게시물<div id="boardCount">0</div></div>
				<div id="follower" class="infos">팔로워<div id="followerCount">0</div></div>
				<div id="follow" class="infos">팔로우<div id="followCount">0</div></div>
			</div>
		</div>
	</div>
	<div id="tabMenu">
		<ul class="tabs">
		    <li class="tab-link current" data-tab="tab-1">게시물</li>
		    <li class="tab-link" data-tab="tab-2">IGTV</li>
		    <li class="tab-link" data-tab="tab-3">저장됨</li>
		    <li class="tab-link" data-tab="tab-4">태그됨</li>
		</ul>
		<div id="tab-1" class="tab-content current">
			<div id="boardList">
			</div>
			<div id="Progress_Loading">
				<div><img src="/upload/loading.gif"/></div>
			</div>
		</div>
		<div id="tab-2" class="tab-content">
			---- ---- ★------ ---- ---- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- ---- -------- ---- ---- ---- ★-- ---- ---- ------★ ---- ---- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- ---- ★------ ---- ---- ---- ----
		</div>
		<div id="tab-3" class="tab-content">
			---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
		</div>
	</div>
</div>