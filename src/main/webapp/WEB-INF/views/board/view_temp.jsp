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
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>       
<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>         
</head>
<body>
<div class="container d-flex mt-5 justify-content-center">
	<div class="w-75 shadow p-5 rounded border">
		<h3>글상세보기</h3>
		<div class="form-group">
			<label for="subject">번호</label>
			<input type="text" class="form-control" id="bid" 
				name="bid"/>
		</div>

		<div class="form-group">
			<label for="subject">제목</label>
			<input type="text" class="form-control" id="subject" 
				name="subject"/>
		</div>

		<div class="form-group">
			<label for="contents">내용</label>
			<textarea class="form-control" id="contents" 
				name="contents" rows="4"></textarea>
		</div>
		<div class="form-group">
			<label for="writer">글쓴이</label>
			<input type="text" class="form-control" id="writer" 
				name="writer"/>
		</div>
		<div class="form-group mt-4">
			<button type="submit" id="btn-modify" class="btn btn-primary me-2">수정하기</button>
			<button type="button" id="btn-list" class="btn btn-primary">리스트</button>
		</div>
	</div>
</div>

</body>
</html>