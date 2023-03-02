<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../inc/header.jsp" %>  
<div class="container w-50 shadow rounded border p-5 mt-5">
	<form action="${ctxPath}/memberLogin.do" method="post">
		<input type="text" name="moveUrl" value="${moveUrl}" size="50"/>
		<h3 class="text-center mb-4">로그인</h3>	
			<c:if test = "${result == 0}">
				<p class="text-center text-danger my-3">사용자 아이디 또는 비밀번호를 다시 확인하세요!!</p>
			</c:if>
			<c:if test = "${sessionScope.loginMsg != null}">
				<p class="text-center text-danger my-3">${sessionScope.loginMsg}</p>
			</c:if>
			<c:remove var="loginMsg" scope="session"/> <!-- 특정 세션값(loginMsg) 삭제하기 -->
			<input class="form-control mb-3" type="text" id="id" name="id" placeholder="아이디"/>
			<input class="form-control mb-2" type="text" id="pw" name="pw" placeholder="비밀번호"/>
			
			<div class="text-center pt-4">
				<input type="submit" class="btn btn-primary w-100" value="로그인"/>
			</div>	
	</form>	
	<div class="mt-3 w-100 findIdPw">
		<div class="d-flex justify-content-between">
			<div><i class="fa fa-lock"></i> <a href="idPwFind.do?find=id">아이디</a><a href="idPwFind.do?find=pw">비밀번호 찾기</a></div>
			<div><i class="fa fa-user-plus" aria-hidden="true"></i> <a href="${ctxPath}/memberRegister.do">회원가입</a></div>	
		</div>
	</div>
</div>
<%@ include file="../inc/footer.jsp" %>