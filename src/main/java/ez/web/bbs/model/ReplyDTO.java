package ez.web.bbs.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data // getter(), setter(), toString(),....

//@Setter 
//@Getter

//@AllArgsConstructor// 모든 필드에 대한 인자 생성자
//@NoArgsConstructor // 기본생성자

public class ReplyDTO {
	private int rno;
	private int bid;
	
	private String r_contents;
	private String replyer;
	private Date r_date;
	private Date update_date;
}
