<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>게시글 form</title>
</head>
<body>
<h1>게시글 상세</h1>
	<p>BbsController의 bbsForm메소드 실행.</p>
	<button onclick="location.href='<c:url value="/bbs/list.do"/>'">목록가기</button>
	
	<c:choose>
		<c:when test="${menu eq 'add'}">
			<c:url var="formUrl" value="/bbs/add.do"></c:url>
		</c:when>
		<c:when test="${menu eq 'edit'}">
			<c:url var="formUrl" value="/bbs/edit.do">
				<c:param name="bbsNo" value="${user.bbsNo}"></c:param>
			</c:url>
		</c:when>
	</c:choose>
	
	<form action="${formUrl}" method="post">
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="bbsTitle" value="<c:out value="${user.bbsTitle}"/>" style="width:395px;"/>
				</td>	
			</tr>
			<c:if test="${menu eq 'add'}">
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="bbsWriter" value="<c:out value="${user.bbsWriter}"/>" style="width:395px;" />
					</td>
				</tr>
			</c:if>
			<tr>
				<th>내용</th>
				<td>
					<%-- <input type="text" name="bbsContent" value="<c:out value="${vo.bbsContent}"/>" /> --%>
					<textarea name="bbsContent" rows="15" cols="50" style="resize: none;"><c:out value="${user.bbsContent}"/></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="등록하기"/>
	</form>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
</body>
</html>