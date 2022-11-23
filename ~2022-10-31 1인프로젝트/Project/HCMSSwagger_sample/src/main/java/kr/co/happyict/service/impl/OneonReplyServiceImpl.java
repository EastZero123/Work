package kr.co.happyict.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.happyict.dao.OneonReplyDAO;
import kr.co.happyict.service.OneonReplyService;
import kr.co.happyict.vo.OneonReplyVO;

@Service
public class OneonReplyServiceImpl implements OneonReplyService {

	private final OneonReplyDAO oneonReplyDAO;

	public OneonReplyServiceImpl(OneonReplyDAO oneonReplyDAO) {
		this.oneonReplyDAO = oneonReplyDAO;
	}

	@Override
	public List<OneonReplyVO> selectAll(int bno) throws Exception {
		// TODO Auto-generated method stub
		return oneonReplyDAO.selectAll(bno);
	}

	@Override
	public void create(OneonReplyVO oneonReplyVO) throws Exception {
		// TODO Auto-generated method stub
		oneonReplyDAO.create(oneonReplyVO);
	}

	@Override
	public void update(OneonReplyVO oneonReplyVO) throws Exception {
		// TODO Auto-generated method stub
		oneonReplyDAO.update(oneonReplyVO);
	}

	@Override
	public void delete(int rno) throws Exception {
		// TODO Auto-generated method stub
		oneonReplyDAO.delete(rno);
	}

}
