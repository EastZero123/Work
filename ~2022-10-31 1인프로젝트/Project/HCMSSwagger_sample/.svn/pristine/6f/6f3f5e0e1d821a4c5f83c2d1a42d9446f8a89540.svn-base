package kr.co.happyict.dao;

import java.util.List;
import kr.co.happyict.vo.AccountAuthVO;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.LoginControlVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

  private final SqlSession sqlSession;

  public AccountDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public List<AccountVO> selectAccountList(AccountVO searchAccountVO) {
    return sqlSession.selectList("AccountDAO.selectAccountList", searchAccountVO);
  }

  public int selectAccountCount(AccountVO searchAccountVO) {
    return sqlSession.selectOne("AccountDAO.selectAccountCount", searchAccountVO);
  }

  public AccountVO selectAccount(long accountSeq) {
    AccountVO accountVO = sqlSession.selectOne("AccountDAO.selectAccount", accountSeq);
    accountVO.setAccountAuthList(
        sqlSession.selectList("AccountDAO.selectAccountAuthList", accountSeq));

    return accountVO;
  }

  public void insertAccount(AccountVO accountVO) {
    sqlSession.insert("AccountDAO.insertAccount", accountVO);

    for (AccountAuthVO accountAuthVO : accountVO.getAccountAuthList()) {
      accountAuthVO.setAccountSeq(accountVO.getAccountSeq());
      accountAuthVO.setRegId(accountVO.getRegId());
      sqlSession.insert("AccountDAO.insertAccountAuth", accountAuthVO);
    }
  }

  public void updateAccount(AccountVO accountVO) {
    sqlSession.update("AccountDAO.updateAccount", accountVO);

    for (AccountAuthVO accountAuthVO : accountVO.getAccountAuthList()) {
      accountAuthVO.setAccountSeq(accountVO.getAccountSeq());
      accountAuthVO.setRegId(accountVO.getUpdId());
      sqlSession.insert("AccountDAO.insertAccountAuth", accountAuthVO);
    }
  }

  public void updateAccountUseAjax(AccountVO accountVO) {
    sqlSession.update("AccountDAO.updateAccountUseAjax", accountVO);
  }

  public void deleteAccount(AccountVO accountVO) {
    sqlSession.update("AccountDAO.deleteAccount", accountVO);
    sqlSession.update("AccountDAO.deleteAccountAuth", accountVO);
  }

  public int checkAccountId(String accountId) {
    return sqlSession.selectOne("AccountDAO.checkAccountId", accountId);
  }

  public AccountVO selectLoginInfo(AccountVO accountVO) {
    AccountVO account = sqlSession.selectOne("AccountDAO.selectLoginInfo", accountVO);

    if (account != null) {
      account.setAccountAuthList(
          sqlSession.selectList("AccountDAO.selectAccountAuthList", account.getAccountSeq()));
    }

    return account;
  }

  public LoginControlVO selectLoginSession(AccountVO accountVO) {
    return sqlSession.selectOne("AccountDAO.selectLoginSession", accountVO);
  }

  public void insertLoginSession(LoginControlVO loginControl) {
    sqlSession.insert("AccountDAO.insertLoginSession", loginControl);
  }

  public void updateLoginSession(LoginControlVO loginControl) {
    sqlSession.update("AccountDAO.updateLoginSession", loginControl);
  }

  public AccountVO selectId(String accountId) {
    return sqlSession.selectOne("AccountDAO.selectId", accountId);
  }
}
