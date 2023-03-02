package kr.mr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mr.mapper.BoardMapper;
import kr.mr.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList(BoardVO bvo) {
		return mapper.getList(bvo);
	}
	
	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
	}
	
	@Override
//	public BoardVO view(int bid) {
	public BoardVO view(int bid, String mode) {
		if(mode.equals("view")) { // 조회수
			mapper.hitAdd(bid);
		}
		return mapper.view(bid);
	}
	
	@Override
	public int remove(int bid) {
		return mapper.delete(bid);
	}
	
	@Override
	public int modify(BoardVO board) {
		
		return mapper.update(board);
	}
	
	@Override
	public int totalCnt(BoardVO bvo) {
		
		return mapper.totalCnt(bvo);
	}
}
