package kr.co.happyict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.co.happyict.dao.RestDAO;
import kr.co.happyict.service.RestService;
import kr.co.happyict.vo.BoardManagementRestVO;
import kr.co.happyict.vo.BoardRestVO;

@Service
public class RestServiceImpl implements RestService {

	private final RestDAO restDAO;
	
	private final SqlSession sqlSession;

	public RestServiceImpl(RestDAO restDAO, SqlSession sqlSession) {
		this.restDAO = restDAO;
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BoardRestVO> selectBoardRest(long boardSeq) throws Exception {
		return restDAO.selectBoardList(boardSeq);
	}

	@Override
	public BoardRestVO selectBoard(long boardSeq) throws Exception {
		BoardRestVO boardRestVO = restDAO.selectBoard(boardSeq);
		return boardRestVO;
	}

	@Override
	public List<BoardManagementRestVO> selectBoardManagementList() throws Exception {
		return sqlSession.selectList("RestDAO.selectBoardManagementList");
	}

}
