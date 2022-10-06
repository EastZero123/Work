package kr.co.happyict.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "게시글 모델 정보")
public class BoardRestVO extends BoardManagementRestVO {

	@ApiModelProperty(value = "게시글 번호")
	private long boardSeq;
	@ApiModelProperty(value = "게시글 카테고리")
	private long boardManagementSeq;
	@ApiModelProperty(value = "게시판 제목")
	private String title;
	@ApiModelProperty(value = "게시판 내용")
	private String content;
	@ApiModelProperty(value = "작성자 이름")
	private String regName;
	@ApiModelProperty(value = "작성자 아이디")
	private String regId;
	@ApiModelProperty(value = "작성일")
	private String regDate;

}
