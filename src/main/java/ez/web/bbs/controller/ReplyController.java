package ez.web.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ez.web.bbs.model.ReplyDTO;
import ez.web.bbs.model.ReplyPageDTO;
import ez.web.bbs.service.ReplyService;

@RestController // @Controller + @ResonseBody
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// 댓글조회
	@GetMapping("/{rno}")
	public ReplyDTO get(@PathVariable("rno") int rno) {
		System.out.println("rno : " + rno);
		
		return service.read(rno);
	}
	
	// 댓글추가
	@PostMapping("/new")
	public String create(@RequestBody ReplyDTO rDto) {
		int resultCnt = service.register(rDto);
		
		return resultCnt == 1 ? "success" : "fail"; 
	}

	// 댓글삭제
	@DeleteMapping("/{rno}")
	public String remove(@PathVariable("rno") int rno) {		
		int resultCnt = service.remove(rno);
		
		return resultCnt == 1 ? "success" : "fail"; 
	}
	
	// 댓글수정
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public String modify(@PathVariable("rno") int rno, @RequestBody ReplyDTO rDto) {		
		rDto.setRno(rno);
		
		int resultCnt = service.modify(rDto);	
		return resultCnt == 1 ? "success" : "fail"; 
	}
	
	// 특정 게시글에 대한 댓글 리스트 조회
//	@GetMapping("list/{bid}")
//	public List<ReplyDTO> getList(@PathVariable("bid") Integer bid){
//		System.out.println("댓글리스트 조회!!!");
//		List<ReplyDTO> list=service.getList(bid);
//		System.out.println("댓글 list : " + list);
//		
//		return list;
//	}
	
	// 페이징 처리가 된 댓글 리스트 조회
	@GetMapping("list/{bid}/{viewPage}")
	public ReplyPageDTO getList(@PathVariable("bid") Integer bid, 
			@PathVariable("viewPage") Integer viewPage){
		
		ReplyPageDTO rPageDto=service.getList(bid, viewPage);
		System.out.println("rPageDto : " + rPageDto);
		
		return rPageDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
