<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
@font-face {font-family: 'GmarketSansMedium';src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');font-weight: normal;font-style: normal;}
    .#container {width: 100%; height:1000px;  margin: 0 auto; font-family: 'GmarketSansMedium', serif ;}
	.header {width: 1200px; padding: 10px; height: 80px; margin: 15px auto;}
	#logo{width: 250px; cursor: pointer; float: left; height: 80px; margin-left: 40px; z-index: 100%;}
	#search {margin: 20px 10px 10px 130px;width: 406px;height: 41px;background: #FFFFFF;padding-left: 30px; 
	padding-right:20px; border: 1px solid #4FA2C7;box-sizing: border-box;border-radius: 10px;}
	#login,#myPage {margin-right: 10px;background: #4FA2C7;border-radius: 10px;width: 98px;height: 41px;color: white; font-family: 'GmarketSansMedium', serif ;border: none;
	position: absolute; right: 200px; top: 20px;}
    #signin,#logout { background: #4FA2C7;border-radius: 10px;width: 98px;height: 41px;color: white; font-family: 'GmarketSansMedium',serif;border: none;
    position: absolute; right: 100px; top: 20px;}
	#btn_login {text-decoration: none; color: black;}
	#myPage{position: absolute; right: 200px; top: 20px;}
	#logout{position: absolute; right: 100px; top: 20px;}
	.topMenu{position: relative;}
	.containerPImg {
		display: inline-block;	
		width: 40px;
		height: 40px;
	    border-radius: 50%;
		    overflow: hidden;
		    position: absolute;
		    top: 20px;
		    right: 320px;
		}
	.containerPImg:hover{cursor: pointer;}	
	.pImg {
		object-fit: cover;	
		height: 100%;
		width: 100%;
	}
	button:hover {cursor: pointer;}
	section {margin: 0 auto; clear: both;}
	section h1{margin-left: 20px;margin-top: 30px;}
	.indexBlock {margin:0 auto; width: 1200px; height : 260px; margin-bottom: 60px;position: relative;}
	img{width: 180px; border-radius: 5%;}
	.imgBlock{display: inline-block; width: 200px; text-align: center;
		   margin-right: -20px; vertical-align: top; margin-left: 40px;}
	.imgBlock:hover{cursor: pointer;}
	.listBlock{vertical-align: top; margin-bottom: 20px; }
	.material-icons{width: 50px; height: 30px; position: absolute; top: 130px; margin-left: 20px;}
	.material-icons:hover{border: 1px solid black; cursor: pointer;}
</style>
<title>홈 화면</title>
</head>
<body>
	<div id="container">
		<div class="header">
			<div class="topMenu">
			<img alt="모두의 웹툰" src="/images/logo2.png" id="logo" onclick="goHome()">
				<input type="search" id="search" placeholder="웹툰, 작가를 검색하세요" onkeydown="moveToResult()">
				<c:choose>
					<c:when test="${loginUser.name == null}">
						<button id="login" onclick="moveToLogin()">로그인</button>
						<button id="signin" onclick="moveToJoin()">회원가입</button>
					</c:when>
					<c:otherwise>
						<div class="containerPImg" onclick="moveToProfile()">
							<img class="pImg" src="${loginUser.profile}" alt="프로필 설정 가기">
						</div>
						<button id="myPage" onclick="moveToMyPage()">${loginUser.name}님</button>
						<button id="logout" onclick="moveToLogOut()">로그아웃</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	<section>
		<div class="indexBlock" style="margin-bottom: 30px;">
			<h1>네이버웹툰 추천</h1>
			<hr>
			<div class="listBlock">
			<span class="material-icons">keyboard_arrow_left</span>
				<c:forEach items="${list}" var="item" begin="0" end="4">
					<c:if test="${item.w_plat_no == 1}">
						<div class="imgBlock" onclick="moveToDetail(${item.w_no})">
							<img src="${item.w_thumbnail}"><br>
							${item.w_title}
						</div>
					</c:if>
				</c:forEach>
				<span class="material-icons	">keyboard_arrow_right</span>
			</div>
		</div>
		<div class="indexBlock">
			<h1>카카오페이지 추천</h1>
			<hr>
			<div class="listBlock">
			<span class="material-icons">keyboard_arrow_left</span>
				<c:forEach items="${list}" var="item" begin="15" end="19">
					<c:if test="${item.w_plat_no == 3}">
						<div class="imgBlock" onclick="moveToDetail(${item.w_no})">
							<img src="${item.w_thumbnail}"><br>
							${item.w_title}
						</div>
					</c:if>
				</c:forEach>
				<span class="material-icons	">keyboard_arrow_right</span>
			</div>
		</div>
		<div class="indexBlock">
			<h1>레진코믹스 추천</h1>
			<hr>
			<div class="listBlock">
			<span class="material-icons">keyboard_arrow_left</span>
				<c:forEach items="${list}" var="item" begin="30" end="34">
					<c:if test="${item.w_plat_no == 4}">
						<div class="imgBlock" onclick="moveToDetail(${item.w_no})">
							<img src="${item.w_thumbnail}"><br>
							${item.w_title}
						</div>
					</c:if>
				</c:forEach>
				<span class="material-icons	">keyboard_arrow_right</span>
			</div>
		</div>
	</section>
	<footer> </footer>
	</div>
	<script>
    	function moveToDetail(w_no) {
    		location.href = '/webtoon/detail?w_no='+w_no
    	}
    	function moveToLogin() {
			location.href = '/login'
		}
    	function moveToJoin() {
			location.href = '/join'
		}
    	function moveToResult() {
			if(event.keyCode == 13){
				var result = search.value
				location.href = '/searchResult?result='+result
			}
		}
    	function goHome() {
    		location.href = '/home'
    	  }
    	function moveToMyPage() {
			location.href = '/myPage?i_user=${loginUser.u_no}'
		}
    	function moveToProfile() {
			location.href = '/profile?i_user=${loginUser.u_no}'
		}
    	function moveToLogOut() {
    		if(confirm('로그아웃 하시겠습니까?')){
	    		location.href = '/logout'
    		}
		}
    	var list1 = new Array();
		console.log('네이버 시작')
    	<c:forEach items="${list}" var="item1">
	    	<c:if test="${item1.w_plat_no == 1}">
		    	list1.push("${item1.w_no}");
		    	list1.push("${item1.w_thumbnail}");
		    	list1.push("${item1.w_title}");
	    	</c:if>
    	</c:forEach>
    	console.log('카카오 시작')
    	<c:forEach items="${list}" var="item1">
	    	<c:if test="${item1.w_plat_no == 3}">
		    	list1.push("${item1.w_no}");
		    	list1.push("${item1.w_thumbnail}");
		    	list1.push("${item1.w_title}");
	    	</c:if>
    	</c:forEach>
    	console.log('레진 시작')
    	<c:forEach items="${list}" var="item1">
	    	<c:if test="${item1.w_plat_no == 4}">
		    	list1.push("${item1.w_no}");
		    	list1.push("${item1.w_thumbnail}");
		    	list1.push("${item1.w_title}");
	    	</c:if>
    	</c:forEach>
    	for (var i = 0; i < list1.length; i++) {
    	    console.log(list1[i])
    	}
    </script>
</body>
</html>