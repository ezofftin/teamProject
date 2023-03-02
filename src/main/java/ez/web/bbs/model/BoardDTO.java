package ez.web.bbs.model;

import java.sql.Date;

public class BoardDTO {
	private int bid;
	private String subject;
	private String contents;
	private int hit;
	private String writer;
	private Date regDate;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [bid=" + bid + ", subject=" + subject + ", contents=" + contents + ", hit=" + hit + ", writer="
				+ writer + ", regDate=" + regDate + "]";
	}
	
	
	
}
