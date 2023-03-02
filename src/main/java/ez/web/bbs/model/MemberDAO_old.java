package ez.web.bbs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO_old {
	
	// DI(Dependency Injection : 의존성 주입)
	@Autowired // 스프링 컨테이너에 있는 sqlSessionFactoryBean을 주입받는다. 
	private SqlSessionFactory sqlSessionFactory;
	
	// 회원 리스트 가져오기
	public List<MemberDTO> memberList(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> list = sqlSession.selectList("memberList");
		sqlSession.close();
		return list;
	}
	
	// 회원저장
	public int memberInsert(MemberDTO dto) {
		// openSession()의 기본 인자값이 false, false가 생략
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int cnt = sqlSession.insert("memberInsert", dto);
		// DB 내용이 수정되면 commit을 해줌
		// openSession(true)이면 AutoCommit이 true이기 때문에 commit()을 생략할 수 있다.
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}
	
	// 회원상세정보
	public MemberDTO memberInfo(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO dto = sqlSession.selectOne("memberInfo", no);
		sqlSession.close();
		return dto;
	}
	// 회원수정
	public int memberUpdate(MemberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = sqlSession.update("memberUpdate", dto);
		sqlSession.commit();
		sqlSession.close();
		return cnt;	
	}
	
	
	// 회원 삭제
	public int memberDelete(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = sqlSession.delete("memberDelete", no);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
