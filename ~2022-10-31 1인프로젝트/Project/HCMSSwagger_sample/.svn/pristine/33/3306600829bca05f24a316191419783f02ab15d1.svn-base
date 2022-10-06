package kr.co.happyict.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DataEncryption {

  private static final String ENC_KEY = "SHA-256";

  private static final String SALT_KEY = "hAppYIct!@UseR#$";

  public DataEncryption() {
  }

  /**
   * 단방향 암호화
   *
   * @param data 암호화 할 데이터
   * @return 암호화한 문자열
   */
  public static String getDataEncryption(String data) {
    try {
      byte[] bSource = (data + SALT_KEY).getBytes();

      MessageDigest messageDigest = MessageDigest.getInstance(ENC_KEY);
      messageDigest.reset();
      messageDigest.update(bSource);

      byte[] byteData = messageDigest.digest();
      StringBuilder buffer = new StringBuilder();

      for (byte byteDatum : byteData) {
        buffer.append(Integer.toString((byteDatum & 0xFF) + 256, 16).substring(1));
      }

      return buffer.toString();
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
  }
}
