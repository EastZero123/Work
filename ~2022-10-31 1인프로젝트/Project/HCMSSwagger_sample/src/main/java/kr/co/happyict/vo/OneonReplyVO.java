package kr.co.happyict.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OneonReplyVO {

	private int rno;
	private int bno;
	private String replyText;
	private String replyWriter;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
