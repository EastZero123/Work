package kr.co.happyict.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpServletResponse;
import kr.co.happyict.common.Constants;
import kr.co.happyict.vo.BoardAttachVO;
import kr.co.happyict.vo.BoardManagementVO;
import kr.co.happyict.vo.BoardVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HappyFile {

  private static final Logger log = LoggerFactory.getLogger(HappyFile.class);

  private static final Calendar calendar = Calendar.getInstance(
      TimeZone.getTimeZone("Asia/Seoul")); //한국시간 날짜 셋팅

  @Value("${upload.path}")
  private String uploadBasePath;

  private final String uploadLivePath = Constants.UPLOAD_LIVE_PATH;
  private final String uploadTempPath = Constants.UPLOAD_TEMP_PATH;
  private String ajaxResult = Constants.AJAX_RETURN;

  // 파일 다운로드
  @RequestMapping("/{subFolder}/{specFolder}/happyFileDown.do")
  public void happyFileDown(@PathVariable("subFolder") String subFolder,
      @PathVariable("specFolder") String specFolder, @RequestParam("fileName") String fileName,
      @RequestParam(value = "orgFileName") String orgFileName, HttpServletResponse response)
      throws IOException {

    OutputStream outs = response.getOutputStream();

    try {
      // 전달 된 파라미터가 null 인지 체크
      if (fileName == null || orgFileName == null) {
        throw new IOException();
      }

      // 인코딩 우회를 방지하기 위한 디코딩
      String decodeOriginalFileName;
      decodeOriginalFileName = URLDecoder.decode(orgFileName, Charset.defaultCharset().name());
      // 파일 이름 체크 (특수문자 필터링)
      if (decodeOriginalFileName.contains("..")
          || decodeOriginalFileName.contains("/")) {
        throw new IOException();
      }

      //물리적 파일 경로
      String saveFilePath = getStorePath(subFolder + "/" + specFolder, fileName);
      //실제 저장된 파일이름
      orgFileName = new String(orgFileName.getBytes("euc-kr"), StandardCharsets.ISO_8859_1);
      //orgFileName = new String(orgFileName.getBytes("ISO-8859-1"), "euc-kr");

      File file = new File(saveFilePath);

      if (file.exists()) {
        byte[] readByte = new byte[4096];

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + orgFileName);

        BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));

        int read;

        while ((read = fin.read(readByte, 0, 4096)) != -1) {
          outs.write(readByte, 0, read);
        }

        outs.flush();
        outs.close();
        fin.close();
      }
    } catch (IOException e) {
      log.debug("================happyFileDown Error=================");
      log.info("happyFileDown Error: " + e.getMessage());
      log.debug("subFolder= " + subFolder + " specFolder= " + specFolder + " fileName= " + fileName
          + " orgFileName= " + orgFileName);
      e.printStackTrace();
    } finally {
      outs.flush();
      outs.close();
    }
  }

  @RequestMapping("/happyUploadAjax.do")
  public String happyUploadAjax(Model model, MultipartFile file,
      BoardManagementVO boardManagementVO, String uploadCode) {
    try {
      if (boardManagementVO == null) {
        ajaxResult = "boardCodeNull";
        model.addAttribute("ajaxResult", ajaxResult);

        return "jsonView";
      }
      if (boardManagementVO.getBoardCode().equals("")) {
        ajaxResult = "boardCodeFail";
        model.addAttribute("ajaxResult", ajaxResult);

        return "jsonView";
      }

      String spec;
      String[] exts;
      int maxSize;

      if (uploadCode.equals("img")) {
        spec = boardManagementVO.getBoardCode() + "-img";
        exts = boardManagementVO.getThumbimgExt().split(",");
        maxSize = boardManagementVO.getThumbimgSize();
      } else {
        spec = boardManagementVO.getBoardCode() + "-file";
        exts = boardManagementVO.getAttExt().split(",");
        maxSize = boardManagementVO.getAttSize();
      }

      String uploadPath = uploadBasePath + "/" + uploadTempPath + "/" + spec;
      String fileName = Objects.requireNonNull(file.getOriginalFilename()).trim();
      String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
      boolean isFile = false;

      SimpleDateFormat sdf = new SimpleDateFormat();
      sdf.applyPattern("yyyyMMddHHmmss");
      String newtime = sdf.format(calendar.getTime());//년월일시분초
      String rndchars = RandomStringUtils.randomAlphanumeric(4); //영문4자리 랜덤
      String newFileName = newtime + rndchars + "." + ext; //파일명조합

      sdf.applyPattern("yyyy");
      String year = sdf.format(calendar.getTime());
      sdf.applyPattern("MM");
      String mon = sdf.format(calendar.getTime());
      //실파일
      String realPath = uploadPath + "/" + year + File.separator + mon + File.separator;

      long fileSize = file.getSize();
      System.out.println("fileSize============" + fileSize);

      if (fileSize > 0 && fileSize < maxSize) {
        for (String arr : exts) {
          if (arr.equalsIgnoreCase(ext)) {
            System.out.println("exts============" + ext);
            isFile = true;
          }
        }

        if (isFile) {
          File realfile = new File(realPath);

          if (!realfile.exists()) {
            realfile.mkdirs();
          }

          // 원본 파일 전송 후
          file.transferTo(new File(realPath + newFileName));

          if (uploadCode.equals("static/img")) {
            // 동일한 파일명으로 썸네일 이미지 생성. (덮어쓰기)
            Thumbnails.of(new File(realPath + newFileName)).size(640, 360)
                .toFile(new File(realPath + newFileName));
          }

          model.addAttribute("orgFileName", fileName);
          model.addAttribute("fileName", newFileName);
          model.addAttribute("fileExt", ext);
          model.addAttribute("fileSize", fileSize);
          model.addAttribute("specId", spec);
        } else {
          System.out.println("exts2============" + ext);
          ajaxResult = "extFail";
        }
      } else {
        System.out.println("fileSize2============" + fileSize);
        ajaxResult = "sizeFail";
      }
    } catch (Exception e) {
      log.info("happyUploadAjax Error: " + e.getMessage());
      e.printStackTrace();
    }

    model.addAttribute("ajaxResult", ajaxResult);

    return "jsonView";
  }

  @RequestMapping("/editFileUpload.do")
  public @ResponseBody
  Map<String, Object> editFileUpload(@ModelAttribute("boardAttachVO") BoardAttachVO boardAttachVO,
      @ModelAttribute("boardManagementVO") BoardManagementVO boardManagementVO) {
    Map<String, Object> result = new HashMap<>();

    try {
      if (boardManagementVO != null) {
        String uploadPath = uploadBasePath + File.separator + uploadTempPath + File.separator
            + boardManagementVO.getBoardCode() + "-edit";
        String fileName = Objects.requireNonNull(boardAttachVO.getUpload().getOriginalFilename())
            .trim();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1)
            .toLowerCase();
        String exts = boardManagementVO.getEditExt().replace(",", "|");
        String returnFileName;

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        String newtime = sdf.format(calendar.getTime());//년월일시분초
        String rndchars = RandomStringUtils.randomAlphanumeric(4); //영문4자리 랜덤
        String newFileName = newtime + rndchars + "." + ext; //파일명조합

        sdf.applyPattern("yyyy");
        String year = sdf.format(calendar.getTime());
        sdf.applyPattern("MM");
        String mon = sdf.format(calendar.getTime());

        //임시파일
        String tempPath =
            uploadPath + File.separator + year + File.separator + mon + File.separator;

        long fileSize = boardAttachVO.getUpload().getSize();

        if (fileSize > 0 && fileSize < boardManagementVO.getEditSize()) {
          if (ext.matches(exts)) {
            File tempfile = new File(tempPath);
            if (!tempfile.exists()) {
              tempfile.mkdirs();
            }

            boardAttachVO.getUpload().transferTo(new File(tempPath + newFileName));

            returnFileName =
                "/" + uploadTempPath + "/" + boardManagementVO.getBoardCode() + "-edit/"
                    + "happyFileView.do?fileName=" + newFileName;

            // 이미지 url 입력
            result.put("url", returnFileName);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.info("fileUpload Error: " + e.getMessage());
    }

    return result;
  }

  /**
   * 파일크기 BYTE로 받아서 적절한 STRING 리턴
   */
  public String getFileSizeText(String str_nBytes) {
    long nBytes = Long.parseLong(str_nBytes);
    long n = 1024;

    if (nBytes < n) {
      return nBytes + "B";
    }

    nBytes /= n;

    if (nBytes < n) {
      return Math.round(nBytes * 100) / 100 + "KB";
    }

    nBytes /= n;

    if (nBytes < n) {
      return Math.round(nBytes * 100) / 100 + "MB";
    }

    nBytes /= n;

    if (nBytes < n) {
      return Math.round(nBytes * 100) / 100 + "GB";
    }

    nBytes /= n;

    return Math.round(nBytes * 100) / 100 + "TB";
  }

  @RequestMapping("/{subFolder}/{specFolder}/happyFileView.do")
  public String happyFileView(@PathVariable("subFolder") String subFolder,
      @PathVariable("specFolder") String specFolder, @RequestParam("fileName") String fileName,
      HttpServletResponse response) {
    //파일 이름에서 폴더 경로 가져와 저장된 폴더 지정
    String storePath = getStorePath(subFolder + "/" + specFolder, fileName);

    //실파일 검사
    if (!new File(storePath).exists()) {
      log.warn("file NOT exists");

      return null;
    }

    InputStream in;
    OutputStream out;

    try {
      File f = new File(storePath);
      long fileSize = f.length();

      if (fileSize > Integer.MAX_VALUE) { //2GB
        log.error("too large file ( Integer.MAX_VALUE )");
      }

      in = new FileInputStream(storePath);
      response.setContentLength((int) fileSize);
      out = response.getOutputStream();

      fileCopy(in, out, false);
      out.flush();
      in.close();
    } catch (IOException e) {
      log.debug("================happyFileView Error=================");
      log.info("happyFileView Error: " + e.getMessage());
      log.debug(
          "subFolder= " + subFolder + " specFolder= " + specFolder + " fileName= " + fileName);
    }

    return null;
  }

  public void happyFileCopy(String spec, String fileName) throws IOException {
    if (!spec.equals("")) {
      String tempPath = uploadBasePath + "/" + Constants.UPLOAD_TEMP_PATH + "/" + spec + "/";
      String livePath = uploadBasePath + "/" + Constants.UPLOAD_LIVE_PATH + "/" + spec + "/";

      String year = fileName.substring(0, 4);
      String month = fileName.substring(4, 6);

      String storePath = year + File.separator + month + File.separator;
      File fileDir = new File(livePath + storePath);

      if (!fileDir.exists()) {
        fileDir.mkdirs();
      }

      storePath = storePath.replaceAll("\\.\\./", ""); // ../
      storePath = storePath.replaceAll("\\.\\.\\\\", ""); // ..\

      FileInputStream inputStream = new FileInputStream(tempPath + storePath + fileName.trim());
      FileOutputStream outputStream = new FileOutputStream(livePath + storePath + fileName.trim());

      FileChannel fcin = inputStream.getChannel();
      FileChannel fcout = outputStream.getChannel();

      long size = fcin.size();
      fcin.transferTo(0, size, fcout);

      fcout.close();
      fcin.close();

      outputStream.close();
      inputStream.close();
    }
  }

  public void happyFileDelete(String spec, String fileName) {
    String livePath = uploadBasePath + "/" + Constants.UPLOAD_LIVE_PATH + "/" + spec + "/";

    String year = fileName.substring(0, 4);
    String month = fileName.substring(4, 6);

    String storePath = year + File.separator + month + File.separator;
    File fileDir = new File(livePath + storePath);

    if (!fileDir.exists()) {
      fileDir.mkdirs();
    }

    storePath = storePath.replaceAll("\\.\\./", ""); // ../
    storePath = storePath.replaceAll("\\.\\.\\\\", ""); // ..\

    File orgFile = new File(livePath + storePath + fileName.trim());

    if (!orgFile.exists() || !orgFile.isFile()) {
      log.warn("Invalid file. path=" + orgFile);

      return;
    }

    if (!orgFile.delete()) {
      log.error("could NOT delete. path=" + orgFile);
    }
  }

  public void fileCopy(InputStream in, OutputStream out, boolean withBufferedStreamSupport)
      throws IOException {
    if (withBufferedStreamSupport) {
      in = new BufferedInputStream(in);
      out = new BufferedOutputStream(out);
    }

    int readBuffer;
    byte[] buffer = new byte[512];

    while ((readBuffer = in.read(buffer)) != -1) {
      out.write(buffer, 0, readBuffer);
    }

    while (true) {
      int b = in.read();

      if (b != -1) {
        out.write(b);
      } else {
        break;
      }
    }
  }

  //파일 이름에서 폴더명 가져오기
  public String getStorePath(String subFolder, String fileName) {

    String storePath = "";
    //실재 패스로 변환.
    try {
      storePath = new File(uploadBasePath).getCanonicalPath();
    } catch (IOException e) {
      log.error("NOT storePath = " + storePath);
    }

    String year = fileName.substring(0, 4);
    String month = fileName.substring(4, 6);

    storePath +=
        File.separator + subFolder + File.separator + year + File.separator + month + File.separator
            + fileName.trim();

    storePath = storePath.replaceAll("\\.\\./", ""); // ../
    storePath = storePath.replaceAll("\\.\\.\\\\", ""); // ..\

    return storePath;
  }

  /**
   * 에디터 파일 이동 및 삭제 처리
   *
   * @param type : insert, update, delete
   */
  public void editorFileService(BoardVO bVO, String type) {
    String[] newFile = bVO.getNewImgFileList();
    String[] orgFile = bVO.getOrgImgFileList();
    String spec = bVO.getEditorSpecId();

    try {
      if ("insert".equals(type)) {
        if (newFile != null) {
          List<String> newFileList = new ArrayList<>(Arrays.asList(newFile));

          for (String fileName : newFileList) {
            happyFileCopy(spec, fileName);
          }
        }
      } else if ("update".equals(type)) {
        if (orgFile != null && newFile == null) {      //전체 이미지 삭제
          List<String> orgFileList = new ArrayList<>(Arrays.asList(orgFile));

          for (String fileName : orgFileList) {
            happyFileDelete(spec, fileName);
          }
        } else if (orgFile == null && newFile != null) {    //전체 등록
          List<String> newFileList = new ArrayList<>(Arrays.asList(newFile));

          for (String fileName : newFileList) {
            happyFileCopy(spec, fileName);
          }
        } else if (orgFile != null) {    //변경여부 확인후 처리
          List<String> newFileList = new ArrayList<>(Arrays.asList(newFile));
          List<String> orgFileList = new ArrayList<>(Arrays.asList(orgFile));
          List<String> delFileList = new ArrayList<>(Arrays.asList(orgFile));

          delFileList.removeAll(newFileList);

          for (String fileName : delFileList) {
            happyFileDelete(spec, fileName);
          }

          newFileList.removeAll(orgFileList);

          for (String fileName : newFileList) {
            happyFileCopy(spec, fileName);
          }
        }
      } else if ("delete".equals(type)) {
        if (orgFile != null) {      //전체 이미지 삭제
          List<String> orgFileList = new ArrayList<>(Arrays.asList(orgFile));
          for (String fileName : orgFileList) {
            happyFileDelete(spec, fileName);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void decompress(String zipFileName, String directory) throws Throwable {
    File zipFile = new File(zipFileName);
    ZipEntry zipentry;

    try (FileInputStream fis = new FileInputStream(
        zipFile); ZipInputStream zis = new ZipInputStream(fis)) {
      //파일 스트림
      //Zip 파일 스트림
      //entry가 없을때까지 뽑기
      while ((zipentry = zis.getNextEntry()) != null) {
        String filename = zipentry.getName();
        File file = new File(directory, filename);
        //entiry가 폴더면 폴더 생성
        if (zipentry.isDirectory()) {
          file.mkdirs();
        } else {
          //파일이면 파일 만들기
          createFile(file, zis);
        }
      }
    }
  }

  /**
   * 파일 만들기 메소드
   *
   * @param file 파일
   * @param zis  Zip스트림
   */
  private static void createFile(File file, ZipInputStream zis) throws Throwable {
    //디렉토리 확인
    File parentDir = new File(file.getParent());
    //디렉토리가 없으면 생성하자
    if (!parentDir.exists()) {
      parentDir.mkdirs();
    }
    //파일 스트림 선언
    try (FileOutputStream fos = new FileOutputStream(file)) {
      byte[] buffer = new byte[256];
      int size;
      //Zip스트림으로부터 byte뽑아내기
      while ((size = zis.read(buffer)) > 0) {
        //byte로 파일 만들기
        fos.write(buffer, 0, size);
      }
    }
  }
}
