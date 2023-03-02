var replyFunc = (function(){
	// 댓글 등록
	function register(reply, cb){
		$.ajax({
			url:'/bbs/replies/new',
			type:'post',
			// 서버에 전송하는 데이터 형식
			data: JSON.stringify(reply), // JSON객체를 문자열(텍스트)로 변환
			contentType: "application/json; charset=utf8",
			success:function(result){
				if(cb){
					cb(result);
				}
			},
			error:function(){alert("요청실패!!")}
		});
	}
	
	// 댓글 삭제
	function remove(rno, cb){
		$.ajax({
			type:'delete',
			url:'/bbs/replies/'+rno,
			success:function(result){
				if(cb){
					cb(result);
				}
			},
			error:function(){alert("요청실패!!")}
		});
	}
	
	// 댓글 조회
	// $.get() : get방식조회
	// $.post() : post방식조회
	function get(rno, cb){
		$.get('/bbs/replies/'+rno+'.json', function(result){
			if(cb) cb(result);
		}).fail(function(){
			alert('요청실패!!');
		});
	}
	
	// 댓글 수정
	function update(reply, cb){
		$.ajax({
			url:'/bbs/replies/'+reply.rno,
			type:'put',
			data:JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success:function(result){
				if(cb){
					cb(result);
				}
			},
			error:function(){alert("요청실패!!");}
		});
	}
	

/*
	// 특정 게시글에 대한 댓글 리스트
	function getList(param, cb){
		var bid= param.bid;
		
		// data: 전송타입 설정 속성, dataType: 응답타입 설정 속성 
		// dataType을 JSON형태로 설정 
		$.getJSON("/bbs/replies/list/"+bid+".json",
			function(result){
				if(cb) cb(result);
			}).fail(function(){alert("요청실패!!!"); }); 
	}
*/
	function getList(param, cb){
		var bid= param.bid;
		var viewPage = param.viewPage;
		
		// data: 전송타입 설정 속성, dataType: 응답타입 설정 속성 
		// dataType을 JSON형태로 설정 
		$.getJSON("/bbs/replies/list/"+bid+"/"+viewPage+".json",
			function(result){
				if(cb) cb(result);
			}).fail(function(){alert("요청실패!!!"); }); 
	}
		
	// 댓글 날짜/시간 표시하기
	function showDateTime(timeValue){
		// 현재 시간 및 날짜 구하기
		var now = new Date();
		
		// 현재시간관 댓글 등록시간의 갭(차이)을 구하기
		// unix 타임으로 계산
		var gap= now.getTime() - timeValue;
		
		var rDate = new Date(timeValue); // 댓글등록시간으로 Date객체 생성
				
		// 갭이 24시간 이상이면 날짜형식으로, 24시간 미만이면 시간으로 출력
		if(gap < (1000*60*60*24)){
			var hh = rDate.getHours();// 댓글 등록 시간 구하기
			var mi = rDate.getMinutes(); // 분
			var ss = rDate.getSeconds(); // 초
			
			// 시,분,초가 01-09-05같이 두자리씩 표현
			// split과 join은 반대, 
			// split(구분자)은 문자열을 구분자로 구분해서 배열로 리턴
			// join(구분자)은 배열을 구분자로 합쳐서 문자열로 리턴 
			return [(hh>9 ? '':'0')+hh, ':', (mi>9 ? '':'0')+mi,
						':', (ss>9 ? '':'0')+ss].join('');
		}else{ // 갭이 24시간 이상인 경우
			var yy = rDate.getFullYear();
			var mm = rDate.getMonth() + 1; // getMonth()는 0부터 시작 ->1월은 0+1로 표현해야함
			var dd = rDate.getDate();
			
			// 위에서 구한 날짜를 문자열로 리턴 
			return[yy, '/', (mm > 9 ? '': '0')+mm,'/',(dd > 9 ? '': '0')+dd].join('');
		}
	}	
		
	return{
		register : register,
		remove : remove,
		get : get,
		update : update,
		getList : getList,
		showDateTime : showDateTime
	}
})();