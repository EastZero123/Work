package kr.co.happyict.service;

import kr.co.happyict.vo.AccessHistoryVO;
import kr.co.happyict.vo.AccessRecordMasterVO;
import kr.co.happyict.vo.LogVO;

public interface CommonService {

  int accessRecordMaster(AccessRecordMasterVO accessRecordMaster) throws Exception;

  int accessHistory(AccessHistoryVO accessHistory);

  void log(LogVO log) throws Exception;
}
