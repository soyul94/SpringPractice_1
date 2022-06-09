<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 MemberMenu.jsp 이다</title>
</head>
<body>

<div>
	<%--서블릿에서 session에 저장한 파라미터를 확인함.(세션말고 다른 곳에 저장 안했다면 그냥 파라미터명만 써도 됨) --%>
	<c:if test="${sessionScope.loginUser eq null }"> 
		<a href="${pageContext.request.contextPath}/member/login.do" >로그인</a> ||
		<a href="${pageContext.request.contextPath}/member/add.do" >회원가입</a>
	</c:if>
	
	<c:if test="${sessionScope.loginUser ne null }"> 
		<c:out value=" ${sessionScope.loginUser.memName}님 안녕하세요 "/> || 
		<a href="${pageContext.request.contextPath}/member/logout.do" >로그아웃</a> 
	</c:if>
	<hr>
</div>

</body>
</html>