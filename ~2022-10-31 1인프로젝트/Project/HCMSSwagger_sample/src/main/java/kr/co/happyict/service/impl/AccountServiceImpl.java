package kr.co.happyict.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.co.happyict.common.Constants;
import kr.co.happyict.dao.AccountDAO;
import kr.co.happyict.service.AccountService;
import kr.co.happyict.util.DataEncryption;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.LoginControlVO;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountDAO accountDAO;

  private final HttpServletRequest request;

  private String ajaxResult = Constants.AJAX_RETURN;

  public AccountServiceImpl(AccountDAO accountDAO,
      HttpServletRequest request) {
    this.accountDAO = accountDAO;
    this.request = request;
  }

  @Override
  public Map<String, Object> selectAccountList(AccountVO searchAccountVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", accountDAO.selectAccountCount(searchAccountVO));
    map.put("list", accountDAO.selectAccountList(searchAccountVO));

    return map;
  }

  @Override
  public AccountVO selectAccount(long accountSeq) {
    return accountDAO.selectAccount(accountSeq);
  }

  @Override
  public String insertAccount(AccountVO accountVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    accountVO.setAccountPassword(DataEncryption.getDataEncryption(accountVO.getAccountPassword()));
    accountVO.setRegId(accountLogin.getAccountId());

    accountDAO.insertAccount(accountVO);

    return ajaxResult;
  }

  @Override
  public String updateAccount(AccountVO accountVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (accountVO.getAccountSeq() > 0) {
      accountVO.setAccountPassword(
          DataEncryption.getDataEncryption(accountVO.getAccountPassword()));
      accountVO.setUpdId(accountLogin.getAccountId());

      accountDAO.updateAccount(accountVO);

      if (accountLogin.getAccountSeq() == accountVO.getAccountSeq()) {
        accountVO.setAccountId(accountLogin.getAccountId());
        accountLogin = accountDAO.selectLoginInfo(accountVO);
        request.getSession().setAttribute("accountLogin", accountLogin);
      }
    }

    return ajaxResult;
  }

  @Override
  public String updateAccountUseAjax(AccountVO accountVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (accountVO.getAccountSeq() > 0) {
      accountVO.setUpdId(accountLogin.getAccountId());

      accountDAO.updateAccountUseAjax(accountVO);
    }

    return ajaxResult;
  }

  @Override
  public String deleteAccount(long accountSeq) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (accountSeq > 0) {
      AccountVO accountVO = new AccountVO();
      accountVO.setAccountSeq(accountSeq);
      accountVO.setUpdId(accountLogin.getAccountId());

      accountDAO.deleteAccount(accountVO);
    }

    return ajaxResult;
  }

  @Override
  public String checkAccountId(String accountId) {
    ajaxResult = Constants.AJAX_RETURN;
    if (!accountId.equals("")) {
      if (accountDAO.checkAccountId(accountId) > 0) {
        ajaxResult = Constants.AJAX_RETURN_FAIL;
      }
    }

    return ajaxResult;
  }

  @Override
  public AccountVO selectLoginInfo(AccountVO accountVO) {
    return accountDAO.selectLoginInfo(accountVO);
  }

  @Override
  public LoginControlVO selectLoginSession(AccountVO accountVO) {
    return accountDAO.selectLoginSession(accountVO);
  }

  @Override
  public void insertLoginSession(LoginControlVO loginControl) {
    accountDAO.insertLoginSession(loginControl);
  }

  @Override
  public void updateLoginSession(LoginControlVO loginControl) {
    accountDAO.updateLoginSession(loginControl);
  }

  @Override
  public AccountVO selectId(String accountId) {
    return accountDAO.selectId(accountId);
  }
}
