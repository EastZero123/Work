package kr.co.happyict.service;

import java.util.List;
import java.util.Map;
import kr.co.happyict.vo.BoardManagementVO;
import kr.co.happyict.vo.BoardVO;
import kr.co.happyict.vo.RestVO;

public interface BoardService {

  Map<String, Object> selectBoardList(BoardVO searchBoardVO);

  BoardVO selectBoard(long boardSeq);

  RestVO selectRestPage(RestVO restVO);

  List<BoardVO> selectRestBoardList(BoardVO boardVO);

  String insertBoard(BoardManagementVO boardManagementVO, BoardVO boardVO);

  String updateBoard(BoardManagementVO boardManagementVO, BoardVO boardVO);

  String deleteBoard(BoardVO boardVO);

  String updateBoardUseAjax(BoardVO boardVO);

  void updateOrderAjax(BoardVO boardVO);

  void updateMainBasic(BoardVO boardVO);

  void updateMainOrderAjax(BoardVO boardVO);

  List<BoardVO> selectBoardMainList(long boardManagementSeq);

  Map<String, Object> selectHistory(BoardVO searchBoardVO);

  int selectBoardCount(BoardVO boardVO);
}
