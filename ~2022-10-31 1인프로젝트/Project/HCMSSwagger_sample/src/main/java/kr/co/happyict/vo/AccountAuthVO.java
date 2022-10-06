package kr.co.happyict.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountAuthVO {

  private long accountAuthSeq;
  private long accountSeq;

  private String authCode;
  private String authYn = "N";
  private String detailName;
  private String detailTitle;
  private String delYn = "N";
  private String updId;
  private String updDate;
  private String regId;
  private String regDate;
}
