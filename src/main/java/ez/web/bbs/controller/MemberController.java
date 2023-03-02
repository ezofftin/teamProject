package ez.web.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ez.web.bbs.mapper.MemberMapper;
import ez.web.bbs.model.MemberDAO_old;
import ez.web.bbs.model.MemberDTO;
import ez.web.bbs.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired // DI
	//private MemberDAO_old dao;
//	private MemberMapper memberMapper;
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) {
		//List<MemberDTO> memberList= dao.memberList();
		List<MemberDTO> memberList= service.memberList();
		model.addAttribute("list", memberList);
		
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberDTO dto) {
		//int cnt = dao.memberInsert(dto);
		int cnt = service.memberRegister(dto);
		
		return "redirect:memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		return "memberRegister";
	}
	
	@RequestMapping("/memberInfo.do")
	public String memberInfo(int no, Model model) {
		//MemberDTO dto = dao.memberInfo(no);
		MemberDTO dto = service.memberInfo(no);
		
		System.out.println(dto);
		
		model.addAttribute("dto", dto);
		
		return "memberInfo";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberDTO dto) {
		//int cnt = dao.memberUpdate(dto);
		int cnt = service.memberModify(dto);
		
		return "redirect:memberList.do";
	}
	
	@RequestMapping("/memberDel.do")
	public String memberDel(int no) {
		//dao.memberDelete(no);
		service.memberRemove(no);
		
		return "redirect:memberList.do";
	}
	/* Message Converter API : jackson databind (MVN repository에서 라이브러리 추가)
	 * ==> JSON형식을 자바객체로 변환, 또는 자바객체를 JSON형식으로 변환
	 * 
	 * 비동기 전송데이터들은 Http Msg의 body안에 담긴다.
	 * 
	 * @RequestBody : 클라이언트에서 서버로 데이터를 보낼때, HTTP 요청 body 담긴 값들을 자바객체로 변환시켜서 객체에 저장
	 * @ResponseBody: 서버에서 클라이언트로 응답할 때, 자바객체를 HTTP 응답 body에 변환해서 클라이언트로 전송
	 * 
	 * 주로 JSON, XML등을 body에 담는다.
	 */
	
	@RequestMapping("/memberAjaxList.do")
	public @ResponseBody List<MemberDTO> memberAjaxList(){
		//List<MemberDTO> memberList= dao.memberList();
		List<MemberDTO> memberList= service.memberList();
		return memberList;
	}
	
	@RequestMapping("memberIdCheck.do")
	@ResponseBody 				// @RequestParam은 post방식의 파라미터도 받을 수 있음 
	public String memberIdCheck(@RequestParam("uid") String uid) {
		MemberDTO dto=service.idCheck(uid);
		
		if(dto !=null || "".equals(uid.trim())) {
			return "no"; // 사용불가
		}		
		
		return "yes"; // 사용가능
	}
	
	@RequestMapping(value="memberLogin.do", method=RequestMethod.GET)
	public String loginForm(@RequestParam(defaultValue="") String moveUrl, Model model) {
		System.out.println("moveUrl : " + moveUrl);
		model.addAttribute("moveUrl", moveUrl);		
		return "login/loginForm";
	}
	
	@RequestMapping(value="memberLogin.do", method=RequestMethod.POST)
	// RedirectAttributes extends Model : redirect시에 메세지전달
	public String memberLogin(@RequestParam(defaultValue="") String moveUrl, MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) {
		
		System.out.println("id : " + dto.getId());		
		
		boolean result = service.memberLogin(dto, req);
		
		if(!result) { // 로그인 실패
			rttr.addFlashAttribute("result", 0); // 전달후에 값이 사라짐
			return "redirect:memberLogin.do";//redirect는 get방식
		}
		System.out.println("post:~~" + moveUrl);
		if(!moveUrl.equals("")) {
			return "redirect:"+moveUrl;
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/memberLogout.do")
	public String memberLogout(@RequestParam(defaultValue = "") String mode, HttpSession session) {
		
		session.invalidate();// session 초기화
		if(mode.equals("change")) {
			return "redirect:memberLogin.do";
		}
		return "redirect:/";
	}
	
	@GetMapping("/idPwFind.do")
	public String idPwFind(String find, Model model) {
		System.out.println("find : " + find);
		
		model.addAttribute("find", find);
		
		return "login/idPwFind";
	}
	
	@PostMapping("/findId.do")
	@ResponseBody
	public String findId(MemberDTO dto) {
		System.out.println("name : " + dto.getName());
		System.out.println("phone : " + dto.getPhone());
		
		String result = service.getFindId(dto);
		System.out.println("result : " + result);
		
		return result;
	}
	
	@PostMapping("/findPw.do")
	@ResponseBody
	public int findPw(String uid, String uEmail) {
		
		int n = service.getFindPw(uid, uEmail);
		return n;
	}
	
	@GetMapping("/myProfile.do")
	public String myProfile() {
		
		return "myProfile";
	}
	
	@PostMapping("pwCheck.do")
	@ResponseBody
	public String pwCheck(String pw, HttpSession session) {
		System.out.println("현재 pw : " + pw);
		String chkResult = "";
		
		MemberDTO dto= (MemberDTO)session.getAttribute("loginDto");
		System.out.println("session dto : " + dto);
		
		// 암호화된 비번
		String dbPw = dto.getPw();
		
		if(pwEncoder.matches(pw, dbPw)) {
			chkResult = "ok";
		}else {
			chkResult = "no";
		}
		
		return chkResult;
	}
	
	@PostMapping("/pwChange.do")
	@ResponseBody
	public int pwChange(@RequestBody MemberDTO dto ) {
		int n = service.modifyPw(dto);
		
		return n;
	}
}
