package kr.co.happyict.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OneonOneVO {

	private Long bno;

	private String title;

	private String content;

	private String writer;

	private LocalDateTime regDate;

	private LocalDateTime modDate;

	public void change(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
