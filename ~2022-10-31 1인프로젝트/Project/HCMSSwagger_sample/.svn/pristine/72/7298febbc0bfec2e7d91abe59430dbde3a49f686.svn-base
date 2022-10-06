package kr.co.happyict.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.co.happyict.common.Constants;
import kr.co.happyict.dao.CodeDAO;
import kr.co.happyict.service.CodeService;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.CodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(CodeServiceImpl.class);

  private final CodeDAO codeDAO;

  private final HttpServletRequest request;

  private String ajaxResult = Constants.AJAX_RETURN;

  public CodeServiceImpl(CodeDAO codeDAO,
      HttpServletRequest request) {
    this.codeDAO = codeDAO;
    this.request = request;
  }

  @Override
  public List<CodeVO> selectCodeList(String masterCode) {
    return codeDAO.selectCodeList(masterCode);
  }

  @Override
  public Map<String, Object> selectCodeList(CodeVO searchCodeVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", codeDAO.selectCodeCount(searchCodeVO));
    map.put("list", codeDAO.selectCodeList(searchCodeVO));

    return map;
  }

  @Override
  public CodeVO selectCode(long codeSeq) {
    return codeDAO.selectCode(codeSeq);
  }

  @Override
  public String insertCode(CodeVO codeVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    codeVO.setDetailName(codeVO.getDetailCode());
    codeVO.setDetailTitle(codeVO.getDetailCode());
    codeVO.setRegId(accountLogin.getAccountId());
    codeVO.setRegName(accountLogin.getAccountName());

    codeDAO.insertCode(codeVO);

    return ajaxResult;
  }

  @Override
  public String updateCode(CodeVO codeVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    codeVO.setDetailName(codeVO.getDetailCode());
    codeVO.setDetailTitle(codeVO.getDetailCode());
    codeVO.setUpdId(accountLogin.getAccountId());

    codeDAO.updateCode(codeVO);

    return ajaxResult;
  }

  @Override
  public String updateCodeUseAjax(CodeVO codeVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    codeVO.setUpdId(accountLogin.getAccountId());

    codeDAO.updateCodeUseAjax(codeVO);

    return ajaxResult;
  }

  @Override
  public String deleteCode(CodeVO codeVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    codeVO.setUpdId(accountLogin.getAccountId());

    codeDAO.deleteCode(codeVO);

    return ajaxResult;
  }

  @Override
  public List<CodeVO> selectUseCode(CodeVO codeVO) {
    return codeDAO.selectUseCode(codeVO);
  }
}
