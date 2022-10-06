package kr.co.happyict.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import kr.co.happyict.util.CommonUtil;

/**
 * 로그 기록(운영 개발자 확인용)
 *
 * @author vegaaltair77@happyict.co.kr
 */
public class LogVO extends PageVO implements Serializable {

  private static final long serialVersionUID = 4488122156943003792L;

  private long LOG_IDX;

  private String MEM_ID;
  private String REQ_URI;
  private String LOG_MSG;

  private Timestamp LOG_REG;

  private int LOG_ACT;

  public long getLOG_IDX() {
    return CommonUtil.parseLong(LOG_IDX + "");
  }

  public void setLOG_IDX(long LOG_IDX) {
    this.LOG_IDX = LOG_IDX;
  }

  public String getMEM_ID() {
    return CommonUtil.checkString(MEM_ID);
  }

  public void setMEM_ID(String MEM_ID) {
    this.MEM_ID = MEM_ID;
  }

  public String getREQ_URI() {
    return CommonUtil.checkString(REQ_URI);
  }

  public void setREQ_URI(String REQ_URI) {
    this.REQ_URI = REQ_URI;
  }

  public String getLOG_MSG() {
    return CommonUtil.checkString(LOG_MSG);
  }

  public void setLOG_MSG(String LOG_MSG) {
    this.LOG_MSG = LOG_MSG;
  }

  public Timestamp getLOG_REG() {
    return LOG_REG;
  }

  public void setLOG_REG(Timestamp LOG_REG) {
    this.LOG_REG = LOG_REG;
  }

  public int getLOG_ACT() {
    return CommonUtil.parseInt(LOG_ACT + "");
  }

  public void setLOG_ACT(int LOG_ACT) {
    this.LOG_ACT = LOG_ACT;
  }
}
