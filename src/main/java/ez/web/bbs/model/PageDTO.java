package ez.web.bbs.model;

public class PageDTO {
	private int viewPage = 1; // 현재 보고있는 페이지
	private int blockSize = 5;
	private int blockNum; // 블럭의 위치
	private int blockStart;
	private int blockEnd;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int totalCnt;	
	
	private int startRowNum;	
	
	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	private int startIndex; // 각페이지별 시작값
	private int cntPerPage = 10; // 페이지별 게시글 수
	
	///////////// search ///////////////
	private String searchType;
	private String keyWord;
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getKeyWord() {
		return keyWord;
	}	
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	///////////////////////////////////

	public void setValue(int totalCnt, int cntPerPage) {
		this.totalCnt = totalCnt;
		this.cntPerPage = cntPerPage;
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
		
		// 행번호 구하기
		// totalCnt(전체게시글 수) : 106, 페이지당 게시글수 : 10, 전체페이지수: 11, 정렬: 내림차순
		
		// 1페이지 -> 106 ~ 97
		// 2페이지 -> 96 ~ 87
		// 3페이지 -> 86 ~ 77
		// 4페이지 -> 76 ~ 67
		// ...
		
		// startRowNum : 페이지의 시작번호
		// 1페이지의 시작번호 : totalCnt - 0;
		// 2페이지의 시작번호 : totalCnt - 10;
		// 3페이지의 시작번호 : totalCnt - 20;
		// ...
		
		startRowNum = totalCnt - (viewPage - 1)*cntPerPage;
	}
	
	
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getBlockNum() {
		return blockNum;
	}
	public void setBlockNum(int blockNum) {
		this.blockNum = blockNum;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	
	
	@Override
	public String toString() {
		return "PageDTO [viewPage=" + viewPage + ", blockSize=" + blockSize + ", blockNum=" + blockNum + ", blockStart="
				+ blockStart + ", blockEnd=" + blockEnd + ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ ", totalPage=" + totalPage + ", startIndex=" + startIndex + ", cntPerPage=" + cntPerPage + "]";
	}
	
	
}
