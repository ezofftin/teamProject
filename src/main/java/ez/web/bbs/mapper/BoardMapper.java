package ez.web.bbs.mapper;

import java.util.List;

import ez.web.bbs.model.BoardDTO;
import ez.web.bbs.model.PageDTO;

public interface BoardMapper {
	// 게시판 리스트
	public List<BoardDTO> getList(PageDTO pDto);
	
	// 게시글 등록
	public void insert(BoardDTO dto);
	
	// 게시글 보기
	public BoardDTO view(int bid);
	
	// 게시글 수정
	public int update(BoardDTO dto);
	
	// 게시글 삭제
	public int delete(int bid);
	
	// 조회수 증가
	public void hitAdd(int bid);
	
	// 전체 게시글 수
	public int totalCnt(PageDTO pDto);
}
