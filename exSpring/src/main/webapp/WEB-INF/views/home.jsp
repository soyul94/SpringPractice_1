<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page session="false" %> 요건 없어도댕--%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p><c:out value="Model에 저장한 값 : ${modelVal}"/></p>
<p><c:out value="ModelMap에 저장한 값 : ${modelMapVal}"/></p>
<p><c:out value="Map에 저장한 값 : ${mapVal}"/></p>
<br>
<p><c:out value="@ModelAttribute로 저장한 값 : ${modelVo.myId}, ${modelVo.myName}"/></p>
<p><c:out value="@ModelAttribute을 생략한 값 : ${myVO.myId}, ${myVO.myName}"/></p>

</body>
</html>
