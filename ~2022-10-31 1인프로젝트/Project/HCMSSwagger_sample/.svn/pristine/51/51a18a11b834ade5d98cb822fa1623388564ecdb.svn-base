package kr.co.happyict.service;

import java.util.Map;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.LoginControlVO;

public interface AccountService {

  Map<String, Object> selectAccountList(AccountVO searchAccountVO);

  AccountVO selectAccount(long accountSeq);

  String insertAccount(AccountVO accountVO);

  String updateAccount(AccountVO accountVO);

  String updateAccountUseAjax(AccountVO accountVO);

  String deleteAccount(long accountSeq);

  String checkAccountId(String accountId);

  AccountVO selectLoginInfo(AccountVO accountVO);

  LoginControlVO selectLoginSession(AccountVO accountVO);

  void insertLoginSession(LoginControlVO loginReward);

  void updateLoginSession(LoginControlVO loginControl);

  AccountVO selectId(String accountId);
}
