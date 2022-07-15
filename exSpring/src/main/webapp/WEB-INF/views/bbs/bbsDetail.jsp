<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script> url주소에서 가장 최신의 제이쿼리를 불러옴 -->

<%--프로젝트폴더에 저장한 제이쿼리 파일 불러오기 --%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/comm/Menu.jsp" />

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
	<br>
	<hr>
	
	<%-- 아래의 폼은 아작스 동작을 위한 폼이므로 action 주소와 method 지정이 무의미하다. --%>
	<form id="ajax-addForm" ><%-- action="${pageContext.request.contextPath}/reply/add.do" method="post" --%>
		<input type="hidden" name="repBbsNo" value="${vo.bbsNo}" />
		<textarea name="repContent" rows="5" cols="50"></textarea>
		<input type="button" value="저장" id="saveBtn"/>
	</form>
	
	 <div id="replyList">
	 	
	 </div>
	
	
	
<script>
	$(document).ready(function(){
		$("#a-del").click(function(){
			if(!confirm("삭제하시겠습니까 ?")){
				return false;
			} //confirm : 상단에 경고창 뜸								
		});
	});
</script>

<script type="text/javascript">
	//처음 화면 랜더링하고서도 목록을 띄워야하기 때문에.
	readReplyList();
	
	function deleteBtnClick(){
		alert('삭제클릭');
	}

	function readReplyList(){
		//request 함수처럼 각각의 done과 fail을 분리해서 작성하기 보다 아래와 같이 체이닝으로 작성하는 경우가 더 많다.
		$.ajax({
			  url: "${pageContext.request.contextPath}/reply/list.do",	
			  method: "GET",											
			  data:{ 
				  	repBbsNo : $('[name="repBbsNo"]').val()
			  },	
			  dataType: "json"											
			}).done(function( msg ) {
				console.log(msg);
				
				$("#replyList").empty();//선택된 요소 내부를 비움.
				
				for (var i = 0; i < msg.length; i++) {
					var rep = msg[i];
					console.log(rep.repNo, rep.repContent, rep.repWriter, rep.repRegDate);
					
					$("#replyList").append($("<div>").text(rep.repWriter));
					$("#replyList").append($("<div>").text(rep.repRegDate));
					$("#replyList").append($("<div>").text(rep.repContent));
					
					if(rep.repWriter === "${sessionScope.loginUser.memId}"){
						$('<button>').attr("data-repNo",rep.repNo).addClass('delBtn').text('삭제').appendTo('#replyList');
					}
					
					//attr("속성명","속성값")는 지정된 선택자의 요소에 커스텀 속성을 부여한다. +원래 있는 속성도 주입 가능함.
					//커스텀 속성명을 설정할 때는 기존의 html 속성과 충돌하지 않도록 'data-'로 시작할 것을 권자한다.
					
					$('<hr>').appendTo("#replyList");
					
//					$("#replyList").append(rep.repContent+"<br>");
//					$("#replyList").append("<div>"+rep.repContent+"</div>"); //효율적이지 않음 번거로움
					
//					$("<div>")				//태그 새로 만듬 <div></div>
//					.text(rep.repContent) 	//$("<dib>")로 생성한 곳에 rep.repContent를 대입 -> <div>rep.repContent</div>
//					.appendTo('#replyList')	//$(선택자1).appendTo(선택자2) -> 선택자1을 선택자2로 대입하라
					
//					$("#replyList").append($("<div>").text(rep.repContent)); //$(선택자1).append(선택자2) -> 선택자1에 선택자2를 대입하라
				}			
			}).fail(function( jqXHR, textStatus ) {
			  alert( "Request failed: " + textStatus );
			});
	}
	
	
	
	// click(function(){}) == on.('click',function(){}) 와 동일	
//아래의 코드는 문서안에 있는 delBtn을 찾으라는 건데 댓글리스트는 html의 렌더링이 끝난 후 생성된 것이기 때문에 효용이 없다.
//즉, 맨 처음 선택자는 처음부터 존제하는 부모 엘리먼트여야한다.
/* 	$('.delBtn').on('click', function(){
		alert('삭제 버튼 클릭');
	}); */
/*  $(document).on('click','.delBtn', function(){
		alert('삭제 버튼 클릭');
	}); */  //document에서 클릭 이벤트가 발생했을 때 이 안에 있는 'delBtn'이 클릭 되었을 때 함수를 실행하라
			//document로 설정하면 해당 페이지의 모든 요소를 다 매번 확인해야하므로 비효율 적임. -> 즉, 선택자는 좁은 영역을 스캔하도록 해줘야한다.
	$("#replyList").on('click','.delBtn', function(){
		alert('삭제 버튼 클릭');
		$.ajax({
				  url: "${pageContext.request.contextPath}/reply/delete.do",	
				  method: "GET",											
				  data:{ 
					  repNo : $(this).attr('data-repNo'),
				  },	
				  dataType: "json"											
			}).done(function( msg ) {
				if(msg.num==0){
					alert(msg.num + "본인만 삭제 가능합니다");
				}
				else{
					alert(msg.num + "개 댓글 삭제 성공");
				}
				readReplyList();
						
			}).fail(function( jqXHR, textStatus ) {
			  	alert( "Request failed: " + textStatus );
			});
	});
	
	$('#saveBtn').on('click', function(){ //saveBtn을 클릭하면 함수 실행
		var request = $.ajax({
		  url: "${pageContext.request.contextPath}/reply/add.do",	//요청주소
		  method: "POST",											//요청방식
		  data: { repBbsNo : $('[name="repBbsNo"]').val(), 
			  	  repContent : $('[name="repContent"]').val()  },	//파라미터. 
		//data : $('#ajax-addForm').serialize() //-> 보내야할 파라미터 갯수가 많을 때 작성 방식.
		//data: 'repBbsNo :'+ $('[name="repBbsNo"]').val()+ 'repContent : '+$('[name="repContent"]').val() -> data를 get주소 방식으로 ㅈㄴ송
		  dataType: "json"											//요청의 결과(서버의 응답)으로 받을 데이터의 형식
		});
		 
		//요청에 대한 응답을 성공적으로 받았을 때 실행할 함수
		request.done(function( msg ) {
		 /*  $( "#log" ).html( msg ); */
			//서버로부터 받은 응답이 인자로 전달된다. 즉, 위의 결과 값의 msg로 전달되는 것
			$('[name="repContent"]').val("");
			//var data = JSON.parse(msg); 	//문자열을 JSON형식으로 변환해줌 -> dataType: "json"이면 자동으로 해줌
		 	alert(msg.num + "개 댓글 등록 성공");
		 	readReplyList();
		});
		
		//요청이 실패한 경우 실행할 함수
		request.fail(function( jqXHR, textStatus ) {
		  alert( "Request failed: " + textStatus );
		});	
		
		//요청이 성공하든 실패하든 요청 처리가 끝나며 항상 실행할 함수
		request.always(function(){
			console.log("항상 실행하지롱");
		});
	});
	//done과 fail말고도 성공실패 여부와 관계없이 항상 작동하는 always함수도 있다. -> 제이쿼리 최근 버전으로 이 형식을 권장하고 있음.
	//문성진 교수님이 했던 것 처럼 ajax 설정 내부에 success와 error, complete 설정으로 done과 fail, always를 작성하기도 한다. 이는 초기버전 형식.
	
	
	

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>