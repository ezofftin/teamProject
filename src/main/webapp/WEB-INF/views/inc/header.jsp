<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">    
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css' rel='stylesheet'>
<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js'></script>

<link rel="stylesheet" href="${ctxPath}/css/main.css">

</head>
<body>
	<div class="container">
		<br/>
		<h3><a href="">Web Study</a></h3>
	</div>
	<nav class="navbar navbar-expand-sm sticky-top">
	<div class="container">
	  <div class="collapse navbar-collapse" id="mynavbar">
	      <ul class="navbar-nav w-100">
	        <li class="nav-item">
	          <a class="nav-link" href="${ctxPath}">HOME</a>
	        </li>
	        
	        <li class="nav-item">
	          <a class="nav-link" href="memberList.do">회원 리스트</a>
	        </li>
	        
	        <li class="nav-item">
	          <a class="nav-link" href="list.do">게시판 리스트</a>
	        </li>
	        
	        <li class="nav-item ms-auto d-flex p-1">
	        <c:if test="${sessionScope.loginDto.id == null}">
	          <a class="btn btn-sm btn-outline-light" href="${ctxPath}/memberLogin.do"  
	           type="button">로그인</a>
	          <a class="btn btn-sm btn-outline-light ms-2" href="${ctxPath}/memberRegister.do"  
	           type="button">회원가입</a>
	         </c:if>
	          
	        <c:if test="${sessionScope.loginDto.id != null}">
	          <div class="me-3 welcomeMsg"></div>
	          <a class="me-3 myProfile" type="button" href="${ctxPath}/myProfile.do">
	          	<i class="fa fa-user-circle"></i> ${sessionScope.loginDto.name}</a>
	          <a href="javascript:logout()" class="btn btn-sm btn-outline-light" 
	          	type="button">로그아웃</a>
	         </c:if> 
	        </li>
	        
	      </ul>
	  </div>
	</div>  
	</nav>
<script>
	function logout(){
		location.href="<c:url value='/memberLogout.do'/>";
	}
</script>
