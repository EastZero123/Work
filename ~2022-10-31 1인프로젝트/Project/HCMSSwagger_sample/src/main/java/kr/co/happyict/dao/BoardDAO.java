package kr.co.happyict.dao;

import java.util.List;
import kr.co.happyict.vo.AccessHistoryVO;
import kr.co.happyict.vo.BoardAttachVO;
import kr.co.happyict.vo.BoardVO;
import kr.co.happyict.vo.RestVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

  private final SqlSession sqlSession;

  public BoardDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public BoardVO selectBoard(long boardSeq) {
    return sqlSession.selectOne("BoardDAO.selectBoard", boardSeq);
  }

  public List<BoardVO> selectBoardList(BoardVO searchBoardVO) {
    return sqlSession.selectList("BoardDAO.selectBoardList", searchBoardVO);
  }

  public RestVO selectRestPage(RestVO title) {
    return sqlSession.selectOne("BoardDAO.selectRestPage", title);
  }

  public List<BoardVO> selectRestBoardList(BoardVO boardVO) {
    return sqlSession.selectList("BoardDAO.selectRestBoardList", boardVO);
  }

  public int selectBoardCount(BoardVO searchBoardVO) {
    return sqlSession.selectOne("BoardDAO.selectBoardCount", searchBoardVO);
  }

  public int selectBoardUseCount(BoardVO boardVO) {
    return sqlSession.selectOne("BoardDAO.selectBoardUseCount", boardVO);
  }

  public void insertBoard(BoardVO boardVO) {
    sqlSession.insert("BoardDAO.insertBoard", boardVO);
  }

  public void updateBoard(BoardVO boardVO) {
    sqlSession.update("BoardDAO.updateBoard", boardVO);
  }

  public void deleteBoard(BoardVO boardVO) {
    sqlSession.update("BoardDAO.deleteBoard", boardVO);
  }

  public List<BoardAttachVO> selectBoardAttachList(long boardSeq) {
    return sqlSession.selectList("BoardDAO.selectBoardAttachList", boardSeq);
  }

  public void insertBoardAttach(BoardAttachVO boardAttachVO) {
    sqlSession.insert("BoardDAO.insertBoardAttach", boardAttachVO);
  }

  public List<BoardVO> selectDelBoardThumImg(BoardVO boardVO) {
    return sqlSession.selectList("BoardDAO.selectDelBoardThumImg", boardVO);
  }

  public void deleteBoardAttach(BoardAttachVO boardAttachVO) {
    sqlSession.update("BoardDAO.deleteBoardAttach", boardAttachVO);
  }

  public List<BoardAttachVO> selectDeleteBoardAttach(BoardVO boardVO) {
    return sqlSession.selectList("BoardDAO.selectDeleteBoardAttach", boardVO);
  }

  public void updateBoardUseAjax(BoardVO boardVO) {
    sqlSession.update("BoardDAO.updateBoardUseAjax", boardVO);
  }

  public void updateOrderAjax(BoardVO boardVO) {
    sqlSession.update("BoardDAO.updateOrderAjax", boardVO);
  }

  public void updateMainBasic(BoardVO boardVO) {
    sqlSession.update("BoardDAO.updateMainBasic", boardVO);

  }

  public void updateMainOrderAjax(BoardVO boardVO) {
    sqlSession.update("BoardDAO.updateMainOrderAjax", boardVO);
  }

  public List<BoardVO> selectBoardMainList(long boardManagementSeq) {
    return sqlSession.selectList("BoardDAO.selectBoardMainList", boardManagementSeq);
  }

  public int selectHistoryCount(BoardVO searchBoardVO) {
    return sqlSession.selectOne("BoardDAO.selectHistoryCount", searchBoardVO);
  }

  public List<AccessHistoryVO> selectHistory(BoardVO searchBoardVO) {
    return sqlSession.selectList("BoardDAO.selectHistory", searchBoardVO);
  }
}
