package ez.web.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ez.web.bbs.model.BoardDTO;
import ez.web.bbs.model.PageDTO;
import ez.web.bbs.service.BoardService;
import ez.web.bbs.service.BoardServiceImpl;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list.do")
	public String list(PageDTO pDto, Model model) {
		List<BoardDTO> list = service.getList(pDto);
		System.out.println(list);
	//	System.out.println("cntPerPage : " + pDto.getCntPerPage());
		
		model.addAttribute("list", list);
		model.addAttribute("pDto", pDto);
		
//		return "board/boardList";
		return "board/boardList";
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String registerForm() {
		return "board/register";
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(BoardDTO dto) {
//		for(int i=1; i<=113; i++) {
//			BoardDTO bdto = new BoardDTO();
//			bdto.setSubject(i + "번째 제목입니다.....");
//			bdto.setContents(i + "번째 내용입니다.....");
//			bdto.setWriter("아무개" +(i%10));
//			
//			service.register(bdto);
//		}
		
		service.register(dto);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping("/boardView.do")
	public String view(int bid, @ModelAttribute("pDto") PageDTO pDto,  Model model) {
		BoardDTO dto= service.view(bid, "view");
		System.out.println(dto);
		model.addAttribute("dto", dto);		
		 	
		return "board/view";
	}
	// 수정 폼페이지
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyForm(int bid, Model model) {
		BoardDTO dto = service.view(bid, "modify");
		model.addAttribute("dto", dto);
				
		return "board/modify";
	}
	
	// 게시글 수정
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(BoardDTO dto) {
		service.modify(dto);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/remove.do")
	public String remove(int bid) {
		service.remove(bid);
		
		return "redirect:list.do";
	}
}
