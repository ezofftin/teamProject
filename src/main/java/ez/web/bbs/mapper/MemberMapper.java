package ez.web.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ez.web.bbs.model.MemberDTO;

// Mapper interface 사용하기
// - mapper.xml 파일과 interface가 동일한 패키지안에 있어야 한다.
// - mapper.xml의 id와 interface의 추상메소등명이 일치해야한다.
// <mapper namespace와 인터페이스명이 동일해야한다.
// root-context.xml에 <mybatis-spring:scan base-package="ez.web.mapper" /> 설정하기

public interface MemberMapper {
	
	public List<MemberDTO> memberList();
		
	public int memberInsert(MemberDTO dto);
	
	public MemberDTO memberInfo(int no);
	
	public int memberUpdate(MemberDTO dto);
	
	public int memberDelete(int no);
	
	MemberDTO idCheck(String uid);
	
	MemberDTO memberLogin(MemberDTO dto); 
	
	String findId(MemberDTO dto);
	
	int findPw(String uid, String uEmail, String cipherPw);
	
	int updatePw(MemberDTO dto);
}
