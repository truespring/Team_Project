<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp" >
	<jsp:param name = "title" value = "Mypage"/>
</jsp:include>
<c:set var = "message">
	<c:choose>
		<c:when test = "${result }">
			ȸ�� ������ �����Ͽ����ϴ�.
		</c:when>
		<c:otherwise>
			ȸ�� ������ �����Ͽ����ϴ�.
		</c:otherwise>
	</c:choose>
</c:set>

<h2>
	${message } <br>
</h2>
<input type = "button" value = "��������" onclick = "location.href='/myhome'">

<jsp:include page = "/layout/footer.jsp" />