package kr.co.happyict.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.happyict.vo.BoardManagementRestVO;
import kr.co.happyict.vo.BoardRestVO;

@Repository
public class RestDAO {

	private final SqlSession sqlSession;

	public RestDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<BoardRestVO> selectBoardList(long boardSeq) {
		return sqlSession.selectList("RestDAO.selectBoardRest", boardSeq);
	}

	public BoardRestVO selectBoard(long boardSeq) {
		return sqlSession.selectOne("RestDAO.selectBoard", boardSeq);
	}

	public List<BoardManagementRestVO> selectBoardManagementList() {
		return sqlSession.selectList("RestDAO.selectBoardManagementList");
	}

}
