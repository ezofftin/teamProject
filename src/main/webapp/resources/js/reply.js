/**
 * 
 * author : 김재현
 * 22.07.14 11:05
 */
 
 // ()안에 함수를 선언하고, 바깥쪽에서 실행하는 함수 --> 즉시 실행 함수
 // 즉시 실행함수의 결과가 바깥쪽에 선언된 변수에 대입된다.

/* 
 var replyFunc = (
	 function(){
	 	return {name : "Test"};
 	 }
 )();

 var replyFunc = (function(){
 		function register(reply, callback){
 			console.log("reply......");
 		}
 
	 	return {register : register};
 	 }
 )();
*/

var replyFunc = (function(){
	// 댓글 등록
	function register(reply, callback){
		console.log("register reply....");
		
		$.ajax({
			type: 'post',
			url : '/bbs/replies/new',
			data: JSON.stringify(reply), // JSON 객체 --> 문자열(텍스트)
			contentType : "application/json; charset=utf8",
			success: function(result){
				if(callback){
					callback(result);
				}
			},
			error: function(){
				alert("요청 실패!!");
			}
		});
	}
	
	// 특정 게시글의 댓글 리스트
	function getList(param, callback){
		var bid = param.bid;
		
		// getJSON() : 비동기식으로 서버와 접속해서 데이터를 불러올 때
		// 사용한다. JSON형식의 데이터를 불러옴.
		
		// ajax()함수의 dataType: 'json' 설정 사용한 것과 같다.
		
		$.getJSON("/bbs/replies/pages/"+bid+".json",
			function(data){
				if(callback) {
					callback(data); // 댓글 리스트 가져오기
				}
			}).fail(function(){
				alert("요청 실패!!");
		});
	}
	
	// 삭제
	function remove(rno, callback){
		$.ajax({
			type: 'delete',
			url : '/bbs/replies/'+rno,
			success: function(deleteResult){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(){
				alert("요청 실패!!");
			}
		});
	}	
	
	// 특정 댓글 조회
	function get(rno, callback){
		$.get("/bbs/replies/"+rno+".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(){
			alert("요청 실패!!");
		});
	}
	
	
	// 댓글 수정
	function update(reply, callback){
		console.log("rno : " + reply.rno);
		
		$.ajax({
			type: 'put',
			url: '/bbs/replies/'+reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error: function(){
				alert("요청 실패!!");
			}
		});
	}
	
	// 시간/날짜 표시하기
	function showDateTime(timeValue) {
		
		// 현재 시간 구하기		
		var today = new Date();
		
		// 현재 시간과 댓글 등록시간 갭(gap) 구하기 
		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		// 갭이 24시간 이상이면 날짜를 출력, 24시간 미만이면 등록 시간을 출력
		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			// 한자리이면 0을 포함해서 출력
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}
	
	return {
		register: register,
		getList : getList,
		remove : remove,
		get : get,
		update : update,
		showDateTime : showDateTime
	};
	
})();




 