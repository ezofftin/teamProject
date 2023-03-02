package kr.mr.bbs_reply03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.mr.model.BoardPaging;
import kr.mr.model.BoardVO;
import kr.mr.service.BoardService;

@Controller
public class BoardController {
	// list.do (get) --> list()
	// register.do (post) --> register()
	// view.do (get) --> view()
	// remove.do(get) --> remove()
	// modify.do(post) --> modify()
	
	@Autowired // 의존성 주입(DI)
	private BoardService service;
	
//	@Autowired // 필드 주입
//	private BoardPaging bp;
	
	@RequestMapping("/list.do")
//	public String list(BoardVO vo, Model model) {
	public String list(
			@RequestParam(defaultValue = "1") int viewPage,
			@RequestParam(defaultValue = "5") int cntPerPage,
			@ModelAttribute("bvo") BoardVO vo, Model model) {
		
		// 전체 게시물의 개수
		int totalCnt = service.totalCnt(vo);
		System.out.println("전체게시글 수 : " + totalCnt);
		
		BoardPaging bp = new BoardPaging(totalCnt, viewPage, cntPerPage);
		
		// 주입 받았을 경우
//		bp.setViewPage(viewPage);
//		bp.setValue(totalCnt, cntPerPage);
		
//		System.out.println("bp.getStartIndex() :" + bp.getStartIndex());
		
		vo.setStartIndex(bp.getStartIndex());
//		vo.setCntPerPage(bp.getCntPerPage()); // Error
		vo.setCntPerPage(cntPerPage);
		
//		System.out.println("bp.getCntPerpage() : "+ bp.getCntPerPage());
		
		model.addAttribute("bp", bp);
		model.addAttribute("vo", vo);
		
		List<BoardVO> list = service.getList(vo);
		
		model.addAttribute("list", list);
		return "board/boardList"; //boardList.jsp
		
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	// @GetMapping : 스프링 버전업되면서 지원
	// 요청메소드를 지정하지 않았을 경우에는 모든 메소드방식을 다 받을 수 있다.
	//@RequestMapping("/register.do")
	public String registerForm(
			@ModelAttribute("viewPage") int viewPage) {
		
		return "board/register"; // 글쓰기 화면
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	// @PostMapping : 스프링 버전업되면서 지원
	public String register(BoardVO board) {
		
//	public void register(BoardVO board) {
//		for(int i=1; i<=106; i++) {
//			BoardVO vo = new BoardVO();
//			vo.setSubject(i + "번째 제목입니다....");
//			vo.setContents(i + "번째 내용 입니다....");
//			vo.setWriter("아무개0"+(i%10));
//			
//			service.register(vo);
//		}
		
		
		service.register(board);
		return "redirect:/list.do"; // 글등록 후 리스트로
	}
	
	@RequestMapping("/view.do")
//	public String view(int bid, int viewPage, Model model) {
	public String view(@ModelAttribute("bvo") BoardVO bvo, int viewPage, Model model) {
//		BoardVO board = service.view(bid);
		BoardVO board = service.view(bvo.getBid(), "view");
		model.addAttribute("board", board);
		
		model.addAttribute("viewPage", viewPage);
//		model.addAttribute("bvo", bvo);
		
		return "board/view"; //view.jsp
	}
	
	
	// @ModelAttribute를 사용하면 model에 자동으로 추가되어 전송된다.
	// model.addAttribute()를 사용하지 않아도 된다.
	
	// @ModelAttribute를 이용해서 객체를 받을 때	
	// BoardPaging객체의 프로퍼티(멤버, 필드)를 찾는다.
	// 해당 프로퍼티의 setter메소드를 호출해서 파라미터값을 입력한다.
	
	// 예> @ModelAttribute("bp") BoardPaging bp
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyForm(@ModelAttribute("bvo") BoardVO bvo, @ModelAttribute("viewPage") int viewPage, Model model) {
		BoardVO board = service.view(bvo.getBid(), "modify");
		model.addAttribute("board", board);
		
		return "board/modify"; // modify.jsp
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(BoardVO board,
			@ModelAttribute("viewPage") int viewPage, Model model) {
		System.out.println(board);
		service.modify(board);
		
		model.addAttribute("keyWord", board.getKeyWord());
		model.addAttribute("searchType", board.getSearchType());
		
		// redirect:접두어가 붙으면 스프링에서 내부적으로
		// response.sendRedirect() 처리해 줌
		return "redirect:/list.do";
	}
	
//	@RequestMapping("/remove.do")
//	public String remove(int bid, BoardVO board,
//	@ModelAttribute("viewPage") int viewPage, Model model) {
//		
//		service.remove(bid);
//		
//		model.addAttribute("keyWord", board.getKeyWord());
//		model.addAttribute("searchType", board.getSearchType());
//		
//		return "redirect:/list.do";
//	}
	
	@RequestMapping("/remove.do")
	public String remove(@ModelAttribute BoardVO bvo,
			int viewPage, RedirectAttributes rttr) {
		
		service.remove(bvo.getBid());
		// RedirectAttributes는 Model을 상속받은 객체 : redirect시 사용
		// addAttribute는 단일 타입을 바인딩,
		// addFlashAttribute는 객체를 바인딩
		// 객체를 받을 때는 @ModelAttribute()로 받을 수 있다.
		// 예> rttr.addFlashAttribute("bvo", bvo) ---> 
		// @ModelAttribute("bvo")로 받는다.
		
//		rttr.addAttribute("keyWord", bvo.getKeyWord());
//		rttr.addAttribute("searchType", bvo.getSearchType());
				
		rttr.addFlashAttribute("bvo", bvo);
		
		return "redirect:/list.do";
	}
	
	
}
