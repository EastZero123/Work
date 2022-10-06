package kr.co.happyict.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public final class CommonUtil {

  private static final String SEPERATOR_HYPHEN = "-";
  private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final String REGEX_PHONE_NORMAL = "(\\d{2,3})(\\d{3,4})(\\d{4})";
  private static final String REGEX_PHONE_HYPHEN = "(\\d{2,3})([-\\t\\n\\x0B\\f\\r]*)(\\d{3,4})([-\\t\\n\\x0B\\f\\r]*)(\\d{4})";
  private static final String REGEX_JIMIN = "(\\d{6})([-\\t\\n\\x0B\\f\\r])[1234]\\d{6}";
  private static final String REGEX_BIRTH_SIX = "(\\d{4})\\d{2}";
  private static final String REGEX_BIRTH_EIGHT = "(\\d{6})\\d{2}";
  private static final String REGEX_KR_NAME_FIRTH = "([가-힣]){1}";
  private static final String REGEX_KR_NAME_SECOND = "([가-힣])\\W{1}";
  private static final String REGEX_KR_NAME_THIRD = "([가-힣])\\W{1}([가-힣])";
  private static final String REGEX_KR_NAME_OTHER = "([가-힣])\\W{1}([가-힣]+)";
  private static final String REGEX_EN_NAME_FIRTH = "\\w{1}([a-zA-Z])";
  private static final String REGEX_EN_NAME_SECOND = "\\w{2}([a-zA-Z])";
  private static final String REGEX_EN_NAME_OTHER = "([a-zA-Z]+)\\w{2}([a-zA-Z])([a-zA-Z])";
  private static final String REGEX_MASKINGID = "([0-9]{4})([0-9]{4})";
  private static final String REGEX_SPECIAL = "!@#$%^&*?_~";
  private static final String REGEX_NUMBER = "^[0-9]*$";
  private static final String REGEX_PASSWORD = "^[_A-Za-z0-9|!|@|#|$|%|^|&|*|?|_|~]*$";

  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

  /**
   * 에디터 xss 필터 적용 후 텍스트로 뿌려질때 Decoder
   */
  public static String editorToHtml(String str) {
    return str.replaceAll("&lt;", "<")
        .replaceAll("&gt;", ">")
        .replaceAll("&amp;", "&")
        .replaceAll("&quot;", "\"")
        .replaceAll("&apos;", "'");
  }

  /**
   * mysql dataTime yyyy.mm.dd 형식으로 변환
   */
  public static String dateToFormat(String str) throws ParseException {
    SimpleDateFormat sourceFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sourceFormatter.parse(str);

    Timestamp ts = new Timestamp(date.getTime());
    SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy.MM.dd");

    return simpledateformat.format(ts);
  }

  /**
   * 문자가 Null이거나 empty String일 경우 true, 아닐 경우 false
   *
   * @return boolean
   * @author vegaaltair77@happyict.co.kr
   */
  public static boolean stringIsNull(String str) {
    return str == null || str.trim().length() == 0;
  }

  /**
   * Object가 Null일 경우 true, Null이 아닐 경우 false
   *
   * @return boolean
   * @author vegaaltair77@happyict.co.kr
   */
  public static boolean objectIsNull(Object obj) {
    return obj == null;
  }

  /**
   * 문자가 Null이거나 empty String일 경우 "", 아닐 경우 입력받은 문자를 가공하지 않고 리턴
   *
   * @return String
   * @author vegaaltair77@happyict.co.kr
   */
  public static String checkString(String str) {
    return (stringIsNull(str) || str.length() == 0) ? "" : str;
  }

  /**
   * 이메일 형식이 맞는지 확인
   *
   * @return true : 이메일 형식임, false : 이메일 형식이 아님
   */
  public static boolean isEmail(final String email) {
    return isValid(REGEX_EMAIL, email);
  }

  /**
   * 핸드폰 형식 검사
   *
   * @return true : 핸드폰 형식임, false : 핸드폰 형식이 아님
   */
  public static boolean isPhoneNum(final String phone) {
    return !phone.contains(SEPERATOR_HYPHEN) ? isValid(REGEX_PHONE_NORMAL, phone)
        : isValid(REGEX_PHONE_HYPHEN, phone);
  }

  /**
   * 숫자만 있는지 검사
   *
   * @return true : 숫자만으로 구성 됨, false : 숫자 이외의 값 포함 됨
   */
  public static boolean isNumber(final String number) {
    log.info("검사실행");
    if (stringIsNull(number)) {
      return false;
    } else {
      return isValid(REGEX_NUMBER, number);
    }
  }

  /**
   * 비밀번호 생성에 사용할 수 있는 값 사용 유무
   *
   * @return return true : 사용 가능, false : 사용 불가능
   */
  public static boolean isPassword(final String password) {
    return isValid(REGEX_PASSWORD, password);
  }

  /**
   * 패턴 검사
   *
   * @param regex  패턴 형식
   * @param target 패턴 검사 대상
   * @return true : 정상, false : 비정상
   */
  private static boolean isValid(final String regex, final String target) {
    return Pattern.compile(regex).matcher(target).matches();
  }

  /**
   * 데이터가 없을 경우 '-'표시
   *
   * @return String
   * @author vegaaltair77@happyict.co.kr
   */
  public static String noData(String val) {
    return stringIsNull(val) ? SEPERATOR_HYPHEN : val;
  }

  /**
   * 전화번호 분리
   *
   * @return String[]
   */
  public static String[] phoneParse(String phone) {
    return (stringIsNull(phone)) ? new String[0] : phone.split(SEPERATOR_HYPHEN);
  }

  /**
   * 문자열이 길 때 어떤 특정한 길이에서 짜른 후 뒤에 "..."를 붙임, 단, 한글이나 영문이나 모두 1글자로 취급한다.
   *
   * @param val       자르고 싶은 문장
   * @param cutLength 자르고 싶은 길이
   * @return 일정길이로 자른 문자열에 "..."를 붙여 반환한다.
   */
  public static String cutString(String val, int cutLength) {
    return stringIsNull(val)
        ? checkString(val) : val.length() > cutLength
        ? val.substring(0, cutLength) + "..." : val;
  }

  /**
   * 문자열이 길 때 어떤 특정한 길이에서 짜른 후 뒤에 "..."를 붙임, 단, 한글은 2바이트, 영문은 1바이트로 취급하여 반올림해서 자른다ㅏ.
   *
   * @param val       자르고 싶은 문장
   * @param cutLength 자르고 싶은 길이
   * @return 일정길이로 자른 문자열에 "..."를 붙여 반환한다.
   */
  public static String cutStringToByte(String val, int cutLength) {
    if (cutLength < 4) {
      return val;
    }

    int j = 0;
    int len = val.length();
    StringBuilder stringbuffer = new StringBuilder();

    for (int idx = 0; idx < len; idx++) {
      char c = val.charAt(idx);

      if (c < '\uAC00' || '\uD7A3' < c) {
        j++;
      } else {
        j += 2;
      }

      stringbuffer.append(c);

      if (j <= cutLength - 3) {
        continue;
      }

      stringbuffer.append("...");

      break;
    }

    return stringbuffer.toString();
  }


  /**
   * Get으로 전송되는 Parameter 값 검증
   *
   * @param parameter Get으로 전송되는 파라미터 값
   * @return xss에 위배되는 파라미터를 사용 불가로 변조한 문자열 파라미터
   */
  public static String xssFilte(String parameter) {
    final String[] arrFilters = new String[]{
        "<script>", "%3Cscript", "%3Ealert", "ja%0Av%0Aa%0As%0Ac%0Aript", "JaVaScRiPt",
        "javascript",
        "ScRiPt%20%0a%0d", "JaVaScRiPt", "ScRiPt%20%0a%0d", "javascript", "vbscript", "expression",
        "applet", "meta", "xml", "blink", "link", "style", "script", "embed", "object", "iframe",
        "frame",
        "frameset", "ilayer", "layer", "bgsound", "<title", "base", "eval", "innerHTML", "charset",
        "document",
        "string", "create", "append", "binding", "alert", "msgbox", "refresh", "embed", "ilayer",
        "applet",
        "cookie", "void", "href", "nabort", "@import", "aim:", "%0da=eval", "allowscriptaccess",
        "xmlns:html",
        "<html xmlns", "xmlns:html=", "http-equiv=refresh", "http-equiv=refresh", "x-scriptlet",
        "0%0d%0a%00",
        "moz-binding", "res://", "#exec", "background=", "&#x", "%u0", "string.fromcharcode",
        "firefoxurl",
        "<br size=", "list-style-image", "acunetix_wvs", "wvs-xss", "lowsrc", "dynsrc", "behavior",
        "activexobject",
        "microsoft.xmlhttp", "clsid:cafeefac-dec7-0000-0000-abcdeffedcba",
        "application/npruntime-scriptable-plugin;deploymenttoolkit",
        "onactivae", "onafterprint", "onafterupdate", "onbefore", "onbeforeactivate",
        "onbeforecopy", "onbeforecut",
        "onbeforedeactivate", "onbeforeeditfocus", "onbeforepaste", "onbeforeprint",
        "onbeforeunload", "onbeforeupdate",
        "onblur", "onbounce", "oncellchange", "onchange", "onclick", "oncontextmenu",
        "oncontrolselect", "oncopy",
        "oncut", "ondataavailable", "ondatasetchanged", "ondatasetcomplete", "ondblclick",
        "ondeactivate", "ondrag",
        "ondragend", "ondragenter", "ondragleave", "ondragover", "ondragstart", "ondrop", "onerror",
        "onerrorupdate",
        "onfilterchange", "onfinish", "onfocus", "onfocusin", "onfocusout", "onhelp", "onkeydown",
        "onkeypress", "onkeyup",
        "onlayoutcomplete", "onload", "onlosecapture", "onmousedown", "onmouseenter",
        "onmouseleave", "onmousemove", "onmouseout",
        "onmouseover", "onmouseup", "onmousewheel", "onmove", "onmoveend", "onmovestart", "onpaste",
        "onpropertychange",
        "onreadystatechange", "onreset", "onresize", "onresizeend", "onresizestart", "onrowenter",
        "onrowexit", "onrowsdelete",
        "onrowsinserted", "onscroll", "onselect", "onselectionchange", "onselectstart", "onstart",
        "onstop", "onsubmit", "onunload"
    };

    if (parameter != null) {
      for (String arrFilter : arrFilters) {
        String tmp = "(?i)" + arrFilter.trim();
        parameter = parameter.replaceAll(tmp, "_");
      }
    }

    return parameter;
  }

  /**
   * XSS 특수 문자(태그) 필터링
   *
   * @param val : 특수 문자로 변활 할 태그
   * @return 특수 문자로 변환 된 문자 열
   */
  public static String specialTranslate(String val) {
    final String[] whiteList = new String[]{"p", "br"};
    final String[] tags = new String[]{"<", ">", "\0", "</", "/>", "\"", "&", "'"};
    final String[] specials = new String[]{"&lt;", "&gt;", " ", "&lt;/", "/&gt;", "&quot;", "&amp;",
        "&#039"};
    final int len = whiteList.length;
    int idx = 0;

    if (CommonUtil.checkString(val).length() > 0) {
      for (String tag : tags) {
        val = val.replaceAll(tag, specials[idx++]);
      }

      for (idx = 0; idx < len; idx++) {
        val = val.replaceAll(specials[0] + val + specials[2], tags[0] + val + specials[2]);
        val = val.replaceAll(specials[0] + val + specials[1], tags[0] + val + tags[1]);
        val = val.replaceAll(specials[2] + val + specials[1], specials[2] + val + tags[1]);
        val = val.replaceAll(val + specials[4], val + tags[4]);
        val = val.replaceAll(specials[3] + val, tags[3] + val);
      }
    }

    return val;
  }

  /**
   * 태그를 특수문자로 치환
   *
   * @return 특수 문자로 치환된 문자열 반환
   */
  public static String tagTranslate(String val) {
    if ("".equals(checkString(val))) {
      return "";
    }

    StringBuilder stringbuffer = new StringBuilder(val.length());
    int len = val.length();

    for (int idx = 0; idx < len; idx++) {
      char c = val.charAt(idx);

      if (c == '<') {
        stringbuffer.append("&lt;");
      } else if (c == '>') {
        stringbuffer.append("&gt;");
      } else if (c == '"') {
        stringbuffer.append("&quot;");
      } else if (c == '&') {
        stringbuffer.append("&amp;");
      } else if (c == '\'') {
        stringbuffer.append("&#039");
      } else {
        stringbuffer.append(c);
      }
    }

    return stringbuffer.toString();
  }

  /**
   * Token 문자열 분리
   *
   * @param sourceString '[' + token + ']'로 구분된 문자열
   * @param token        구분자
   * @return 분리된 문자열을 List<String>에 저장하여 리턴
   */
  public static List<String> tokenSplit(String sourceString, String token) {
    if (sourceString == null || sourceString.trim().equals("")) {
      return null;
    }

    String[] arrData = sourceString.split("[" + token + "]");
    int len = arrData.length;

    return new ArrayList<>(Arrays.asList(arrData).subList(0, len));
  }

  /**
   * 문자열 내용 중 enter가 있을 경우 <br>로 변환. 단, 변환 후 제일 마지막 <br>은 삭제
   */
  public static String enterToBrTag(String val) {
    if (val == null || val.trim().equals("")) {
      return "";
    }

    val = tagTranslate(val);
    StringTokenizer stringtokenizer = new StringTokenizer(val, "\n");
    StringBuilder stringbuffer = new StringBuilder();

//    for (; stringtokenizer.hasMoreTokens();
//        stringbuffer.append(stringtokenizer.nextToken() + "<br>"))
//      ;

    stringbuffer.replace(stringbuffer.lastIndexOf("<br>"), stringbuffer.length(), "");

    return stringbuffer.toString();
  }

  /**
   * String형 숫자를 int형 숫자로 반환한다. 변환이 안되는 String은 0으로 반환한다.
   *
   * @param requestParameter int로 변환한 문자
   * @return int형으로 바뀐 String
   */
  public static int parseInt(String requestParameter) {
    try {
      return Integer.parseInt(requestParameter);
    } catch (NumberFormatException exception) {
      return 0;
    }
  }

  /**
   * String형 숫자를 long형 숫자로 반환한다. 변환이 안되는 String은 0으로 반환한다.
   *
   * @param requestParameter long으로 변환한 문자
   * @return long형으로 바뀐 String
   */
  public static long parseLong(String requestParameter) {
    try {
      return Long.parseLong(requestParameter);
    } catch (NumberFormatException exception) {
      return 0;
    }
  }

  /**
   * String형 숫자를 float형 숫자로 반환한다. 변환이 안되는 String은 0.0f으로 반환한다.
   *
   * @param requestParameter float으로 변환한 문자
   * @return float형으로 바뀐 String
   */
  public static float parseFloat(String requestParameter) {
    try {
      return Float.parseFloat(requestParameter);
    } catch (NumberFormatException exception) {
      return 0;
    }
  }

  /**
   * String형 숫자를 double형 숫자로 반환한다. 변환이 안되는 String은 0.0d으로 반환한다.
   *
   * @param requestParameter double으로 변환한 문자
   * @return double형으로 바뀐 String
   */
  public static double parseDouble(String requestParameter) {
    try {
      return Double.parseDouble(requestParameter);
    } catch (NumberFormatException e) {
      return 0.0D;
    }
  }

  /**
   * request로 받은 문자가 null이면 target(int)값을 반환한다.
   */
  public static int convertNullToInt(String requestParameter, int target) {
    return stringIsNull(requestParameter) ? target : parseInt(requestParameter);
  }

  /**
   * 통화형식처럼 숫자 3자리미다 ,(콤마)를 찍는다. double(int)형 인자를 받는다.
   *
   * @param d double(int)형 통화형식
   * @return 3자리마다 ,(콤마)가 찍힌 형식
   */
  public static String moneyFormat(double d) {
    try {
      return NumberFormat.getNumberInstance().format(d);
    } catch (NumberFormatException e) {
      return "0";
    }
  }

  /**
   * 통화형식처럼 숫자 3자리미다 ,(콤마)를 찍는다. String형 인자를 받는다.
   *
   * @param money String형 통화형식
   * @return 3자리마다 ,(콤마)가 찍힌 형식
   */
  public static String moneyFormat(String money) {
    NumberFormat numberformat = NumberFormat.getNumberInstance();

    try {
      return numberformat.format(numberformat.parse(money));
    } catch (ParseException parseexception) {
      return "0";
    }
  }

  /**
   * 대상문자열안의 논리적 값이 숫자를 표현하면 true, 아니면 false를 반환한다.
   *
   * @param str 대상문자열
   * @return 대상문자열의 논리적 값이 숫자이면 true, 아니면 false
   */
  public static boolean isStringIsNumeric(String str) {
    NumberFormat nf = NumberFormat.getInstance();

    try {
      nf.parse(str);
      return true;
    } catch (ParseException parseexception) {
      return false;
    }
  }

  /**
   * 대상문자열안의 논리적 숫자앞에 원하는 size의 자릿수에 맞게 '0'를 붙인다. 예) 000000000 (일억자리수)에 345를 넣었을 경우 '000000345'반환
   *
   * @param str  대상문자열
   * @param size 원하는 자릿수
   * @return 대상문자열에 자릿수 만큼의 '0'를 붙인 문자열
   */
  public static String zerofill(String str, int size) {
    try {
      NumberFormat nf = NumberFormat.getInstance();
      return zerofill(nf.parse(str), size);
    } catch (ParseException e) {
      return "";
    }
  }

  /**
   * int형 숫자앞에 원하는 size의 자릿수에 맞게 '0'를 붙인다. 예) 000000000 (일억자리수)에 345를 넣었을 경우 '000000345'반환
   *
   * @param num  대상 int형 숫자
   * @param size 원하는 자릿수
   * @return 대상문자열에 자릿수 만큼의 '0'를 붙인 문자열
   */
  public static String zerofill(int num, int size) {
    return zerofill(Integer.valueOf(num), size);
  }

  /**
   * double형 숫자앞에 원하는 size의 자릿수에 맞게 '0'를 붙인다. 예) 000000000 (일억자리수)에 345를 넣었을 경우 '000000345'반환
   *
   * @param num  대상 double형 숫자
   * @param size 원하는 자릿수
   * @return 대상문자열에 자릿수 만큼의 '0'를 붙인 문자열
   */
  public static String zerofill(double num, int size) {
    return zerofill(new Double(num), size);
  }

  /**
   * Number형 숫자앞에 원하는 size의 자릿수에 맞게 '0'를 붙인다. 예) 000000000 (일억자리수)에 345를 넣었을 경우 '000000345'반환
   *
   * @param num  대상 Number형 숫자
   * @param size 원하는 자릿수
   * @return 대상문자열에 자릿수 만큼의 '0'를 붙인 문자열
   */
  public static String zerofill(Number num, int size) {
    StringBuilder zero = new StringBuilder();

    for (int i = 0; i < size; i++) {
      zero.append("0");
    }

    DecimalFormat df = new DecimalFormat(zero.toString());

    return df.format(num);
  }

  public static Timestamp makeTimestamp(String string) throws Exception {
    return makeTimestamp(string, "yyyy-MM-dd HH:mm:ss");
  }

  public static Timestamp makeTimestamp(String string, String format) throws Exception {
    SimpleDateFormat sourceFormatter = new SimpleDateFormat(format);
    Date date = sourceFormatter.parse(string);

    return new Timestamp(date.getTime());
  }

  /**
   * java.sql.Timestamp형태의 날짜를 "yyyy-mm-dd hh:mm:ss"형의 날짜로 변환하여 반환한다.
   *
   * @param ts java.sql.Timestamp형태의 날짜
   * @return "yyyy-MM-dd hh:mm:ss"형의 날짜
   */
  public static String getSQLDate(Timestamp ts) {
    if (ts == null) {
      return "";
    } else {
      return ts.toString();
    }
  }

  public static String getDate(Timestamp ts) {
    SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
    return simpledateformat.format(ts);
  }

  public static String getDate(Timestamp ts, String format) {
    SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
    return simpledateformat.format(ts);
  }

  /**
   * "yyyy-MM-dd hh:mm:ss"형태의 날짜를 java.sql.Timestamp형의 날짜로 변환하여 반환한다.
   *
   * @param date "yyyy-MM-dd hh:mm:ss"형태의 날짜
   * @return java.sql.Timestamp형의 날짜
   */
  public static Timestamp setSQLDate(String date) {

    if (stringIsNull(date)) {
      return null;
    } else if (isYMDHMSF(date)) {
      return Timestamp.valueOf(date);
    } else if (isYMDHMS(date)) {
      return Timestamp.valueOf(date + ".0");
    } else if (isYMD(date)) {
      return Timestamp.valueOf(date + " 00:00:00.0");
    } else {
      return null;
    }
  }

  public static boolean isYMDHMSF(String date) {
    Pattern p = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{1}");
    Matcher m = p.matcher(date);

    return m.find();
  }

  public static boolean isYMDHMS(String date) {
    Pattern p = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}");
    Matcher m = p.matcher(date);

    return m.find();
  }

  public static boolean isYMD(String date) {
    Pattern p = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
    Matcher m = p.matcher(date);

    return m.find();
  }

  public static boolean validateDate(String dayOfBirth) {
    Pattern pattern = Pattern.compile("^((19|20)\\d\\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$");
    Matcher matcher = pattern.matcher(dayOfBirth);

    return matcher.find();
  }

  /**
   * "yyyy-MM-dd hh:mm:ss"형의 날짜를 반환한다.
   *
   * @return yyyy-MM-dd hh:mm:ss 형의 날짜
   */
  public static String getThisDate() {
    return getThisDate("yyyy-MM-dd hh:mm:ss");
  }

  /**
   * 인자로 들어오는 형식의 날짜를 반환한다.
   *
   * @param s 날짜 형식
   * @return 인자로 들어오는 형식의 날짜
   */
  public static String getThisDate(String s) {
    SimpleDateFormat simpledateformat = new SimpleDateFormat(s, Locale.US);

    return simpledateformat.format(new Date());
  }

  /**
   * 날짜 데이터가 오늘보다 과거인지 미래인지 체크한다.
   *
   * @param s yyyy-MM-dd 형식의 문자열 날짜 데이터
   * @return 과거면 false, 같거나 미래면 true
   */
  public static boolean isAway(String s) {
    return isAway(s.substring(0, 4), s.substring(5, 7), s.substring(8));
  }

  /**
   * 날짜 데이터가 오늘보다 과거인지 미래인지 체크한다.
   *
   * @param yyyy 년(年)
   * @param mm   월(月)
   * @param dd   일(日)
   * @return 과거면 false, 같거나 미래면 true
   */
  public static boolean isAway(String yyyy, String mm, String dd) {
    int y = parseInt(yyyy);
    int m = parseInt(mm);
    int d = parseInt(dd);

    return isAway(y, m, d);
  }

  /**
   * 날짜 데이터가 오늘보다 과거인지 미래인지 체크한다.
   *
   * @param y 년(年)
   * @param m 월(月)
   * @param d 일(日)
   * @return 과거면 false, 같거나 미래면 true
   */
  public static boolean isAway(int y, int m, int d) {

    GregorianCalendar rightNow = new GregorianCalendar();
    GregorianCalendar fromDate = new GregorianCalendar(y, (m - 1), d);

    return !fromDate.before(rightNow);
  }

  /**
   * 배열을 format 에 맞는 문자열로 변환.
   *
   * @return String
   */
  public static String delimitObjectsToString(String format, String[] arrayStr) {

    StringBuilder sb = new StringBuilder();
    int arrayLength = arrayStr.length;

    for (int i = 0; i < arrayLength; i++) {
      sb.append(arrayStr[i]);

      if (arrayLength != (i + 1)) {
        sb.append(format);
      }
    }
    return sb.toString();
  }

  /**
   * null이거나 isEmpty()가 true 또는 length가 0 일때 true 를 반환
   *
   * @param obj Collection, Map 이나 String[]
   * @return null이거나 공백이면 true반환, 아니면 false 반환, Collection 이나 Map 이아닌경우 null 로 판단
   */
  @SuppressWarnings("rawtypes")
  public static boolean isNullOrEmpty(Object obj) {

    if (obj == null) {
      return true;
    }

    if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    } else if (obj instanceof Map) {
      return ((Map) obj).isEmpty();
    } else if (obj instanceof String[]) {
      return ((String[]) obj).length <= 0;
    }

    return true;
  }

  /**
   * 특수문자 제거
   */
  public static String removeSpecialLetter(String str) {
//        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
    // Percent-encoding < !#$&'()*+,/:;=?@[] > 문자 모두 스페이스로 변경
    String match = "[!#$&'()*+,/:;=?@\\[\\]]";
    str = str.replaceAll(match, " ");

    return str;
  }

  public static String getRequestURL(HttpServletRequest request) {
    StringBuilder builder = new StringBuilder();
    builder.append(request.getRequestURL().toString());

    if (StringUtils.isNotEmpty(request.getQueryString())) {
      builder.append("?").append(request.getQueryString());
    }

    return builder.toString();
  }

  private static final String[] HEADERS_TO_TRY = {
      "X-Forwarded-For",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_X_FORWARDED_FOR",
      "HTTP_X_FORWARDED",
      "HTTP_X_CLUSTER_CLIENT_IP",
      "HTTP_CLIENT_IP",
      "HTTP_FORWARDED_FOR",
      "HTTP_FORWARDED",
      "HTTP_VIA",
      "REMOTE_ADDR"};

  public static String getClientIpAddress(HttpServletRequest request) {
    for (String header : HEADERS_TO_TRY) {
      String ip = request.getHeader(header);

      if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
        return ip;
      }
    }

    return request.getRemoteAddr();
  }

  public static String getURLWithContextPath(HttpServletRequest request) {
    return request.getScheme() + "://" + request.getServerName();
  }

  public static String mapToString(HashMap<String, String> map) {
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

    while (iter.hasNext()) {
      Map.Entry<String, String> entry = iter.next();
      sb.append(entry.getKey());
      sb.append('=').append('"');
      sb.append(entry.getValue());
      sb.append('"');
      if (iter.hasNext()) {
        sb.append(',').append(' ');
      }
    }

    return sb.toString();
  }

  public static String getValue(HashMap<String, String> map, String key) {
    return map.getOrDefault(key, null);
  }

  public static void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }

  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    String[] result = new String[emptyNames.size()];

    return emptyNames.toArray(result);
  }

  /**
   * XSS 관련 제거
   *
   * @param val : 파라미터
   * @return XSS 관련 제거된 문자열
   */
  public String cleanXSS(String val) {
    val = val.replaceAll(" ", "");

    Pattern scriptPattern = Pattern.compile("<script>[.*?]</script>", Pattern.CASE_INSENSITIVE);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("<script(.*?)>",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("eval\\((.*?)\\)",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("expression\\((.*?)\\)",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    val = scriptPattern.matcher(val).replaceAll("");

    scriptPattern = Pattern.compile("onload(.*?)=",
        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    val = scriptPattern.matcher(val).replaceAll("");

    return val;
  }

  /**
   * SQL Injection 방어를 위한 쿼리 관련 parameter 필터링
   *
   * @param val : parameter
   * @return 불필요한 parameter를 제거된 쿼리 문자열
   */
  public String sqlFilter(String val) {
    final String[] filters = new String[]{"'", "\"", "--", ",", "\\(", "\\)", "#", ">", "<", "=",
        "\\*/", "/\\*", "\\+", "%", ",", "@", ";", "\\\\", "|", "[", "]", "&"};

    for (String string : filters) {
      val = val.replaceAll(string, "");
    }

    return val;
  }

  /**
   * 영문대문자, 영문소문자, 숫자, 특수문자 중 2종류 조합인 경우 10자리 이상 15자리 이내의 길이로 구성 여부 확인 영문대문자, 영문소문자, 숫자, 특수문자 중 3종류
   * 조합인 경우 8자리 이상 15자리 이내의 길이로 구성 여부 확인 2021.06.23 보안정책변경 수정
   *
   * @param val 비밀번호
   * @return true : 사용가능, false : 사용 불가
   */
  public static boolean checkUnion(String val) {
    val = CommonUtil.checkString(val);
    final int valLength = val.length();
    boolean NumberCH = false;
    boolean CharCH = false;
    boolean SpecialCharCH = false;
    final int specialLength = REGEX_SPECIAL.length();
    String chNumber = "0123456789";
    char tmp;

    for (int idx = 0; idx < valLength; idx++) {
      tmp = val.charAt(idx);

      for (int j = 0; j < chNumber.length(); j++) {
        if (chNumber.charAt(j) == tmp) {
          NumberCH = true;

          break;
        }
      }

      if (((tmp >= 'a') && (tmp <= 'z')) || ((tmp >= 'A') && (tmp <= 'Z'))) {
        CharCH = true;
      }

      for (int idx2 = 0; idx2 < specialLength; idx2++) {
        if (REGEX_SPECIAL.charAt(idx2) == tmp) {
          SpecialCharCH = true;

          break;
        }
      }
    }

    if ((NumberCH) && (CharCH) && (!SpecialCharCH) && (valLength >= 10)) {
      return true;
    } else {
      return (NumberCH) && (CharCH) && (SpecialCharCH) && (valLength >= 8);
    }
  }

  /**
   * 3회 이상 반복 연속된 문자와 숫자 사용 여부 확인(특수문자 제외)
   *
   * @param val 비밀번호
   * @return true : 사용 가능, false : 사용 불가
   */
  public static boolean oneStringRepetition(String val) {
    val = CommonUtil.checkString(val);
    final int valLength = val.length() - 2;
    String[] tmpArray = new String[3];

    for (int idx = 0; idx < valLength; idx++) {
      tmpArray[0] = val.substring(idx, idx + 1);
      tmpArray[1] = val.substring(idx + 1, idx + 2);
      tmpArray[2] = val.substring(idx + 2, idx + 3);

      if ((tmpArray[0].charAt(0) >= 'a' && tmpArray[0].charAt(0) <= 'z')
          || (tmpArray[0].charAt(0) >= 'A' && tmpArray[0].charAt(0) <= 'Z')
          || (tmpArray[0].charAt(0) >= '0' && tmpArray[0].charAt(0) <= '9')
          || (tmpArray[1].charAt(0) >= 'a' && tmpArray[1].charAt(0) <= 'z')
          || (tmpArray[1].charAt(0) >= 'A' && tmpArray[1].charAt(0) <= 'Z')
          || (tmpArray[1].charAt(0) >= '0' && tmpArray[1].charAt(0) <= '9')
          || (tmpArray[2].charAt(0) >= 'a' && tmpArray[2].charAt(0) <= 'z')
          || (tmpArray[2].charAt(0) >= 'A' && tmpArray[2].charAt(0) <= 'Z')
          || (tmpArray[2].charAt(0) >= '0' && tmpArray[2].charAt(0) <= '9')) {
        if (tmpArray[0].equals(tmpArray[1])) {
          if (tmpArray[1].equals(tmpArray[2])) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * 두 자 이상의 동일문자 연속성 확인(두 번 이상 중복될 경우)
   *
   * @param val 비밀번호
   * @return true : 사용 가능, false : 사용 불가
   */
  public static boolean twoStringRepetition(String val) {
    val = CommonUtil.checkString(val);
    final int valLength = val.length() - 2;
    final int valLength2 = val.length() - 1;
    String[] tmpArray = new String[2];
    int count;

    for (int idx = 0; idx < valLength; idx++) {
      count = 0;
      tmpArray[0] = val.substring(idx, idx + 2);

      for (int idx2 = 0; idx2 < valLength2; idx2++) {
        tmpArray[1] = val.substring(idx2, idx2 + 2);

        if (tmpArray[1].equals(tmpArray[0])) {
          count++;

          if (count >= 2) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * 문자 또는 숫자의 연속성 확인
   *
   * @param val 비밀번호
   * @return true: 사용 가능, false : 사용 불가능
   */
  public static boolean checkContinuity(String val) {
    int int1, int2, int3;

    for (int idx = 0; idx < val.length() - 2; idx++) {
      int1 = val.charAt(idx);
      int2 = val.charAt(idx + 1);
      int3 = val.charAt(idx + 2);

      if ((int1 - int2) == 1 && (int2 - int3) == 1) {
        return false;
      }

      if ((int1 - int2) == -1 && (int2 - int3) == -1) {
        return false;
      }
    }

    return true;
  }

  /**
   * 아이디가 비밀번호에 사용되었는지 여부
   *
   * @param val  비밀번호
   * @param val2 사용자 아이디
   * @return true : 사용 가능, false : 사용 불가
   */
  public static boolean personalInformation(String val, String val2) {
    return !val.contains(val2);
  }

  /**
   * 특정 패턴을 갖는 문자열 확인
   *
   * @param val 비밀번호
   * @return true : 사용 가능, false : 사용 불가
   */
  public static boolean keyboardContinuity(String val) {
    final String[] keyboard = {
        "0123456789", "9876543210"
        , "QWERTYUIOP", "POIUYTREWQ"
        , "ASDFGHJKL", "LKJHGFDSA"
        , "ZXCVBNM", "MNBVCXZ"
        , "1QAZ", "ZAQ1"
        , "2WSX", "XSW2"
        , "3EDC", "CDE3"
        , "4RFV", "VFR4"
        , "5TGB", "BGT5"
        , "6YHN", "NHY6"
        , "7UJM", "MJU7"
        , "0OKM", "MKO0"
        , "9IJN", "NJI9"
        , "8UHB", "BHU8S"
        , "7YGV", "VGY7"
        , "6TFC", "CFT6"
        , "5RRDX", "XDR5"
        , "4ESZ", "ZSE4"
    };

    for (String s : keyboard) {
      if (val.toUpperCase().contains(s)) {
        return false;
      }
    }

    return true;
  }

  /*
   * 모바일접속 여부 확인 (true, false)
   */
  public static boolean isMobile(HttpServletRequest request) {

    String userAgent = request.getHeader("user-agent");
    boolean mobile1 = userAgent.matches(
        ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
    boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");

    return mobile1 || mobile2;
  }

  /*
   * 모바일접속 여부 확인 (페이징)
   */
  public static HashMap<String, Object> isPageService(HttpServletRequest request) {

    HashMap<String, Object> param = new HashMap<>();

    String userAgent = request.getHeader("user-agent");
    boolean mobile1 = userAgent.matches(
        ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
    boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");

    int pageRow = 10;

    ArrayList<String> temp = new ArrayList<>();
    // 이미지 페이지 당 8개 표시 temp.add로 추가
    temp.add("/lookie/storyList.do");
    String requestURL = request.getServletPath() == null ? "" : request.getServletPath();

    if (temp.contains(requestURL)) {
      pageRow = 8;
    }

    //모바일 5, 일발 10
    if (mobile1 || mobile2) {
      int mobilePageBlock = 5;
      param.put("pageRow", pageRow);
      param.put("pageBlock", mobilePageBlock);
    } else {
      int pageBlock = 10;
      param.put("pageRow", pageRow);
      param.put("pageBlock", pageBlock);
    }

    return param;
  }

  /*
   * 모바일접속 여부 확인 (베너)
   * 참조 URL : http://mugrammer.tistory.com/151
   */
  public static HashMap<String, Object> isMobileBanner(HttpServletRequest request) {

    HashMap<String, Object> param = new HashMap<>();

    String userAgent = request.getHeader("user-agent");
    boolean mobile1 = userAgent.matches(
        ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
    boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");

    if (mobile1 || mobile2) {
      int mobileBannerWidth = 640;
      int mobileBannerHeight = 600;
      param.put("bannerWidth", mobileBannerWidth);
      param.put("bannerHeight", mobileBannerHeight);
    } else {
      int bannerWidth = 1980;
      int bannerHeight = 580;
      param.put("bannerWidth", bannerWidth);
      param.put("bannerHeight", bannerHeight);
    }
    return param;
  }

  /**
   * Null 값을 빈 값으로 치환화여 반환(String)
   */
  static public String nvl(Object arg) {
    return nvl(arg, "");
  }

  /**
   * 기능설명 : Null 값을 0으로 치환화여 반환(Integer)
   */
  static public int nvlInt(Object arg) {
    return nvl(arg, 0);
  }

  /**
   * 기능설명 : Null 값을 특정 값으로 치환화여 반환(String)
   */
  static public String nvl(Object arg, String ch) {
    if (arg == null) {
      return ch;
    }

    String str = String.valueOf(arg);

    if (str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
      return ch;
    }

    return str;
  }

  /**
   * 기능설명 : Null 값을 특정 값으로 치환화여 반환(Integer)
   */
  static public int nvl(Object arg, int i) {
    if (arg == null) {
      return i;
    }

    int returnValue;

    try {
      returnValue = Integer.parseInt(nvl(arg, "0"));
    } catch (Exception e) {
      return i;
    }

    if (returnValue == 0) {
      returnValue = i;
    }

    return returnValue;
  }

  /**
   * PHP의 rawurlencode 함수를 자바코드화
   */
  public static String rawurlencode(String str) {
    if (checkString(str).length() > 0) {
      try {
        return URLEncoder.encode(str, "UTF-8").replace("*", "%2A").replace("+", "%20")
            .replace("%7E", "~");
      } catch (UnsupportedEncodingException e) {
        return null;
      }
    } else {
      return str;
    }
  }

  /**
   * 파라메터로 넘어온 객체의 Null 혹은 빈값 판단 비어있거나 Null일 경우 true 반환
   */
  public static boolean isEmpty(Object arg) {
    return arg == null || "".equals(String.valueOf(arg).trim());
  }

  /**
   * 휴대폰번호의 마스킹 처리
   */
  public static String getMaskedPhoneNum(String phoneNum) {
    /*
     * 요구되는 휴대폰 번호 포맷
     * 01055557777 또는 0113339999 로 010+네자리+네자리 또는 011~019+세자리+네자리이나
     * 사실 0107770000 과 01188884444 같이 가운데 번호는 3자리 또는 4자리면 돈케어
     */
    String regex = "(01[016789])(\\d{3,4})\\d{4}$";
    Matcher matcher = Pattern.compile(regex).matcher(phoneNum);
    if (matcher.find()) {
      String replaceTarget = matcher.group(2);
      char[] c = new char[replaceTarget.length()];
      Arrays.fill(c, '*');

      return phoneNum.replace(replaceTarget, String.valueOf(c));
    }

    return phoneNum;
  }
}
