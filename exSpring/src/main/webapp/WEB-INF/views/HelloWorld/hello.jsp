<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 HelloWorld/hello.jsp</title>
</head>
<body>

<p><c:out value="핸들러로 받은 객체를 이용한 계산결과 : ${myValue.x} + ${myValue.y} = ${myValue.sum}" /></p>
<p><c:out value="Model.Attribute()를 이용한 계산결과 : ${modelMyValue.x} + ${modelMyValue.y} = ${modelMyValue.sum}" /></p>
<br>
<p><c:out value="현재 서버 시간 : "/><fmt:formatDate value="${time}" pattern="yyyy년 M월 d일 H시 m분" /> </p>

</body>
</html>