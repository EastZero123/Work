package kr.co.happyict.service;

import java.util.List;
import java.util.Map;

import kr.co.happyict.vo.BoardManagementRestVO;
import kr.co.happyict.vo.BoardRestVO;

public interface RestService {

	List<BoardRestVO> selectBoardRest(long boardSeq) throws Exception;

	BoardRestVO selectBoard(long boardSeq) throws Exception;

	public List<BoardManagementRestVO> selectBoardManagementList() throws Exception;

}
