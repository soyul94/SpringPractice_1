<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 bbsList.jsp</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous"> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- url주소에서 가장 최신의 제이쿼리를 불러옴 -->
</head>
<body>

<jsp:include page="/WEB-INF/views/comm/Menu.jsp" />

	<h1>게시판</h1>
	<p>BbsController의 bbsList메소드 실행.</p>
	
	<%-- 검색창 만들기 --%>
	<form action="<c:url value="/bbs/list.do"/>">
		<select name="searchType">
			<%-- <option value="title" ${searchInfo.searchType=='title' ? 'selected':''}>제목</option> 
			<option value="content" <c:if test="${searchInfo.searchType == 'content'}">selected</c:if>>내용</option>
			<option value="total" <c:if test="${searchInfo.searchType == 'total'}">selected</c:if>>제목+내용</option> --%>
			<option value="title" >제목</option> 
			<option value="content" >내용</option>
			<option value="total" >제목+내용</option>
		</select>
		<script type="text/javascript">
			//자바스크립트에서 빈문자열은 false이다.
			if('${searchInfo.searchType}'){ //if('${searchInfo.searchType}'!='')
				$('[name="searchType"]').val('${searchInfo.searchType}');
			}
		</script>
		
		<input type="text" name="searchWord" placeholder="검색어를 입력하세요" value="${searchInfo.searchWord}" />
		<input type="submit" value="검색" /><%--value="${param.searchWord}"  --%>
	</form>
	<br>


	<table class="table table-hover ">
		<thead class="table-group-divider">
		<tr class="table-primary">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		</thead>
		<tbody class="table-hover ">
		<c:forEach var="vo" items="${list}" >
			<tr>				
				<td style="width:10%;">
					<c:out value="${vo.bbsNo}"/>
				</td>
				<td style="width:60%;">
					<c:url var="detail" value="/bbs/detail.do">
						<c:param name="bbsNo" value="${vo.bbsNo}"/>
					</c:url>
					<a href="${detail}">
						<c:out value="${vo.bbsTitle}"/>
					</a>
				</td>
				<td style="width:10%;"><c:out value="${vo.bbsWriter}"/></td>
				<td style="width:20%;"><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	<c:url var="addUrl" value="/bbs/form.do">
		<c:param name="menu" value="add"/>
	</c:url>
	<a href="${addUrl}">글쓰기</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- 	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>	
 -->
</body>
</html>