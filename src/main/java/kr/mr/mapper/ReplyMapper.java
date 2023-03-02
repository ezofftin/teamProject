package kr.mr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.mr.model.ReplyVO;

public interface ReplyMapper {
	// 삽입
	public int insert(ReplyVO rvo);
	
	// 조회
	public ReplyVO read(int rno);
	
	// 수정
	public int update(ReplyVO rvo);
	
	// 삭제
	public int delete(int rno);
	
	// 특정 게시글에 대한 조회
	// @Param 속성값은 MyBatis에서 SQL을 이용할 때 '#{}'의 이름으로 사용 가능
	public List<ReplyVO> getListByBid(@Param("bid") Integer bid);
}



