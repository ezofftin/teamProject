<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<%-- <link rel="stylesheet" href="${ctx}/resources/css/main.css"> --%>
<link rel="stylesheet" href="css/main.css">

<script type="text/javascript">
	$(document).ready(function(){
		<c:if test="${msg != null}">
			alert("${msg}");
		</c:if>
		<c:remove var="msg" scope="session"/>
	});

	function chkValidation(){	
		if($("#user_id").val()==''){
			//alert("아이디를 입력하세요!!!");
			//alert($("#idMsg"));
			$("#idMsg").css("display", "block");
			
			$("#user_id").focus();
			return false;
		}
		
		if($("#pw").val()==''){
			$("#pwMsg").css("display", "block");
			//alert("비밀번호를 입력하세요!!!");
			$("#pw").focus();
			return false;
		}
		return true;
	}
	
	function valueChk(obj){
		if(obj.id == "user_id"){
			$("#idMsg").css("display", "none");
		}else{
			$("#pwMsg").css("display", "none");
		}
	}
	
	function logout(){
		// c:url를 이용하면 ${ctx}를 생략할 수 있다.
		location.href="<c:url value='/memberLogout.do'/>";
	}
</script>


</head>
<body>
<!-- <header> -->
	<div class="container">
		<br>
		<h3><a href="">JAVAcademy</a></h3>
	</div>
	<nav class="navbar navbar-expand-md sticky-top">
	  <div class="container">	    
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="collapsibleNavbar">
	      
	      <ul class="navbar-nav w-100">
	        <li class="nav-item">
	          <a class="nav-link" href="${ctx}/home.do">HOME</a>
	        </li>
	        
	        <li class="nav-item me-auto">
	          <a class="nav-link" href="${ctx}/list.do">게시판</a>
	        </li>
	        
	        <li class="nav-item p-1 d-flex">
	          <c:if test="${sessionScope.userId == null || sessionScope.userId==''}">	
	          	<a type="button" class="btn btn-sm btn-outline-light" 
	          		data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
	          </c:if>
	          <c:if test="${sessionScope.userId != null && sessionScope.userId !=''}">
	          	<div class="me-3 welcomeMsg">${sessionScope.userName}님 환영합니다!!</div>	
	          	<a href="javascript:logout()" type="button" 
	          		class="btn btn-sm btn-outline-light">로그아웃</a>
	          </c:if>
	        </li>
	      </ul>      
	    </div>
	  </div>
	</nav>

<!-- The Modal -->
<div class="modal fade" id="loginModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header border-0">
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <form action="${ctx}/memberLogin.do" method="post">
	      <div class="modal-body p-4">
	        <input class="form-control" type="text" placeholder="아이디"
	        	id ="user_id" name="user_id" onkeyup="valueChk(this)">
			<p id="idMsg">아이디를 입력하세요.</p>	        	
	        <input class="form-control mt-4" type="password" 
	        	placeholder="비밀번호" id ="pw" name="pw" onkeyup="valueChk(this)">
			<p id="pwMsg">비밀번호를 입력하세요.</p>	        	
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer border-0">
<!-- 	        <button type="button" class="btn btn-primary" 
	        	data-bs-dismiss="modal" onclick="chkValidation()">로그인</button> -->
	        	
	        <button type="submit" class="btn btn-primary" 
	        	onclick="return chkValidation()">로그인</button>
	      </div>
	  </form>	
    </div>
  </div>
</div>
	
<div class="container">
  
</div>	
<!-- </header>	 -->
	
