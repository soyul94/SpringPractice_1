<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
<style> .center{text-align:center;}</style>
</head>
<body>

	<%--<%@ include file="" %> : 현재 jsp파일에 include한 파일의 소스를 아예 붙임--%>
	<%--<c:import url=""></c:import> : 결과만 현재 jsp파일에 붙여줌. 외부 사이트에서도 결과 가져올 수 있음. --%>
	<jsp:include page="/WEB-INF/views/member/memberMenu.jsp" /> <%-- 결과만 현재 jsp파일에 붙여줌. 이 사이에도 param태그 사용 가능--%>
	<%--일일히 jsp파일마다 추가하는 방법 외에도 
		web.xml에 공통부분을 정의할 수도 있고 설정파일을 만들어서 공통부분을 추가하도록 하는 것을 도와주는 Tiles와 SiteMesh등이 있다. --%>

	<h2>MEMBER_TABLE</h2></b>
	<p>DB로부터 리스트를 받아와 출력하기</p>
	
	<button onclick="location.href='${pageContext.request.contextPath}/member/add.do'">회원추가</button>
	<br> <br>
	
	<table class="center">
	<tr>
		<th style="width:70px">ID</th>
		<th style="width:70px">NAME</th>
		<th style="width:70px">POINT</th>
		<th>DELETE</th>
	</tr>
		<c:forEach var="vo" items="${memList}" >
			<tr>
				<td>
				<%--<form action="${pageContext.request.contextPath}/member/edit.do" method='get'>
						<input type='hidden' name='memId' value="${vo.memId}">
						<input type='submit' value='<c:out value="${vo.memId}" />'/>
					</form> --%>
					<c:url var="edit" value="/member/edit.do">
						<c:param name="memId" value="${vo.memId}"/>
					</c:url>
					<a href="${edit}"><c:out value="${vo.memId}" /></a>
				</td>
				<td><c:out value="${vo.memName}" /></td>
				<td><c:out value="${vo.memPoint}" /></td>
				<%--int변수에는 악성스크립트 넣기는 어려움 꼭 c태그 안써도 됌. --%>
			<td>
			<form class="btn-del" action="${pageContext.request.contextPath}/member/delete.do" method='get' >
				<input type='hidden' name='memId' value="${vo.memId}">
				<input type='submit' value='삭제' />
			</form>
			</td>
		</c:forEach>
	</tr>
	</table>
	<br>
<script>
	$(document).ready(function(){
		$(".btn-del").click(function(){
			if(!confirm("삭제하시겠습니까 ?")){
				return false;
			} //confirm : 상단에 경고창 뜸								
		});
	});
</script>
</body>
</html>