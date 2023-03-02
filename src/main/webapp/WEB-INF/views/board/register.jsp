<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<%@ include file="../inc/header.jsp" %>

<style>
	.ck-editor__editable { height: 200px;}
	.ck-content {font-size: 12px;}
</style>
<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
<div class="container d-flex mt-5 justify-content-center">
	<div class="w-75 shadow p-5 rounded border">
		<h3>글쓰기</h3>
		<form method="post" action="<c:url value='register.do'/>">
			<div class="form-group">
				<label for="subject">제목</label>
				<input type="text" class="form-control" id="subject" 
					name="subject" placeholder="글 제목을 입력하세요"/>
			</div>
			<div class="form-group">
				<label for="contents">내용</label>
				<textarea class="form-control" id="contents" 
					name="contents" rows="3"></textarea>
				<script type="text/javascript">
					ClassicEditor.defaultConfig={
						toolbar:{
							items:[
								'heading',
								'|',
								'bold',
								'|',
								'link',
								'|',
								'bulletedList',
								'numberedList',
								'|',
								'indent',
								'|',
								'outdent',
								'|',
								/* 'imageUpload',
								'|', 
								'insertTable',
								'|',*/
								'undo',
								'redo'
							]
						},
						language: "ko"
					};
					ClassicEditor.editorConfig = function(config){
						config.enterMode = ClassicEditor.ENTER_BR
					};
					ClassicEditor.create(document.querySelector('#contents'))
						.catch(error => {
							console.error(error);
						});
				</script>
			</div>
			<div class="form-group">
				<label for="writer">글쓴이</label>
				<input type="text" class="form-control" id="writer" 
					name="writer" placeholder="이름을 입력하세요"/>
			</div>
			<div class="form-group mt-4">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" id="btn-list" class="btn btn-secondary">리스트</button>
			</div>
		</form>

	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btn-list").click(() => {
			location.href="<c:url value='list.do?viewPage=${viewPage}'/>";
		})
	});
</script>

<%@ include file="../inc/footer.jsp" %>