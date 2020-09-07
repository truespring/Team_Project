<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의 웹툰(검색 결과)</title>
<style type="text/css">
    img{width: 180px; border-radius: 5%;}
    .container section {width: 1200px; padding: 10px; margin: 0 auto; clear: both;}
    h2{margin-top: 0px; width: 300px; margin-left: 80px;}
    .webtoonContainer {width: 1100px; height:180px; padding: 10px; position: relative;}
    ul {list-style-type: none; clear: both; height: 185px; margin: 0 auto;padding-bottom: 15px;}
    ul li:nth-child(1) {float: left; padding: 30px; padding-top: 0px;}
    ul li img{ border-radius: 10%;
	    transform: scale(1.1);
	    -webkit-transform: scale(1.1);
	    -moz-transform: scale(1.1);
	    -ms-transform: scale(1.1);
	    -o-transform: scale(1.1);
	    transition: all 0.2s ease-in-out;
	    margin-right: 20px;
	    margin-top: 5px;	
	    }
    ul li img:hover {cursor: pointer;
	    transform: scale(1.2);
	    -webkit-transform: scale(1.2);
	    -moz-transform: scale(1.2);
	    -ms-transform: scale(1.2);
	    -o-transform: scale(1.2);}
	ul li a{color: #0c65c6; text-decoration: none;}
    ul li:nth-child(2) {font-weight: bold; font-size: 1.2em; padding-top: 1px;}
    ul li:nth-child(3) {line-height: 25px;}
    ul li:not(:first-child){margin-top: 15px;}
    ul li .list{color: gray; font-weight: gray; font-weight: bold;}
    ul .thumbnail{width: 180px; height: 160px; margin-top: 10px;}
</style>
</head>
<body>
   <div class="container">
   <jsp:include page="../header/header.jsp"></jsp:include>
   <section>
      <div class="content">
         <div class="aboveContainer"><h2>상위 검색 결과</h2></div>
           <hr style="width: 1100px;">
	    <c:forEach items="${result}" var="item">
	       <div class="webtoonContainer">
	          <ul class="itemRow">
	             <li><img class="thumbnail" src=" ${item.w_thumbnail }" onclick="moveToDetail(${item.w_no	})"></li>
	             <li><a href="/webtoon/detail?w_no=${item.w_no}">${item.w_title }</a></li>
	             <li><span class="list" id="ctnt">내용</span> &nbsp; ${item.w_story }</li>
	             <li><span class="list">작가</span> &nbsp; ${item.w_writer}</li>
	             <li><span class="list">장르</span> &nbsp; ${item.w_genre}</li>
	          </ul>
	       </div>
	    </c:forEach>
      </div>
   </section>
</div>
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
</body>
</html>