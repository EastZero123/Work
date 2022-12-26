package kr.co.happyict.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.co.happyict.dao.OneonOneDAO;
import kr.co.happyict.service.OneonOneService;
import kr.co.happyict.vo.OneonOneVO;

@Service
public class OneonOneServiceImpl implements OneonOneService {

	private final OneonOneDAO oneonOneDAO;

	private final SqlSession sqlSession;

	public OneonOneServiceImpl(OneonOneDAO oneonOneDAO, SqlSession sqlSession) {
		this.oneonOneDAO = oneonOneDAO;
		this.sqlSession = sqlSession;
	}

	@Override
	public List<OneonOneVO> selectAll() throws Exception {
		return oneonOneDAO.selectAll();
	}

	@Override
	public void register(OneonOneVO oneonOne) {

		oneonOneDAO.register(oneonOne);

	}
	
	@Override
	public void update(OneonOneVO oneonOneVO) throws Exception {
		// TODO Auto-generated method stub
		oneonOneDAO.update(oneonOneVO);
	}

	@Override
	public List<OneonOneVO> readOne(int bno) throws Exception {
		// TODO Auto-generated method stub
		return oneonOneDAO.readOne(bno);
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		oneonOneDAO.delete(bno);
	}

	

}
