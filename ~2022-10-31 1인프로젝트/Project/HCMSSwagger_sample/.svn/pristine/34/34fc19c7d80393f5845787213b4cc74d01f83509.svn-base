package kr.co.happyict.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import kr.co.happyict.util.CommonUtil;

/**
 * 로그인 관리
 *
 * @author vegaaltair77@happyict.co.kr
 */
public class LoginControlVO implements Serializable {

  private static final long serialVersionUID = 156149861149062681L;

  private long logIdx;

  private int loginStartDate;
  private int loginStartMinute;

  private String accountId;
  private String logSessionId;
  private String logStatus;
  private String logIp;

  private Timestamp loginTime;
  private Timestamp logoutTime;

  public long getLogIdx() {
    return CommonUtil.parseLong(logIdx + "");
  }

  public void setLogIdx(long logIdx) {
    this.logIdx = logIdx;
  }

  public int getLoginStartDate() {
    return CommonUtil.parseInt(loginStartDate + "");
  }

  public void setLoginStartDate(int loginStartDate) {
    this.loginStartDate = loginStartDate;
  }

  public int getLoginStartMinute() {
    return CommonUtil.parseInt(loginStartMinute + "");
  }

  public void setLoginStartMinute(int loginStartMinute) {
    this.loginStartMinute = loginStartMinute;
  }

  public String getAccountId() {
    return CommonUtil.checkString(accountId);
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getLogSessionId() {
    return CommonUtil.checkString(logSessionId);
  }

  public void setLogSessionId(String logSessionId) {
    this.logSessionId = logSessionId;
  }

  public String getLogStatus() {
    return CommonUtil.checkString(logStatus);
  }

  public void setLogStatus(String logStatus) {
    this.logStatus = logStatus;
  }

  public String getLogIp() {
    return CommonUtil.checkString(logIp);
  }

  public void setLogIp(String logIp) {
    this.logIp = logIp;
  }

  public Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Timestamp loginTime) {
    this.loginTime = loginTime;
  }

  public Timestamp getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(Timestamp logoutTime) {
    this.logoutTime = logoutTime;
  }
}
