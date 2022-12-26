package kr.co.happyict.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.happyict.vo.OneonOneVO;

@Repository
public class OneonOneDAO {

	private final SqlSession sqlSession;

	public OneonOneDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<OneonOneVO> selectAll() {
		return sqlSession.selectList("OneonOneDAO.selectAll");
	}

	public void register(OneonOneVO oneonOneVO) {
		sqlSession.insert("OneonOneDAO.register", oneonOneVO);
	}

	public List<OneonOneVO> readOne(int bno) throws Exception {
		return sqlSession.selectList("OneonOneDAO.readOne", bno);
	}

	public void update(OneonOneVO oneonOneVO) throws Exception {
		sqlSession.update("OneonOneDAO.update", oneonOneVO);
	}

	public void delete(int bno) throws Exception {
		sqlSession.delete("OneonOneDAO.delete", bno);
	}

}
