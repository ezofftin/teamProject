package ez.web.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ez.web.bbs.mapper.ReplyMapper;
import ez.web.bbs.model.ReplyDTO;
import ez.web.bbs.model.ReplyPageDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyDTO rDto) {
		return mapper.insert(rDto);
	}

	@Override
	public ReplyDTO read(int rno) {
		
		return mapper.select(rno);
	}

	@Override
	public int remove(int rno) {
		
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyDTO rDto) {
		return mapper.update(rDto);
	}

//	@Override
//	public List<ReplyDTO> getList(Integer bid) {
//		
//		return mapper.getListByBid(bid);
//	}
	
	@Override
	public ReplyPageDTO getList(Integer bid, Integer viewPage) {
		// bid에 해당하는 전체 댓글 수 구하기
		int replyCnt = mapper.replyCnt(bid);
		ReplyPageDTO rPageDto = new ReplyPageDTO();
		
		// viewPage가 바뀔 때마다 새롭게 셋팅
		rPageDto.setViewPage(viewPage);
		
		// 페이지별 startIndex를 새롭게 셋팅
		rPageDto.setValue(replyCnt);
		
		List<ReplyDTO> list = mapper.getListByBid(bid, rPageDto.getStartIndex(), 
				rPageDto.getCntPerPage());
		
		rPageDto.setList(list);
		
		return rPageDto;
	}

	@Override
	public int replyCnt(Integer bid) {		
		return mapper.replyCnt(bid);
	}

}
