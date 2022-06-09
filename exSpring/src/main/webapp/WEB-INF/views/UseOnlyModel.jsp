<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useOnlyModel.jsp</title>
</head>
<body>
/exSpring/src/main/webapp/WEB-INF/views/UseOnlyModel.jsp
						 /WEB-INF/views/UseOnlyModel.jsp
	<p><c:out value="${myVO.myId }"/></p> <%--JSP에서는 getter를 사용하지 않고 그냥 필드명으로 값을 받을 수 있다--%>
	<p><c:out value="${myVO.myName }"/></p>
</body>
</html>