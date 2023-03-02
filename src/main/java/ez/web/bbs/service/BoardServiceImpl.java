package ez.web.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ez.web.bbs.mapper.BoardMapper;
import ez.web.bbs.model.BoardDTO;
import ez.web.bbs.model.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void register(BoardDTO dto) {
		mapper.insert(dto);
	}

	@Override
	public List<BoardDTO> getList(PageDTO pDto) {
		System.out.println("pDto.getKeyWord() : " + pDto.getKeyWord());
		int totalCnt = mapper.totalCnt(pDto);
		System.out.println("게시글 수 : " + totalCnt);

		pDto.setValue(totalCnt, pDto.getCntPerPage());
		return mapper.getList(pDto);
	}

	@Override
//	public BoardDTO view(int bid) {
	public BoardDTO view(int bid, String mode) {
		if(mode.equals("view")) {
			// 조회수 증가
			mapper.hitAdd(bid);
		}
		return mapper.view(bid);
	}

	@Override
	public int modify(BoardDTO dto) {		
		return mapper.update(dto);
	}

	@Override
	public int remove(int bid) {		
		return mapper.delete(bid);
	}
	
	@Override
	public int totalCnt(PageDTO pDto) {		
		return mapper.totalCnt(pDto);
	}

}
