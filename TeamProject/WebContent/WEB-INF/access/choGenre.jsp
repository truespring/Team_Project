<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="container">
	<!-- 헤더 삽입 -->
	<jsp:include page="../header/header.jsp"></jsp:include>
	<!-- section 부분 -->
	<form action="/choGenre" method="post">
	<div id="genre_arr"></div>
	<input type="hidden" name="user_id" value="${param.user_id}">선호 장르
	<select id="genres" onchange="sel_genre()">
		<option value="">장르 선택</option>
		<option value="romance">로맨스</option>
		<option value="romance">드라마</option>
		<option value="comic">일상</option>
		<option value="school">감성</option>
		<option value="action">학원</option>
		<option value="horror">코믹</option>
		<option value="day">시대극</option>
		<option value="gene">판타지</option>
		<option value="e">액션</option>
		<option value="romance">소년</option>
		<option value="romance">스포츠</option>
		<option value="romance">미스터리</option>
		<option value="romance">공포</option>
		<option value="romance">스릴러</option>
		<option value="romance">실용,교양</option>
	</select>
<div id="selected_genre"></div>
		<input type="submit" value="확인">
	</form>
</div>
<script>
	var genres_arr = new Array();
	function sel_genre(){
		var sel_text = genres.options[genres.selectedIndex].text;
		if(genres_arr.length <3 && genres_arr.indexOf(sel_text) == -1){
			genres_arr.push(sel_text);
			showlist();
		}
	//	genre_arr.innerHTML = "<input type='hidden' name='genre_arr' value="+ genres_arr +">";
		inputhtml(genres_arr);
	}
	function cancleSel(i){
		genres_arr.splice(i,1);
		showlist();
	}
	function showlist(){
		selected_genre.innerHTML = ""
		for(var i=0; i<genres_arr.length; i++){
			selected_genre.innerHTML += genres_arr[i] + "<a onclick='cancleSel("+i+")' >x</a>"
		}
	}
	function inputhtml(arr){
		genre_arr.innerHTML = "";
		for(var i=0; i<arr.length; i++){
			genre_arr.innerHTML += "<input type='hidden' name='genre_arr' value="+ arr[i] +">";
		}
	}
</script>
</body>
</html>