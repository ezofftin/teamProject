package ez.web.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ez.web.bbs.model.ReplyDTO;

public interface ReplyMapper {
	// 댓글 추가
	public int insert(ReplyDTO rDto);
	
	// 댓글 조회
	public ReplyDTO select(int rno);
	
	// 댓글 삭제
	public int delete(int rno);
	
	// 댓글 수정
	public int update(ReplyDTO rDto);
	
	// 특정 게시글에 대한 댓글리스트
//	public List<ReplyDTO> getListByBid(Integer bid);
	public List<ReplyDTO> getListByBid(@Param("bid") Integer bid, 
			@Param("startIndex") Integer si, 
			@Param("cntPerPage") Integer cp);
	
	// 특정 게시글에 대한 전체 댓글 수 구하기
	public int replyCnt(Integer bid);
}
