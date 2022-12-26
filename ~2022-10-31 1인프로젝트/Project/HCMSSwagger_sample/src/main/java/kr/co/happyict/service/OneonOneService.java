package kr.co.happyict.service;

import java.util.List;

import kr.co.happyict.vo.OneonOneVO;

public interface OneonOneService {

	public List<OneonOneVO> selectAll() throws Exception;

	public void register(OneonOneVO oneonOne) throws Exception;

	public List<OneonOneVO> readOne(int bno) throws Exception;
	
	public void update(OneonOneVO oneonOneVO) throws Exception;
	
	public void delete(int bno) throws Exception;


}
