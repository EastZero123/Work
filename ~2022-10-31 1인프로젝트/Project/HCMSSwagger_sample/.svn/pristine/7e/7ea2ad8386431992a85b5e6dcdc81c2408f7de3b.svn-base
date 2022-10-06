package kr.co.happyict.dao;

import kr.co.happyict.vo.AccessHistoryVO;
import kr.co.happyict.vo.AccessRecordMasterVO;
import kr.co.happyict.vo.LogVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {

  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(CommonDAO.class);

  private final SqlSession sqlSession;

  public CommonDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public int accessRecordMaster(AccessRecordMasterVO accessRecordMaster) {
    return sqlSession.insert("CommonDAO.insertAccessRecordMaster", accessRecordMaster);
  }

  public int accessHistory(AccessHistoryVO accessHistory) {
    return sqlSession.insert("CommonDAO.insertAccessHistory", accessHistory);
  }

  public void log(LogVO log) {
    sqlSession.insert("CommonDAO.log", log);
  }
}
