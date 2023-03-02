<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="inc/header.jsp" %>
<div class="container d-flex mt-5">
<%-- 	<%@ include file="myProfile_left.jsp" %> --%>  
	<jsp:include page="myProfile_left.jsp" />  
	
	<div class="container w-50 shadow rounded border p-5 mt-3">
	
		<h3 class="text-center mb-4">비밀번호 변경</h3>
		<input type="text" id="memberId" value="${sessionScope.loginDto.id}"/>
		<p id="pwChkMsg" class="my-3" style="height:30px"></p>
		<input class="form-control mb-2" type="text" id="pw" name="pw" placeholder="현재 비밀번호"/>
		<input class="form-control mb-2" type="password" id="newPw" name="newPw" placeholder="새 비밀번호"/>
		<input class="form-control mb-2" type="password" id="newPwConfirm" name="newPwConfirm" placeholder="새 비밀번호 확인"/>
		
		<div class="text-center pt-4">
			<button class="btn btn-sm btn-success" id="pwChangeBtn">비밀번호 변경</button>
		</div>	
		
		<div class="mt-3">
		<span style="font-size:13px">비밀번호가 기억나지 않나요? </span><a href="idPwFind.do?find=pw">비밀번호 찾기</a>
		</div>
	</div>
</div>

<script>
	var pw = "";
	var currentPwChk = "";
	var newPwChk = "";
	var newPwConfirmChk = "";
	
	// 현재 비밀번호가 데이터베이스에 있는 비밀번호인지 체크하기
	function pwCheck(){
		pw = $('#pw').val();
		console.log("pw : " + pw);
		
		$.ajax({
			url:"${ctxPath}/pwCheck.do",
			type:"post",
			data:{"pw":pw},
			async:false, // 동기화 처리
			success:function(result){
				console.log("result : " + result);
				if(result=="ok"){
					//alert("현재 비밀번호 확인완료!!");	
					currentPwChk = true;					
				}else{
					//alert("현재 비밀번호 다시 확인요망!!");
					currentPwChk = false;
				}
				console.log("pwCheck함수 currentPwChk : " + currentPwChk);
			},
			error:function(){alert("현재 비밀번호 체크 요청 실패!!");}				
		});
	}
	
	// 새비밀번호 유효성 검사
	$("#newPw").on("keyup", function(){
		var npValue = $("#newPw").val();
		if(npValue ==""){
			$("#pwChkMsg").text("새 비밀번호를 입력하세요.");
			newPwChk = false;
		}else if(npValue.length < 4){
			$("#pwChkMsg").text("4자리 이상 입력하세요.");
			newPwChk = false;
		}else{
			$("#pwChkMsg").text("");
			newPwChk = true;
		}
	});
	
	// 새 비밀번호 확인 체크
	$("#newPwConfirm").on("keyup", function(){
		var npcValue = $("#newPwConfirm").val();
		if(npcValue ==""){
			$("#pwChkMsg").text("새 확인 비밀번호를 입력하세요.");
			newPwConfirmChk = false;
		}else if($("#newPw").val() != npcValue){
			$("#pwChkMsg").text("새 비밀번호가 일치하지 않습니다.");
			newPwConfirmChk = false;
		}else{
			$("#pwChkMsg").text("");
			newPwConfirmChk = true;
		}
	});
	
	$("#pwChangeBtn").on("click", function(){
		pwCheck();		
		
		console.log("currentPwChk : " + currentPwChk);
		if(currentPwChk == false){
			alert("현재 비밀번호를 다시 확인하세요!!");
		}else if(newPwChk == false){
			alert("새 비밀번호를 다시 확인하세요!!");
		}else if(newPwConfirmChk == false){
			alert("새 비밀번호가 일치하지 않습니다... 다시 확인하세요.");
		}else if(currentPwChk && newPwChk && newPwConfirmChk){
			var id = $("#memberId").val();
			var pw = $("#newPw").val();
			var member = {"id":id, "pw":pw};
			
			$.ajax({
				url:"${ctxPath}/pwChange.do",
				type:"post",
				data:JSON.stringify(member),
				contentType:"application/json; charset=utf8", 
				success:function(result){
					if(result > 0){
						alert("비밀번호 변경처리되었습니다!! 새로운 비밀번호로 로그인 하세요!!");
						location.href="${ctxPath}/memberLogout.do?mode=change";
					}
				},
				error:function(){alert("비밀번호 변경 요청 실패!!");}
			});
		} // if문
		
		
		
	});
	
</script>
<%-- <%@ include file="inc/footer.jsp" %> --%>
<jsp:include page="inc/footer.jsp" />














