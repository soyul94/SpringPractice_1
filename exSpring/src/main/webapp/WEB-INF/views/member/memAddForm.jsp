<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
<style> .center{text-align:center;}</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/comm/Menu.jsp" />

<h2>MEMBER_TABLE</h2>
<p>DB에 인스턴스 추가하기</p>	

<form action="${pageContext.request.contextPath}/member/add.do" method="post">
ID&emsp;&emsp;&emsp;&emsp;&emsp;: <input type = "text" name="memId"/><br>
PASSWORD&emsp;: <input type="password" name="memPW"/><br>
NAME&emsp;&emsp;&emsp;: <input type="text" name="memName"/><br>
POINT&emsp;&emsp;&emsp;: <input type="text" name="memPoint"/><br>
<input type="submit" value="등록"/>
</form>
<%-- <button onclick="location.href='${pageContext.request.contextPath}/member/list.do'">목록가기</button> --%>
<button onclick="location.href='<c:url value="/member/list.do"/>'">목록가기</button>
</body>
</html>