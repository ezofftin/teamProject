package ez.web.bbs.service;

import java.util.List;

import ez.web.bbs.model.ReplyDTO;
import ez.web.bbs.model.ReplyPageDTO;

public interface ReplyService {
	int register(ReplyDTO rDto);
	
	ReplyDTO read(int rno);
	
	int remove(int rno);
	
	int modify(ReplyDTO rDto);
	
//	List<ReplyDTO> getList(Integer bid);
	ReplyPageDTO getList(Integer bid, Integer viewPage);
	
	int replyCnt(Integer bid);
}
