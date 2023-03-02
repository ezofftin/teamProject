package ez.web.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ez.web.bbs.model.MemberDTO;

public interface MemberService {
	List<MemberDTO> memberList();
	
	int memberRegister(MemberDTO dto);
	
	MemberDTO memberInfo(int no);
	
	int memberModify(MemberDTO dto);
	
	int memberRemove(int no);
	
	MemberDTO idCheck(String uid);
	
	boolean memberLogin(MemberDTO dto, HttpServletRequest req);
	
	String getFindId(MemberDTO dto);
	
	int getFindPw(String uid, String uEmail);
	
	int modifyPw(MemberDTO dto);
	
}
