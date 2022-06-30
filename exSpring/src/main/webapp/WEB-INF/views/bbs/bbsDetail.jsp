<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>
<h1>게시글 상세</h1>
	<p>BbsController의 bbsDetail메소드 실행.</p>
	
	<button onclick="location.href='<c:url value="/bbs/list.do"/>'">목록가기</button>
	<table class="table table-bordered"> 
		<tr>
			<th class="table-primary">번호</th>
			<td><c:out value="${vo.bbsNo}"/></td>
			<th class="table-primary">작성자</th>
			<td><c:out value="${vo.bbsWriter}"/></td>
			<th class="table-primary">작성일</th>
			<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy-MM-dd"/></td>
			<th class="table-primary">조회수</th>
			<td><c:out value="${vo.bbsCount}"/></td>
		</tr>
		<tr>
			<th class="table-primary" colspan="2">제목</th>
			<td colspan="6"><c:out value="${vo.bbsTitle}"/></td>
		</tr>
		<tr>
			<th class="table-primary" colspan="2">내용</th>
			<td colspan="6"><c:out value="${vo.bbsContent}"/></td>
		</tr>
	</table>
	<c:url var="formUrl" value="/bbs/form.do">
		<c:param name="menu" value="edit"/>
		<c:param name="bbsNo" value="${vo.bbsNo}"/>
	</c:url>
	<c:url var="deleteUrl" value="/bbs/delete.do">
		<c:param name="bbsNo" value="${vo.bbsNo}"/>
	</c:url>
	<a href="${formUrl}">수정</a>&emsp;<a id="a-del" href="${deleteUrl}">삭제</a> 
	<%-- <button id="a-del" onclick="location.href='${deleteUrl}'">목록가기</button>--%>	
	
<script>
	$(document).ready(function(){
		$("#a-del").click(function(){
			if(!confirm("삭제하시겠습니까 ?")){
				return false;
			} //confirm : 상단에 경고창 뜸								
		});
	});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>