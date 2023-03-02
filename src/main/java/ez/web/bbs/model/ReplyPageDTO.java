package ez.web.bbs.model;

import java.util.List;

import lombok.Data;

@Data
public class ReplyPageDTO {
	private int viewPage = 1; // 현재 보고있는 페이지
	private int blockSize = 5;
	private int blockNum; // 블럭의 위치
	private int blockStart;
	private int blockEnd;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int totalCnt; // 특정 게시글에 대한 전체 댓글수

	private int startIndex; // 각페이지별 시작값
	private int cntPerPage = 5; // 페이지별 댓글 수
	
	private List<ReplyDTO> list;
	
	
	public void setValue(int totalCnt) {
		// 전체 페이지수
		this.totalPage = (int)Math.ceil((double)totalCnt/cntPerPage);
		// 페이지별 시작 인덱스 : 0, 10, 20, 30,....
		this.startIndex = (this.viewPage-1)*cntPerPage;
		
		// 현재 페이지의 블럭위치 : 0부터 시작
		this.blockNum = (viewPage-1)/blockSize;
		// 블럭 시작값 : 1, 6, 11, ...
		this.blockStart = (blockSize*blockNum)+1;
		// 5, 10, 15,...
		this.blockEnd = blockStart + (blockSize - 1);
		if(blockEnd > totalPage) blockEnd = totalPage;
		
		// 이전페이지
		this.prevPage = blockStart - 1;
		// 다음페이지
		this.nextPage = blockEnd + 1;
		
		// 전체 페이지수를 초과할 수 없음
		if(nextPage > totalPage) nextPage = totalPage;
	}
}
