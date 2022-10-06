package kr.co.happyict.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.happyict.vo.SearchSurveyVO;
import kr.co.happyict.vo.SurveyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyDAO {

  private final SqlSession sqlSession;

  public SurveyDAO(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public List<SurveyVO> selectGeneralSurveyList(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectList("SurveyDAO.selectGeneralSurveyList", searchSurveyVO);
  }

  public int selectGeneralSurveyCount(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectOne("SurveyDAO.selectGeneralSurveyCount", searchSurveyVO);
  }

  public List<SurveyVO> selectSurveyList(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectList("SurveyDAO.selectSurveyList", searchSurveyVO);
  }

  public int selectSurveyCount(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectOne("SurveyDAO.selectSurveyCount", searchSurveyVO);
  }

  //중복 체크
  public Map<String, Object> checkDate(SurveyVO surveyVO) {
    return sqlSession.selectOne("SurveyDAO.checkDate", surveyVO);
  }

  public int insertSurveySummar(SurveyVO surveyVO) {
    return sqlSession.insert("SurveyDAO.insertSurveySummar", surveyVO);
  }

  public int insertSurveyGroup(SurveyVO surveyVO) {
    return sqlSession.insert("SurveyDAO.insertSurveyGroup", surveyVO);
  }

  public int insertSurveyQuest(SurveyVO surveyVO) {
    return sqlSession.insert("SurveyDAO.insertSurveyQuest", surveyVO);
  }

  public int insertSurveyItem(SurveyVO surveyVO) {
    return sqlSession.insert("SurveyDAO.insertSurveyItem", surveyVO);
  }

  public SurveyVO selectSurveyMasterDetail(SurveyVO surveyVO) {
    return sqlSession.selectOne("SurveyDAO.selectSurveyMasterDetail", surveyVO);
  }

  public List<SurveyVO> selectPollListDetail(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.selectPollListDetail", surveyVO);
  }

  public List<SurveyVO> selectPollItemDetail(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.selectPollItemDetail", surveyVO);
  }

  public SurveyVO slelectSurveyMasterData(SurveyVO surveyVO) {
    return sqlSession.selectOne("SurveyDAO.slelectSurveyMasterData", surveyVO);
  }

  public int slelectSurveyMasterPersonCnt(SurveyVO surveyVO) {
    return sqlSession.selectOne("SurveyDAO.slelectSurveyMasterPersonCnt", surveyVO);
  }

  public List<SurveyVO> slelectSurveyMasterPersonList(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.slelectSurveyMasterPersonList", surveyVO);
  }

  public int pollDateSave(SurveyVO surveyVO) {
    return sqlSession.update("SurveyDAO.pollDateSave", surveyVO);
  }

  public void initPollItem(SurveyVO surveyVO) {
    sqlSession.delete("SurveyDAO.initPollItem", surveyVO);
  }

  public void initPollSubList(SurveyVO surveyVO) {
    sqlSession.delete("SurveyDAO.initPollSubList", surveyVO);
  }

  public int pollDelete(SurveyVO surveyVO) {
    return sqlSession.delete("SurveyDAO.pollDelete", surveyVO);
  }

  public void pollNoUse(SurveyVO surveyVO) {
    sqlSession.update("SurveyDAO.pollNoUse", surveyVO);
  }

  public List<SurveyVO> selectPollItemStat(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.selectPollItemStat", surveyVO);
  }

  public List<SurveyVO> selectPollItemTextStat(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.selectPollItemTextStat", surveyVO);
  }

  public List<SurveyVO> selectSurveyData(SurveyVO surveyVO) {
    return sqlSession.selectList("SurveyDAO.selectSurveyData", surveyVO);
  }

  public String selectSurveyFile(SurveyVO surveyVO) {
    return sqlSession.selectOne("SurveyDAO.selectSurveyFile", surveyVO);
  }

  public void updatePoll(SurveyVO surveyVO) {
    sqlSession.update("SurveyDAO.updatePoll", surveyVO);
  }


  public List<SurveyVO> selectNoneSurveyList(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectList("SurveyDAO.selectNoneSurveyList", searchSurveyVO);
  }

  public int selectNoneSurveyCount(SearchSurveyVO searchSurveyVO) {
    return sqlSession.selectOne("SurveyDAO.selectNoneSurveyCount", searchSurveyVO);
  }

  public void pollCopyMaster(SurveyVO surveyVO) {
    sqlSession.insert("SurveyDAO.pollCopyMaster", surveyVO);
  }

  public void pollCopyList(SurveyVO surveyVO) {
    sqlSession.insert("SurveyDAO.pollCopyList", surveyVO);
  }

  public void pollCopyItemList(SurveyVO surveyVO) {
    sqlSession.insert("SurveyDAO.pollCopyItemList", surveyVO);
  }

  public HashMap<String, Object> selectPollMasterDetailExcel(HashMap<String, Object> params) {
    return sqlSession.selectOne("SurveyDAO.selectPollMasterDetailExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollListDetailExcel(
      HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollListDetailExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollItemDetailExcel(
      HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollItemDetailExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollItemStatExcel(
      HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollItemStatExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollAnsListExcel(HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollAnsListExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollAnsMAsterListExcel(
      HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollAnsMAsterListExcel", params);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ArrayList<HashMap<String, Object>> selectPollAnsList(HashMap<String, Object> params) {
    return (ArrayList) sqlSession.selectList("SurveyDAO.selectPollAnsList", params);
  }

  public void deletePollAnsDetail(SurveyVO surveyVO) {
    sqlSession.update("deletePollAnsDetail", surveyVO);
  }

  public void deletePollAnsMaster(SurveyVO surveyVO) {
    sqlSession.update("deletePollAnsMaster", surveyVO);
  }

  public void deletePollItemList(SurveyVO surveyVO) {
    sqlSession.update("deletePollItemList", surveyVO);
  }

  public void deletePollList(SurveyVO surveyVO) {
    sqlSession.update("deletePollList", surveyVO);
  }

  public void deletePollMaster(SurveyVO surveyVO) {
    sqlSession.update("deletePollMaster", surveyVO);
  }
}
