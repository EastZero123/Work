package kr.co.happyict.service;

import java.util.List;
import java.util.Map;
import kr.co.happyict.vo.CodeVO;

public interface CodeService {

  List<CodeVO> selectCodeList(String masterCode);

  Map<String, Object> selectCodeList(CodeVO searchCodeVO);

  CodeVO selectCode(long codeSeq);

  String insertCode(CodeVO codeVO);

  String updateCode(CodeVO codeVO);

  String updateCodeUseAjax(CodeVO codeVO);

  String deleteCode(CodeVO codeVO);

  List<CodeVO> selectUseCode(CodeVO codeVO);
}
