<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
<style> .center{text-align:center;}</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/comm/Menu.jsp" />

<h2>MEMBER_TABLE</h2>
<p>DB에 인스턴스 변경하기</p>	

<%-- <c:out value="${identification}"/> --%>
<button onclick="location.href='<c:url value="/member/list.do"/>'">목록가기</button>

<form action="${pageContext.request.contextPath}/member/edit.do" method="post">
ID&emsp;&emsp;&emsp;:
<c:choose>
	<c:when test="${identification ne 'true'}">
		<input type="text" name="memId" value="${memVo.memId}" disabled />
	</c:when>
	<c:otherwise>
		<input type="text" name="memId" value="${memVo.memId}" readonly="readonly"/>
	</c:otherwise>
</c:choose>
<br>
<%-- PASSWORD&emsp;: <input type="password" name="memPW" value="${memVo.memPW}"/><br> --%>
NAME&emsp;: 		 <input type="text" name="memName" value="${memVo.memName}" <c:if test="${identification ne 'true'}">disabled</c:if> /><br>
POINT&emsp;: 		 <input type="text" name="memPoint" value="${memVo.memPoint}"<c:if test="${identification ne 'true'}">disabled</c:if> /><br>
<c:if test="${identification eq 'true'}"> <input type="submit" value="변경"/>	</c:if>
<%--<c:if test="${sessionScope.loginUser.memId ep memVo.memId}"> --%>
										
</form>
<%-- <button onclick="location.href='${pageContext.request.contextPath}/member/list.do'">목록가기</button> --%>

<script>
	<c:if test="${identification ne 'true'}">
		alert("관리자와 본인만 변경할 수 있습니다.");
	</c:if>
</script>
</body>
</html>

<%-- HTML주석 <!--  -->은 브라우저로 전송이 되며 보이지만 않는 것 --%>
<%--JSP주석 <%-%>은 브라우저로 아예 전송이 되지 않는 것--%>