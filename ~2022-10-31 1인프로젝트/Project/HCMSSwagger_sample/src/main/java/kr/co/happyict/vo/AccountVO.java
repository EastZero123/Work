package kr.co.happyict.vo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountVO extends PageVO {

  private List<AccountAuthVO> accountAuthList;

  private long accountSeq;
  private String accountId;
  private String accountPassword;
  private String accountName;
  private String accountAuths;

  private String useYn = "N";
  private String delYn = "N";
  private String updId;
  private String updDate;
  private String regId;
  private String regDate;
}
