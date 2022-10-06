package kr.co.happyict.vo;

import java.io.Serializable;
import kr.co.happyict.util.CommonUtil;

/**
 * ISMS/PIMS 제출 요청시 사용
 *
 * @author vegaaltair77@happyict.co.kr
 */
public class AccessHistoryVO implements Serializable {

  private static final long serialVersionUID = -3115670530589495112L;

  private String acsUrl;
  private String acsUid;
  private String acsUip;
  private String acsPge;
  private String acsCde;

  private int acsAct;

  public String getAcsUrl() {
    return CommonUtil.checkString(acsUrl);
  }

  public void setAcsUrl(String acsUrl) {
    this.acsUrl = acsUrl;
  }

  public String getAcsUid() {
    return CommonUtil.checkString(acsUid);
  }

  public void setAcsUid(String acsUid) {
    this.acsUid = acsUid;
  }

  public String getAcsUip() {
    return CommonUtil.checkString(acsUip);
  }

  public void setAcsUip(String acsUip) {
    this.acsUip = acsUip;
  }

  public String getAcsPge() {
    return CommonUtil.checkString(acsPge);
  }

  public void setAcsPge(String acsPge) {
    this.acsPge = acsPge;
  }

  public String getAcsCde() {
    return CommonUtil.checkString(acsCde);
  }

  public void setAcsCde(String acsCde) {
    this.acsCde = acsCde;
  }

  public int getAcsAct() {
    return CommonUtil.parseInt(acsAct + "");
  }

  public void setAcsAct(int acsAct) {
    this.acsAct = acsAct;
  }
}
