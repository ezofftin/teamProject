package ez.web.bbs.service;

import java.util.List;

import ez.web.bbs.model.BoardDTO;
import ez.web.bbs.model.PageDTO;

public interface BoardService {
	// 게시글 리스트
	public List<BoardDTO> getList(PageDTO pDto);
	
	// 게시글 등록
	void register(BoardDTO dto);
	
	// 게시글 보기
//	BoardDTO view(int bid);
	BoardDTO view(int bid, String mode);
	
	// 게시글 수정
	int modify(BoardDTO dto);
	
	// 게시글 삭제
	int remove(int bid);
	
	// 조회수 증가
//	int hitAdd(int bid);
	
	// 전체 게시글 수
	public int totalCnt(PageDTO pDto);
}
