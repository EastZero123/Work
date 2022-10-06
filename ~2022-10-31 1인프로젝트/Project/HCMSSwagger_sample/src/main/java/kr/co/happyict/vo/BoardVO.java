package kr.co.happyict.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO extends PageVO {

  private long boardSeq;
  private long boardManagementSeq;
  private String title;
  private int hits;
  private String content;

  private String tempThumImg;
  private String thumImg;
  private String orgThumImg;
  private String tempThumImg2;
  private String thumImg2;
  private String orgThumImg2;
  private String tempThumImg3;
  private String thumImg3;
  private String orgThumImg3;
  private String thumAlt;
  private String fileYn = "N";
  private String attFile = "N";
  private String notiYn = "N";
  private String skYn = "N";
  private String useYn = "N";
  private String useDate;
  private String column1Value;
  private String column2Value;
  private String column3Value;
  private String category;
  private int orderNum = 999;

  private long[] boardSeqList;
  private int[] orderNumList;

  private String regName;
  private String delYn = "N";
  private String updId;
  private String updDate;
  private String regId;
  private String regDate;

  private List<BoardAttachVO> boardAttachList;

  //에디터 이미지 첨부
  private String editorSpecId;
  private String[] orgImgFileList;
  private String[] newImgFileList;
}
