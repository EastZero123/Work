package kr.co.happyict.dao;

import java.util.List;
import kr.co.happyict.vo.BoardManagementVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardManagementDAO {

  private final SqlSession sqlSession;

  public BoardManagementDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public List<BoardManagementVO> selectBoardManagementList(BoardManagementVO boardManagementVO) {
    return sqlSession.selectList("BoardManagementDAO.selectBoardManagementList", boardManagementVO);
  }

  public int selectBoardManagementCount(BoardManagementVO boardManagementVO) {
    return sqlSession.selectOne("BoardManagementDAO.selectBoardManagementCount", boardManagementVO);
  }

  public BoardManagementVO selectBoardManagement(String boardCode) {
    return sqlSession.selectOne("BoardManagementDAO.selectBoardManagement", boardCode);
  }

  public BoardManagementVO selectBoardManagementSeq(long boardManagementSeq) {
    return sqlSession.selectOne("BoardManagementDAO.selectBoardManagementSeq", boardManagementSeq);
  }

  public void insertBoardManagement(BoardManagementVO boardManagementVO) {
		sqlSession.insert("BoardManagementDAO.insertBoardManagement", boardManagementVO);
	}

  public void updateBoardManagement(BoardManagementVO boardManagementVO) {
    sqlSession.update("BoardManagementDAO.updateBoardManagement", boardManagementVO);
  }

  public int deleteBoardManagement(BoardManagementVO boardManagementVO) {
    return sqlSession.update("BoardManagementDAO.deleteBoardManagement", boardManagementVO);
  }
}
