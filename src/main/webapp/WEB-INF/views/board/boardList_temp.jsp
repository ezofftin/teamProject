<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>                                         
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>       
<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>         
</head>
<body>
<div class="container">
<h3>스프링 게시판</h3>
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
	<tbody>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>글번호</td>
			<td><a href=""/>글제목</a></td>
			<td>0</td>
			<td>아무개</td>
			<td>22-11-11</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" class="text-center">
				<button class="btn btn-primary" id="btn-write">글쓰기</button>
			</td>
		</tr>
	</tbody>
</table>
<ul class="pagination justify-content-center my-5">
  <li class="page-item">
  	<a class="page-link" href="">이전</a>
  </li>
  
  	<li class="page-item">
  		<a class="page-link" href="">1</a>
  	</li>
  	<li class="page-item">
  		<a class="page-link" href="">2</a>
  	</li>

  <li class="page-item>
  	<a class="page-link" href="">다음</a>
  </li>
</ul>

		
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn-write").click(() => {
			location.href="<c:url value='register.do'/>";
		})
	});
</script>

</body>
</html>