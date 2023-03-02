<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../inc/header.jsp" %>  
<div class="container w-50 shadow rounded border p-5 mt-5">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
      <a class="nav-link <c:if test="${requestScope.find=='id'}">active</c:if>" data-bs-toggle="tab" href="#menu1">아이디 찾기</a>
    </li>
    <li class="nav-item">
      <a class="nav-link <c:if test="${requestScope.find=='pw'}">active</c:if>" data-bs-toggle="tab" href="#menu2">비밀번호 찾기</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div id="menu1" class="container tab-pane fade <c:if test="${requestScope.find=='id'}">active show</c:if>"><br>
      <div id="findIdSuccess" style="display:none" class="px-4">
      	<p class="text-center mt-4">가입시 아이디는 <b><span id="resultId"></span></b>입니다.</p>
      	<a class="btn btn-primary mt-3 w-100" type="button" href="${ctxPath}/memberLogin.do">로그인</a>
      </div>
      
      <div id="findIdBefore" class="px-4">    
      	<p>이름과 휴대폰 번호를 입력하세요.</p>
      	<input class="form-control mb-2" type="text" id="name" name="name" placeholder="이름"/>
		<input class="form-control mb-2" type="text" id="phone" name="phone" placeholder="전화번호"/>
		<input class="btn btn-primary mt-3 w-100" type="button" value="아이디 찾기" onclick="idFind()"/>
	  </div>	
    </div>
    
    <div id="menu2" class="container tab-pane fade <c:if test="${requestScope.find=='pw'}">active show</c:if>"><br>
      <div id="findPwSuccess" style="display:none" class="px-4" >
      	<p class="text-center">
	      	<small>해당 이메일로 임시 비밀번호를 보냈습니다.<br/>
	      		로그인후 마이페이지에서 비밀번호를 변경하시기 바랍니다.
	      	</small>
      	</p>
      	<a class="btn btn-primary mt-3 w-100" type="button" href="${ctxPath}/memberLogin.do">로그인</a>
      </div>
      <div id="findPwBefore" class="px-4">      
      <p>가입시 아이디와 이메일을 입력하세요.</p>
      	<input class="form-control mb-2" type="text" id="id" name="id" placeholder="아이디"/>
		<input class="form-control mb-2" type="text" id="email" name="email" placeholder="이메일"/>
		<input class="btn btn-primary mt-3 w-100" type="button" value="비밀번호 찾기" onclick="pwFind()"/>
      </div>
    </div>    
  </div>
</div>

<script>
	function idFind(){
		var name = $('#name').val().trim();
		var phone = $('#phone').val().trim();
		
		$.ajax({
			url:"${ctxPath}/findId.do?name="+name+"&phone="+phone,
			type:"post",
			success: function(data){ // data는 찾기 실패시 0, 아이디를 찾으면 해당 아이디값
				console.log(data);
				if(data == 0){
					alert("이름 및 휴대폰 번호를 다시 확인하기 바랍니다.")	;
				}else{ // id찾기 성공					
					$("#findIdBefore").css("display", "none");
					$("#findIdSuccess").css("display", "block");
					$("#resultId").text(data);
				}
			},
			error:function(){alert("아이디 찾기 요청 실패!!");}
		});
	}
	
	function pwFind(){
		var uid = $("#id").val();
		var uEmail = $("#email").val();
		
		$.ajax({
			url:"${ctxPath}/findPw.do?uid="+uid+"&uEmail="+uEmail,
			type:"post",
			success:function(data){
				if(data == 0){
					alert("아이디 및 비밀번호를 다시 확인하세요!!");
				}else{
					$("#findPwBefore").css("display", "none");
					$("#findPwSuccess").css("display", "block");					
				}
			},
			error:function(){alert("비밀번호 찾기 요청 실패!!")}
		});
	}
</script>








<%@ include file="../inc/footer.jsp" %>