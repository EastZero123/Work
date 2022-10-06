package kr.co.happyict.service.impl;

import kr.co.happyict.dao.CommonDAO;
import kr.co.happyict.service.CommonService;
import kr.co.happyict.vo.AccessHistoryVO;
import kr.co.happyict.vo.AccessRecordMasterVO;
import kr.co.happyict.vo.LogVO;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
	
	private final CommonDAO commonDAO;

	public CommonServiceImpl(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	public int accessRecordMaster(AccessRecordMasterVO accessRecordMaster) throws Exception {
		return commonDAO.accessRecordMaster(accessRecordMaster);
	}

	@Override
	public int accessHistory(AccessHistoryVO accessHistory) {
		return commonDAO.accessHistory(accessHistory);
	}
	
	@Override
	public void log(LogVO log) throws Exception {
    commonDAO.log(log);
  }
}
