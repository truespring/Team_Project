<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<style type="text/css">
	@font-face {font-family: 'GmarketSansMedium';src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');font-weight: normal;font-style: normal;}
	section{background-color: #F8F8F8; margin: 15px auto; height: 780px; background-color: #F8F8F8;
 			border-top: 1px solid #EAEAEA; text-align: center;}
 	section .frmContainer{margin: 15px auto; text-align: center;}
 	.profileImg{width: 200px; border-radius: 50%; object-fit: cover; overflow: hidden;}
 	.name{color: gray; font-weight: gray; font-weight: bold;}
 	.updName{width: 280px; height: 41px;background: #FFFFFF; padding-left: 30px; 
	padding-right:20px; border: 1px solid #4FA2C7; box-sizing: border-box; border-radius: 10px;
	margin: 15px auto;}
	.updName[type="search"]{font-family: 'GmarketSansMedium', serif ;
	line-height: normal;padding-top: 6px;}
	.updName:hover{cursor: pointer;}
	.imgFile[type="file"]{font-family: 'GmarketSansMedium', serif ; width:200px;  text-align: center; margin: 5px auto;}
	input[type="submit"]{font-family: 'GmarketSansMedium', serif ;}
	.box-file-input label{
	font-family: 'GmarketSansMedium', serif ;
    display:inline-block;
    background: #4FA2C7
	}
</style>
</head>
<body>
<div id	="container">
	<jsp:include page="../header/header.jsp"></jsp:include>
	<section>
		<h1>프로필 변경</h1>
		<div class="printImage">
			<c:choose>
				<c:when test="${profileData.profile == ''}">
					<img src="/images/u_profile/default_image.jpg" class="profileImg">
				</c:when>
			</c:choose>
		</div>
		<div class="frmContainer">
			<form action="/profile" method="post" enctype="multipart/form-data">
				<div>
					<span class="name">이름</span>&nbsp;&nbsp;
					<div><input type="search" name="updName" value="${profileData.name}" class="updName"></div>
					<input type="file" name="profile_img" accept="image/*" value="이미지 선택" class="imgFile">
					<input type="submit" value="업로드">
				</div>
			</form>
		</div>
	</section>
</div>
</body>
<script type="text/javascript">
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
</script>
</html>