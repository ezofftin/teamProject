<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   
<%@ include file="../inc/header.jsp" %>
<div class="container mt-5">
<h3>스프링 게시판</h3>
<div>
<form id="searchForm" method="post" action="list.do">
	<div class="d-flex justify-content-end">
		<select class="form-select me-2" name="searchType" 
			style="width:100px">
			<option
				<c:out value="${vo.searchType == null ? 'selected':''}"/>>선택</option>
			<option value="S"
				<c:out value="${vo.searchType == 'S' ? 'selected':''}"/>>제목</option>
			<option value="C"
				<c:out value="${vo.searchType eq 'C' ? 'selected':''}"/>>내용</option>
			<option value="W"
				<c:out value="${vo.searchType eq 'W' ? 'selected':''}"/>>글쓴이</option>
		</select>
		<input class="form-control rounded-0 rounded-start" type="text" 
			id="keyWord" name="keyWord"	placeholder="Search" 
			style="width:250px" value="${vo.keyWord}">
		<button id="btn-search" class="btn rounded-0 rounded-end" 
			style="width:40px; background:#138496; color:white" ><i class="fa fa-search"></i></button> 
	</div>
</form>
</div>

<div class="m-0 my-1"><b>${bp.viewPage}</b> / ${bp.totalPage} pages</div>
<table class="table">
	<thead style="background:#8091a3; color:white">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>글쓴이</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="bno" value="${bp.startRowNum}"/>
		<c:forEach var="lvo" items="${list}">
		<tr>
<%-- 			<td>${vo.bid}</td> --%>
			<td>${bno}</td>
			<td><a href="<c:url value='/view.do?bid=${lvo.bid}&viewPage=${bp.viewPage}&searchType=${vo.searchType}&keyWord=${vo.keyWord}'/>"/>${lvo.subject}</a></td>
			<td>${lvo.hit}</td>
			<td>${lvo.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${lvo.regDate}"/></td>
		</tr>
		<c:set var="bno" value="${bno-1}"/>
		</c:forEach>
	</tbody>
</table>

<!-- 페이지 네비게이션(블럭) -->
<ul class="pagination justify-content-center my-5">
  <li class="page-item ${bp.prevPage <=0 ? 'disabled' : ''}">
  	<a class="page-link" href="list.do?viewPage=${bp.prevPage}">이전</a>
  </li>
  
  <c:forEach var="i" begin="${bp.blockStart}" end="${bp.blockEnd}">
	  <li class="page-item ${bp.viewPage == i ? 'active':''}">
	  	<a class="page-link" href="list.do?viewPage=${i}&searchType=${vo.searchType}&keyWord=${vo.keyWord}">${i}</a>
	  </li>
  </c:forEach>
  
  <li class="page-item ${bp.blockEnd >= bp.totalPage ? 'disabled': ''}">
  	<a class="page-link" href="list.do?viewPage=${bp.nextPage}">다음</a>
  </li>
</ul>

<div class="text-center">
	<button class="btn btn-primary" id="btn-write"><i class="fa fa-pencil-square-o"></i> 글쓰기</button>
</div>
		
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn-write").click(() => {
			location.href="<c:url value='register.do?viewPage=${bp.viewPage}'/>";
		})
		
		var sf = $("#searchForm");
		
		$("#btn-search").on("click", function(e){
			if(!sf.find("input[name='keyWord']").val()){
				alert("키워드를 입력하세요!!");
				$("#keyWord").focus();
				return false;
			}
			
			sf.submit();
		});
	});
</script>

<%@ include file="../inc/footer.jsp" %>