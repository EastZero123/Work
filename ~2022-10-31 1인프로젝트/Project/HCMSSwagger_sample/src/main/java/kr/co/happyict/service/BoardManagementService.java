package kr.co.happyict.service;

import java.util.Map;
import kr.co.happyict.vo.BoardManagementVO;

public interface BoardManagementService {

  Map<String, Object> selectBoardManagementList(BoardManagementVO boardManagementVO);

  BoardManagementVO selectBoardManagement(String boardCode);

  BoardManagementVO selectBoardManagementSeq(long boardManagementSeq);

  String insertBoardManagement(BoardManagementVO boardManagementVO);

  String updateBoardManagement(BoardManagementVO boardManagementVO);
  /*public String deleteBoardManagement(long boardManagementSeq);*/
}
