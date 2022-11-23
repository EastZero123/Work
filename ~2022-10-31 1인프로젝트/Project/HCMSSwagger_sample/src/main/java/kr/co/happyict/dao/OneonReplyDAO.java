package kr.co.happyict.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.happyict.vo.OneonReplyVO;

@Repository
public class OneonReplyDAO {

	private final SqlSession sqlSession;

	public OneonReplyDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<OneonReplyVO> selectAll(int bno) throws Exception {
		return sqlSession.selectList("OneonReplyMapper.selectAll", bno);
	}

	public void create(OneonReplyVO oneonReplyVO) throws Exception {
		sqlSession.insert("OneonReplyMapper.create", oneonReplyVO);
	}

	public void update(OneonReplyVO oneonReplyVO) throws Exception {
		sqlSession.update("OneonReplyMapper.update", oneonReplyVO);
	}

	public void delete(int rno) throws Exception {
		sqlSession.delete("OneonReplyMapper.delete", rno);
	}
}
