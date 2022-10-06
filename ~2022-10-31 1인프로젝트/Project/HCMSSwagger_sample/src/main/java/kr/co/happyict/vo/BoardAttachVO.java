package kr.co.happyict.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@ToString
@ApiModel(description = "첨부파일 모델정보")
public class BoardAttachVO {
	@ApiModelProperty(value = "첨부파일 글 카테고리")
	private long boardAttachSeq;
	@ApiModelProperty(value = "첨부파일 글 번호")
	private long boardSeq;
	@ApiModelProperty(value = "첨부파일 이름")
	private String fileName;
	@ApiModelProperty(value = "첨부파일 원본 이름")
	private String orgFileName;
	@ApiModelProperty(value = "첨부파일 확장자")
	private String fileExt;
	@ApiModelProperty(value = "첨부파일 사이즈")
	private int fileSize;
	private String specId;

	private String delYn = "N";
	private String updId;
	private String updDate;
	private String regId;
	private String regDate;

	private String tempName;

	private MultipartFile upload;
	private String CKEditorFuncNum;
	private String responseType = "";
}
