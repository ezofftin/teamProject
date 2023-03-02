<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ include file="inc/header.jsp" %>                       
		<div class='container mt-5'>
			<h3> 회원 리스트</h3>   
			<table class='table table-striped'>                 
				<thead class='table-dark'>                      
					<tr>               
						<th>번호</th>  
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>  
						<th>나이</th>  
						<th>이메일</th>
						<th>전화번호</th>
						<th>삭제</th>  
					</tr>              
				</thead>               
				<tbody>
			<c:forEach var="dto" items="${list}">             
				<tr>                    
					<td>${dto.no}</td>
					<td><a href='${ctxPath}/memberInfo.do?no=${dto.no}'>${dto.id}</a></td>      
					<td>${dto.pw}</td>           
					<td>${dto.name}</td>         
					<td>${dto.age}</td>          
					<td>${dto.email}</td>        
					<td>${dto.phone}</td>        
					<td><a href='${ctxPath}/memberDel.do?no=${dto.no}' class='btn btn-danger btn-sm'>삭제</a></td>
				</tr>
			</c:forEach>
			<!-- Ajax 리스트 -->
			<tr>
				<td colspan="8" class="">
					<input type="button" value="Ajax리스트" class="btn btn-secondary" onclick="showList()"/>
				</td>			
			</tr>
			<tr>
				<td colspan="8" class="" id="ajax-list"></td>
			</tr>
								              
			</tbody>              
		</table>                  
	 </div>                       
	 <div class='text-center'><a href='${ctxPath}/memberRegister.do' class='btn btn-primary'>회원가입</a>
		<a href='${ctxPath}' class='btn btn-info'>홈으로</a>
	 </div>
	 
	 <script>
	 	function showList(){
	 		$.ajax({
	 			url:"<c:url value='/memberAjaxList.do'/>",
	 			type:"get", // 서버에 전송하기 위한 전송방식
	 			dataType:"json", // 응답형식
	 			success: resultList, // resultList 콜백함수 호출
	 			error: function(){alert("error")}
	 		});
	 	}
	 	
	 	function resultList(data){
	 		console.log(data);
	 		
	 	var html ="<table class='table'>";                 
			html+="	<thead class='table-dark'> ";                     
			html+="		<tr>                   ";
			html+="			<th>번호</th>      ";
			html+="			<th>아이디</th>    ";
			html+="			<th>비밀번호</th>  ";
			html+="			<th>이름</th>      ";
			html+="			<th>나이</th>      ";
			html+="			<th>이메일</th>    ";
			html+="			<th>전화번호</th>  ";
			html+="		</tr>                  ";
			html+="	</thead>                   ";
			html+="	<tbody>                    ";

		$.each(data, function(index, obj){
			html+="<tr>                   ";
			html+="<td>"+obj.no+"</td>                   ";
			html+="<td>"+obj.id+"</td>                   ";
			html+="<td>"+obj.pw+"</td>                   ";
			html+="<td>"+obj.name+"</td>                   ";
			html+="<td>"+obj.age+"</td>                   ";
			html+="<td>"+obj.email+"</td>                   ";
			html+="<td>"+obj.phone+"</td>                   ";
			html+="</tr>                  ";
		});
			html+="	</tbody>                    ";
			html+="	</table>                    ";
			
			$("#ajax-list").html(html);	
	 	}
	 	
	 </script>
	 
	 
	 
<%-- <%@ include file="inc/footer.jsp" %>  --%>
<jsp:include page="inc/footer.jsp" />                           