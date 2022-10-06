package kr.co.happyict.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardManagementVO {

  private long boardManagementSeq;
  private String boardCode;
  private String boardName;
  private String boardSkin = "Basic";
  private int pageRow = 10;
  private int pageBlock = 10;
  private String titleMsg;
  private int titleMax = 0;
  private String editCheck = "N";
  private String editYn = "N";
  private String editExt;
  private int editSize = 0;
  private String thumbimgYn = "N";
  private String thumbimgCheck = "N";
  private String thumbimgExt;
  private String thumbimgAlt;
  private int thumbimgSize = 0;
  private String attYn = "N";
  private String attCheck = "N";
  private int attCount = 0;
  private String attExt;
  private int attSize = 0;
  private String useDateYn = "N";
  private String noticeYn = "N";
  private String categoryYn = "N";

  private String column1 = "";
  private String column1Check = "N";
  private String column1Exptext = "";
  private String column2 = "";
  private String column2Check = "N";
  private String column2Exptext = "";
  private String column3 = "";
  private String column3Check = "N";
  private String column3Exptext = "";

  private String delYn = "N";
  private String updId;
  private String updDate;
  private String regId;
  private String regDate;
}
