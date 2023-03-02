<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../inc/header.jsp" %>  
<div class="container mt-5">
<h3>스프링 게시판</h3>

<div>
	<form id="searchForm" method="post" action="list.do">
		<div class="d-flex justify-content-center">
			<select class="form-select me-2" style="width:200px" name="searchType">
				<option value="">선택</option>
				<option value="S"
					<c:out value="${pDto.searchType == 'S' ? 'selected' : ''}"/>>제목</option>
				<option value="C"
					<c:out value="${pDto.searchType == 'C' ? 'selected' : ''}"/>>내용</option>
				<option value="W"
					<c:out value="${pDto.searchType == 'W' ? 'selected' : ''}"/>>글쓴이</option>					
				<option value="SC"
					<c:out value="${pDto.searchType == 'SC' ? 'selected' : ''}"/>>제목+내용</option>
				<option value="SW"
					<c:out value="${pDto.searchType == 'SW' ? 'selected' : ''}"/>>제목+글쓴이</option>
				<option value="SCW"
					<c:out value="${pDto.searchType == 'SCW' ? 'selected' : ''}"/>>제목+내용+글쓴이</option>
					
			</select>
			<input type="text" id="keyWord" name="keyWord" placeholder="검색어 입력"
				style="width:300px" class="form-control rounded-0 rounded-start" value="${pDto.keyWord}"/>
			<button class="btn rounded-0 rounded-end" style="background:#138499; color:white"><i class="fa fa-search"></i></button>
		</div>
	</form>
</div>
<div class="mt-3 d-flex justify-content-between">
	<div>
		<b>${pDto.viewPage}</b> / ${pDto.totalPage} pages 
	</div>

	<!-- 검색이 없을 경우 -->
	<c:if test="${pDto.searchType == null || pDto.searchType == ''}">
	<div>
		<select class="form-select form-select-sm" id="cntPerPage">			
			<option value="5"
				<c:out value="${pDto.cntPerPage == 5 ? 'selected':''}"/>>게시글 5개</option>
			<option value="10"
				<c:out value="${pDto.cntPerPage == 10 ? 'selected':''}"/>>게시글 10개</option>
			<option value="20"
				<c:out value="${pDto.cntPerPage == 20 ? 'selected':''}"/>>게시글 20개</option>
		</select>
	</div>
	</c:if>
	
	<!-- 검색할 경우 -->
	<c:if test="${pDto.searchType != null && pDto.searchType != ''}">
	<div>
		<select class="form-select form-select-sm" id="cntPerPage">
		<c:choose>
         <c:when test = "${pDto.totalCnt <= 5}">
         	<option value="5"
				<c:out value="${pDto.cntPerPage == 5 ? 'selected':''}"/>>선택 없음</option>
         </c:when>
         
         <c:when test = "${pDto.totalCnt > 5 && pDto.totalCnt< 10}">
         	<option value="5"
				<c:out value="${pDto.cntPerPage == 5 ? 'selected':''}"/>>게시글 5개</option>         	
         	<option value="10"
				<c:out value="${pDto.cntPerPage == 10 ? 'selected':''}"/>>게시글 10개</option>
         </c:when>
         
         <c:when test = "${pDto.totalCnt >= 10 && pDto.totalCnt < 20}">
         	<option value="5"
				<c:out value="${pDto.cntPerPage == 5 ? 'selected':''}"/>>게시글 5개</option>
			<option value="10"
				<c:out value="${pDto.cntPerPage == 10 ? 'selected':''}"/>>게시글 10개</option>		
         </c:when>
         <c:otherwise>
          	<option value="5"
				<c:out value="${pDto.cntPerPage == 5 ? 'selected':''}"/>>게시글 5개</option>
			<option value="10"
				<c:out value="${pDto.cntPerPage == 10 ? 'selected':''}"/>>게시글 10개</option>
			<option value="20"
				<c:out value="${pDto.cntPerPage == 20 ? 'selected':''}"/>>게시글 20개</option>
         </c:otherwise>
      	</c:choose>
		</select>
	</div>
	</c:if>

</div>

<table class="table">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>글쓴이</th>
			<th>등록일</th>
		</tr>
	</thead>
	 
	<%-- XSS(Cross-site Scripting) 공격 : 웹사이트에 스크립트 코드를 주입시켜서 웹사이트를 공격하는
		해킹 기법
		
		태그를 해석하지 못하도록 하면 이공격을 방어할 수 있다.
		JSP에서는 이공격을 방어하기위해서 <c:out />를 사용한다. 
	--%>
	 
	
	<tbody>
		<c:set var="rowNum" value="${pDto.startRowNum}"/>
		<c:forEach var="dto" items="${list}">
		<tr>
			<%--<td>${dto.bid}</td> --%>
			<td>${rowNum}</td>
			<td><a href="<c:url value='/boardView.do?bid=${dto.bid}&viewPage=${pDto.viewPage}&cntPerPage=${pDto.cntPerPage}
			&searchType=${pDto.searchType}&keyWord=${pDto.keyWord}'/>"/><c:out value="${dto.subject}"/></a></td>
			<td>${dto.hit}</td>
			<td><c:out value="${dto.writer}"/></td>
			<td>${dto.regDate}</td>
		</tr>
		<c:set var="rowNum" value="${rowNum - 1}"/>
		</c:forEach>
		<tr>
			<td colspan="5" class="text-center">
				<button class="btn btn-primary" id="btn-write">글쓰기</button>
			</td>
		</tr>
	</tbody>
</table>
<ul class="pagination justify-content-center my-5">
  <li class="page-item ${pDto.prevPage <= 0 ? 'disabled':''}">
  	<a class="page-link" href="list.do?viewPage=${pDto.prevPage}&cntPerPage=${pDto.cntPerPage}">이전</a>
  </li>
  
  <c:forEach var="i" begin="${pDto.blockStart}" end="${pDto.blockEnd}">
  	<li class="page-item ${pDto.viewPage == i ? 'active':''}">
  		<a class="page-link" href="list.do?viewPage=${i}&cntPerPage=${pDto.cntPerPage}&searchType=${pDto.searchType}&keyWord=${pDto.keyWord}">${i}</a>
  	</li>
  </c:forEach>
  
  <li class="page-item ${pDto.blockEnd >= pDto.totalPage ? 'disabled':''}">
  	<a class="page-link" href="list.do?viewPage=${pDto.nextPage}&cntPerPage=${pDto.cntPerPage}">다음</a>
  </li>
</ul>

		
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn-write").click(() => {
			location.href="<c:url value='register.do'/>";
		})
	});
	
	$("#cntPerPage").change(function(e){
		let cntVal = $("#cntPerPage option:selected").val();
		//alert(cntVal);
//		location.href="<c:url value='list.do?viewPage=${pDto.viewPage}&cntPerPage='/>"+cntVal;		
		location.href="<c:url value='list.do?viewPage=1&searchType=${pDto.searchType}&keyWord=${pDto.keyWord}&cntPerPage='/>"+cntVal;		
	});
</script>
<%@ include file="../inc/footer.jsp" %>  

