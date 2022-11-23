package kr.co.happyict.service;

import java.util.List;

import kr.co.happyict.vo.OneonReplyVO;

public interface OneonReplyService {

	List<OneonReplyVO> selectAll(int bno) throws Exception;

	void create(OneonReplyVO oneonReplyVO) throws Exception;

	void update(OneonReplyVO oneonReplyVO) throws Exception;

	void delete(int rno) throws Exception;
}
