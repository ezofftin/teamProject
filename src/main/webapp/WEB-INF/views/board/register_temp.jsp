<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css' rel='stylesheet'>
<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js'></script>
</head>
<body>

<div class="container w-50 mt-5 p-5 shadow">
	 
	<form action="<c:url value="register.do"/>" method="post"> 

		<h4>글쓰기</h4>				
			
		<input class="form-control" type="text" id="subject" name="subject" placeholder="글제목을 입력하세요" autofocus>		
		
		<textarea class="form-control mt-2" name="contents" id="contents" placeholder="내용을 입력하세요"></textarea>
		
		<input class="form-control mt-2" type="text" id="writer" name="writer" placeholder="이름을 입력하세요">
		
		<div class="text-center mt-3">
			<button class="btn btn-primary">등록</button>	
		</div>
	</form>
</div>
</body>
</html>