<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 bbsList.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>
	<h1>게시판</h1>
	<p>BbsController의 bbsList메소드 실행.</p>
	
	<table>
		<tr class="table-secondary">
			<th>게시글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="vo" items="${list}" >
			<tr class=".table-hover">				
				<td>
					<c:url var="detail" value="/bbs/detail.do">
						<c:param name="bbsNo" value="${vo.bbsNo}"/>
					</c:url>
					<a href="${detail}">
						<c:out value="${vo.bbsNo}"/>
					</a>
				</td>
				<td><c:out value="${vo.bbsTitle}"/></td>
				<td><c:out value="${vo.bbsWriter}"/></td>
				<td><c:out value="${vo.bbsRegDate}"/></td>
			</tr>
		</c:forEach>
	</table>
	<c:url var="addUrl" value="/bbs/form.do">
		<c:param name="menu" value="add"/>
	</c:url>
	<a href="${addUrl}">글쓰기</a>
<!-- 	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
 -->
</body>
</html>