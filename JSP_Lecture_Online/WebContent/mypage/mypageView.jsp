<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "Mypage"/>
</jsp:include>

<form action = "mypageLogic.jsp" method = "post">
	<h2>�� ���� ����</h2>
	PW
	<input type = "password" name = "user_password" 
	       placeholder = "${currentNickname }���� ��й�ȣ" required>
	<br>
	<input type = "submit" value = "�� ���� ����">
</form>

<jsp:include page = "/layout/footer.jsp"/>