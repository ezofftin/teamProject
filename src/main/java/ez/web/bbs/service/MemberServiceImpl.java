package ez.web.bbs.service;

import java.util.List;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ez.web.bbs.mapper.MemberMapper;
import ez.web.bbs.model.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	@Override
	public List<MemberDTO> memberList() {
		
		return mapper.memberList();
	}

	@Override
	public int memberRegister(MemberDTO dto) {
		
		String inputPw = dto.getPw();
		System.out.println("암호화전 : " + inputPw);
		String cipherPw = pwEncoder.encode(inputPw);
		System.out.println("암호화후 : " + cipherPw);
		dto.setPw(cipherPw);
		
		return mapper.memberInsert(dto);
	}

	@Override
	public MemberDTO memberInfo(int no) {
		
		return mapper.memberInfo(no);
	}

	@Override
	public int memberModify(MemberDTO dto) {
		
		return mapper.memberUpdate(dto);
	}

	@Override
	public int memberRemove(int no) {
		
		return mapper.memberDelete(no);
	}

	@Override
	public MemberDTO idCheck(String uid) {
		
		return mapper.idCheck(uid);
	}

	@Override
	public boolean memberLogin(MemberDTO dto, HttpServletRequest req) {

		HttpSession session = req.getSession();
		
		// 아이디와 일치하는 회원정보를 DTO에 담아서 가져옴 
		MemberDTO loginDto =mapper.memberLogin(dto);
		
		if(loginDto !=null) {  // 일치하는 아이디가 있는 경우 
			String inputPw = dto.getPw(); // 사용자가 입력한 비번
			String dbPw = loginDto.getPw(); // DB에 있는 암호화된 비번			
			
			// 암호화 전 조건
			//if(inputPw.equals(dbPw)) { // 비밀번호와 일치
			
			// 암호화 후 조건문
			if(pwEncoder.matches(inputPw, dbPw)) { // 입력비밀번호와 암호비밀번호와 일치되면
				
				session.setAttribute("loginDto", loginDto);
				return true;
			}else { // 비밀번호와 일치하지 않는 경우
				return false;
			}
			
		}else { // 일치하는 아이디가 없는 경우
			return false;
		}
		
	}

	@Override
	public String getFindId(MemberDTO dto) {
		System.out.println("service name : " + dto.getName());
		System.out.println("service phone : " + dto.getPhone());
		
		String result = mapper.findId(dto);
		System.out.println("service result : " + result);
		return result;
	}

	@Override
	public int getFindPw(String uid, String uEmail) {
		// 임시 비밀번호 생성
		String tempPw = UUID.randomUUID().toString().substring(0, 6);
		
		System.out.println("임시비밀번호 : "+ tempPw);
		MimeMessage mail = mailSender.createMimeMessage();
		
		String mailContents = "<h3>임시 비밀번호 발급</h3></br>"				
				+ "<h2>"+tempPw+"</h2>"
				+ "<p>로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다.</p>";
		
		try {
			mail.setSubject("jh아카데미 [임시 비밀번호]", "utf-8");
			mail.setText(mailContents, "utf-8", "html");
			
			// 수신자 셋팅
			mail.addRecipient(RecipientType.TO, new InternetAddress(uEmail));
			
			mailSender.send(mail);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		// 임시비번 암호화
		String cipherPw = pwEncoder.encode(tempPw);
		int n = mapper.findPw(uid, uEmail, cipherPw);
		
		return n;
	}

	@Override
	public int modifyPw(MemberDTO dto) {
		// 새 비밀번호 암호화
		String cipherPw = pwEncoder.encode(dto.getPw());
		
		// 암호화된 비번을 dto에 셋팅
		dto.setPw(cipherPw);
		
		// 수정후 결과값 가져오기 
		int n = mapper.updatePw(dto);
		
		return n;
	}
	
	
}
