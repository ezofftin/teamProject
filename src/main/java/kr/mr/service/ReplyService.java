package kr.mr.service;

import java.util.List;

import kr.mr.model.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO rvo);
	
	public ReplyVO get(int rno);
	
	public int modify(ReplyVO rvo);
	
	public int remove(int rno);
	
	public List<ReplyVO> getList(Integer bid);
}
