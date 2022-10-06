package kr.co.happyict.dao;

import java.util.List;
import kr.co.happyict.vo.CodeVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDAO {

  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(CodeDAO.class);

  private final SqlSession sqlSession;

  public CodeDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public List<CodeVO> selectCodeList(String masterCode) {
    return sqlSession.selectList("CodeDAO.selectCodeMasterList", masterCode);
  }

  public List<CodeVO> selectCodeList(CodeVO searchCodeVO) {
    return sqlSession.selectList("CodeDAO.selectCodeList", searchCodeVO);
  }

  public int selectCodeCount(CodeVO searchCodeVO) {
    return sqlSession.selectOne("CodeDAO.selectCodeCount", searchCodeVO);
  }

  public CodeVO selectCode(long codeSeq) {
    return sqlSession.selectOne("CodeDAO.selectCode", codeSeq);
  }

  public void insertCode(CodeVO codeVO) {
    sqlSession.insert("CodeDAO.insertCode", codeVO);
  }

  public void updateCode(CodeVO codeVO) {
    sqlSession.update("CodeDAO.updateCode", codeVO);
  }

  public int updateBoardHashtag(CodeVO codeVO) {
    return sqlSession.update("CodeDAO.updateBoardHashtag", codeVO);
  }

  public void updateCodeUseAjax(CodeVO codeVO) {
    sqlSession.update("CodeDAO.updateCodeUseAjax", codeVO);
  }

  public void deleteCode(CodeVO codeVO) {
    sqlSession.update("CodeDAO.deleteCode", codeVO);
  }

  public List<CodeVO> selectUseCode(CodeVO codeVO) {
    return sqlSession.selectList("CodeDAO.selectUseCode", codeVO);
  }
}
