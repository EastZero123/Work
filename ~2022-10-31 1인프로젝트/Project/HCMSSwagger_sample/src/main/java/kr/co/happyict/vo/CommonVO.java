package kr.co.happyict.vo;

import java.io.Serializable;
import kr.co.happyict.util.CommonUtil;

/**
 * 공통 코드
 *
 * @author vegaaltair77@happyict.co.kr
 */
public class CommonVO implements Serializable {

  private static final long serialVersionUID = -412746351353388117L;

  private long COD_IDX;

  private int COD_TYP;
  private int COD_NUM;

  private String COD_VAL;
  private String COD_TOT;
  private String COD_USE;

  public long getCOD_IDX() {
    return CommonUtil.parseLong(COD_IDX + "");
  }

  public void setCOD_IDX(long COD_IDX) {
    this.COD_IDX = COD_IDX;
  }

  public int getCOD_TYP() {
    return CommonUtil.parseInt(COD_TYP + "");
  }

  public void setCOD_TYP(int COD_TYP) {
    this.COD_TYP = COD_TYP;
  }

  public int getCOD_NUM() {
    return CommonUtil.parseInt(COD_NUM + "");
  }

  public void setCOD_NUM(int COD_NUM) {
    this.COD_NUM = COD_NUM;
  }

  public String getCOD_VAL() {
    return CommonUtil.checkString(COD_VAL);
  }

  public void setCOD_VAL(String COD_VAL) {
    this.COD_VAL = COD_VAL;
  }

  public String getCOD_TOT() {
    return CommonUtil.checkString(COD_TOT);
  }

  public void setCOD_TOT(String COD_TOT) {
    this.COD_TOT = COD_TOT;
  }

  public String getCOD_USE() {
    return CommonUtil.checkString(COD_USE);
  }

  public void setCOD_USE(String COD_USE) {
    this.COD_USE = COD_USE;
  }

}
