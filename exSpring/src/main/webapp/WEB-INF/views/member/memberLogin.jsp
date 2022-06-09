<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 : MemberLogin.jsp</title>
<style> .center{text-align:center;}</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/member/memberMenu.jsp" />

<h2>MEMBER_TABLE</h2>
<p>로그인 하기</p>	

<form action="${pageContext.request.contextPath}/member/login.do" method="post">
ID&emsp;&emsp;&emsp;&emsp;&emsp;: <input type = "text" name="userId"/><br>
PASSWORD&emsp;: <input type="password" name="userPW"/><br>

<input type="submit" value="로그인"/>
</form>

<%-- <button onclick="location.href='<c:url value="/member/list.do"/>'">목록가기</button> --%>
</body>
</html>