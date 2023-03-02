package kr.mr.model;

import java.util.Date;

import lombok.Data;
import lombok.Setter;

@Data
public class ReplyVO {
	private int rno;
	private int bid;
	
	private String reply_contents;
	private String replyer;
	private Date reply_date;
	private Date update_date;
}
