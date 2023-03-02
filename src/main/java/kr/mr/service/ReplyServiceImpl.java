package kr.mr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.mr.mapper.ReplyMapper;
import kr.mr.model.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO rvo) {
		return mapper.insert(rvo);
	}	
	@Override
	public ReplyVO get(int rno) {
		return mapper.read(rno);
	}	
	@Override
	public int modify(ReplyVO rvo) {
		return mapper.update(rvo);
	}	
	@Override
	public int remove(int rno) {		
		return mapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Integer bid) {
		
		return mapper.getListByBid(bid);
	}
}
