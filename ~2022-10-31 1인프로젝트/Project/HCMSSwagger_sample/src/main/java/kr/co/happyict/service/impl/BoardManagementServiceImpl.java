package kr.co.happyict.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.co.happyict.common.Constants;
import kr.co.happyict.dao.BoardManagementDAO;
import kr.co.happyict.service.BoardManagementService;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.BoardManagementVO;
import org.springframework.stereotype.Service;

@Service
public class BoardManagementServiceImpl implements BoardManagementService {

  private final BoardManagementDAO boardManagementDAO;

  private final HttpServletRequest request;

  private final String ajaxReturn = Constants.AJAX_RETURN;

  public BoardManagementServiceImpl(
      BoardManagementDAO boardManagementDAO, HttpServletRequest request) {
    this.boardManagementDAO = boardManagementDAO;
    this.request = request;
  }

  @Override
  public Map<String, Object> selectBoardManagementList(BoardManagementVO boardManagementVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", boardManagementDAO.selectBoardManagementCount(boardManagementVO));
    map.put("list", boardManagementDAO.selectBoardManagementList(boardManagementVO));

    return map;
  }

  @Override
  public BoardManagementVO selectBoardManagement(String boardCode) {
    return boardManagementDAO.selectBoardManagement(boardCode);
  }

  @Override
  public BoardManagementVO selectBoardManagementSeq(long boardManagementSeq) {
    return boardManagementDAO.selectBoardManagementSeq(boardManagementSeq);
  }

  @Override
  public String insertBoardManagement(BoardManagementVO boardManagementVO) {
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (!boardManagementVO.getBoardName().equals("")) {
      boardManagementVO.setRegId(accountLogin.getAccountId());
      boardManagementDAO.insertBoardManagement(boardManagementVO);
    }

    return ajaxReturn;
  }

  @Override
  public String updateBoardManagement(BoardManagementVO boardManagementVO) {
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (boardManagementVO.getBoardManagementSeq() > 0) {
      boardManagementVO.setUpdId(accountLogin.getAccountId());
      boardManagementDAO.updateBoardManagement(boardManagementVO);
    }

    return ajaxReturn;
  }
/*
	@Override
	public String deleteBoardManagement(long boardManagementSeq) {
		AccountVO accountLogin = (AccountVO)request.getSession().getAttribute("accountLogin");

		if(boardManagementSeq > 0){
			boardManagementDAO.deleteBoardManagement(boardManagementVO);
		}

	}
*/
}
