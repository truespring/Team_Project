<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Cute+Font&family=Noto+Sans+KR&family=Noto+Serif+KR:wght@600&display=swap" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>웹툰 상세 페이지</title>
<style>
   .container {width: 100%; margin: 0 auto; font-family: 'Noto Serif KR', serif ;}
   #btn_login {text-decoration: none; color: black;}
   .detail {border: 1px solid black; margin: 0 auto; width: 1200px; padding: 10px; clear: both; position: relative;}
   #thumbnail img {width: 180px; border-radius: 5%;height: 180px;   margin: 20px;display: inline-block;}
   #title, #writer, #story, #platform {display: inline-block;position: absolute;}
   #title {left: 230px;top: 30px;}
   #writer {left: 230px;top: 60px;}
   #platform {   left: 230px;top: 90px;}
   #story {left: 230px;top: 120px;}
   .comment {border: 1px solid black; margin: 50px auto; width: 1200px; padding: 10px; }
   #cmtFrm, #comment {margin: 10px;}
   .startRadio__box, #comment, #submit{text-align: center;}
   #comment {height: 60px;}
   #comment #cmt {width: 800px; height: 40px; padding: 5px;}
   #cmt_btn {margin: 10px; width: 300px; border: none; height: 50px; background-color: #4FA2C7; color: white;font-size: 1.1em;}
    @font-face {font-family: 'GmarketSansMedium';src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');font-weight: normal;font-style: normal;}
    .container {width: 100%; height:1000px;  margin: 0 auto; font-family: 'GmarketSansMedium', serif ;}
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
   .containerPImg {display: inline-block;width: 40px;height: 40px;border-radius: 50%;overflow: hidden;position: absolute;top: 20px;right: 320px;}
   .containerPImg:hover{cursor: pointer;}   
   .pImg {   object-fit: cover;height: 100%;width: 100%;}
   button:hover {cursor: pointer;}
   .blind { position: absolute; overflow: hidden; margin: -1px;padding: 0;width: 1px;height: 1px;border: none;clip: rect(0, 0, 0, 0);}
   .startRadio {display: inline-block; overflow: hidden; height: 40px;}
   .startRadio:after { content: ""; display: block; position: relative; z-index: 10; height: 40px;
           background: url("data:image/png;base64,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")
           repeat-x 0 0; background-size: contain; pointer-events: none;}
   .startRadio__box { position: relative; z-index: 1; float: left; width: 20px; height: 40px;cursor: pointer;}
   .startRadio input { opacity: 0 !important; height: 0 !important;width: 0 !important;position: absolute !important;}
   .startRadio input:checked + .startRadio__img { background-color: #ffd700;}
   .startRadio__img { display: block; position: absolute;right: 0; width: 500px;height: 40px;pointer-events: none;}   
</style>
</head>
<body>
   <div class="container">
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
      <div class="detail">
         <div id="thumbnail">
            <img src="${data.w_thumbnail }">
         </div>
         <div id="title">${data.w_title }</div>
         <div id="writer">${data.w_writer }</div>
         <div id="story">${data.w_story}</div>
         <div id="platform">${data.w_plat_nm }</div>
      </div>
      <!-- 댓글 부분 -->
      <div class="comment">
         <form action="/webtoon/cmt" method="post" id="cmtFrm" name="cmtFrm" onsubmit="return chk()">
            <div class="startRadio">
               <c:forEach begin="1" end="10" step="1" var="item">
                  <label class="startRadio__box">
                     <input type="radio" name="star" id="" onclick="score(${item})">
                     <span class="startRadio__img"><span class="blind"></span></span>
                  </label>
               </c:forEach>
               <input type="hidden" id="point" name="c_rating" value="${cmtFrm.u_no.value == '' ? '0.0' : myCmt.c_rating }" required>
               <input type="hidden" id="cmtChk" name="cmtChk" value="0">
            </div>
               <!-- 댓글 남기기 -->
            <div id="comment"><input type="text" id="cmt" name="c_com" placeholder="댓글을 남겨주세요" value="${myCmt.c_com }"></div>
            <!-- 완료 후 보내기 -->
            <div id="submit"><input type="submit" id="cmt_btn" value="${myCmt.c_com == null ? '등록하기' : '수정하기' }"></div>
            <div><input type="hidden" name="w_no" value="${data.w_no }"></div>
            <input type="hidden" id="u_no" name="u_no" value="${loginUser.name }">
         </form>
      </div>
   </div>
   <script>
      if(cmtFrm.point.value != '0.0'){
         document.cmtFrm.star[Number(cmtFrm.point.value*2-1)].checked = true
      }
      if(cmtFrm.cmt_btn.value == '수정하기'){
         console.log('누르기 전 ' + cmtFrm.cmtChk.value)
         cmtFrm.cmtChk.value = 1
         console.log('누른 후 ' + cmtFrm.cmtChk.value)
      }
      
      function score(star) { // 별점주기
         console.log('star : ' + star)
         console.log('star type' + typeof star)
         point.value = parseFloat(star)/2
         console.log('point.value : ' + point.value)
      }
   
      function chk() {
         if(cmtFrm.u_no.value == '') {
            alert('먼저 로그인 해주세요')
            return false
         }else if (point.value <= 0) { // 별점 체크
            alert('별점을 입력해 주세요')
            return false
         } else if (cmtFrm.cmt.value <= 0) { // 댓글 체크
            alert('댓글을 작성해 주세요')
            cmtFrm.cmt.focus()
            return false
         }
         alert('댓글이 등록되었습니다')
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
</body>
</html>