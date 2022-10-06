package kr.co.happyict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.happyict.dao.SurveyDAO;
import kr.co.happyict.service.SurveyService;
import kr.co.happyict.util.CommonUtil;
import kr.co.happyict.vo.SearchSurveyVO;
import kr.co.happyict.vo.SurveyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Service
public class SurveyServiceImpl implements SurveyService {

  @SuppressWarnings("unused")
  private static final Logger log = LoggerFactory.getLogger(SurveyServiceImpl.class);

  private final SurveyDAO surveyDAO;

  public SurveyServiceImpl(SurveyDAO surveyDAO) {
    this.surveyDAO = surveyDAO;
  }

  @Override
  public Map<String, Object> selectGeneralSurveyList(SearchSurveyVO searchSurveyVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", surveyDAO.selectGeneralSurveyCount(searchSurveyVO));
    map.put("list", surveyDAO.selectGeneralSurveyList(searchSurveyVO));

    return map;
  }

  @Override
  public Map<String, Object> selectSurveyList(SearchSurveyVO searchSurveyVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", surveyDAO.selectSurveyCount(searchSurveyVO));
    map.put("list", surveyDAO.selectSurveyList(searchSurveyVO));

    return map;
  }

  @Override
  public Model insertSurveySummar(Model model, SurveyVO surveyVO) {
    long pollMasterSeq = surveyVO.getPollMasterSeq();

    // 같은 대상의 중복된 날짜 있는지 체크
    Map<String, Object> map = surveyDAO.checkDate(surveyVO);

    if (CommonUtil.nvlInt(map.get("startDateVali")) > 0
        || CommonUtil.nvlInt(map.get("endDateVali")) > 0) {
      model.addAttribute("code", "0001");
      model.addAttribute("msg", "중복된 날짜의 설문이 있습니다.");
    } else {
      if (pollMasterSeq > 0) {//수정
        surveyDAO.initPollSubList(surveyVO);
        surveyDAO.initPollItem(surveyVO);
        surveyDAO.updatePoll(surveyVO);

        model.addAttribute("code", "0000");
        model.addAttribute("msg", "OK");
        model.addAttribute("masterPollId", surveyVO.getPollMasterSeq());
      } else {//저장
        int result = surveyDAO.insertSurveySummar(surveyVO);

        if (0 < result) {
          model.addAttribute("code", "0000");
          model.addAttribute("msg", "OK");
          model.addAttribute("masterPollId", surveyVO.getPollMasterSeq());
        } else {
          model.addAttribute("code", "9991");
          model.addAttribute("msg", "등록 실패");
        }
      }
    }

    return model;
  }

  @Override
  public int insertSurveyGroup(SurveyVO surveyVO) {
    return surveyDAO.insertSurveyGroup(surveyVO);
  }

  @Override
  public int insertSurveyQuest(SurveyVO surveyVO) {
    return surveyDAO.insertSurveyQuest(surveyVO);
  }

  @Override
  public int insertSurveyItem(SurveyVO surveyVO) {
    return surveyDAO.insertSurveyItem(surveyVO);
  }

  @Override
  public void selectSurveyMasterDetail(Model model, SurveyVO surveyVO) {
    SurveyVO masterDetail = surveyDAO.selectSurveyMasterDetail(surveyVO);
    String lastP = masterDetail.getLastPage();
    String nextP = CommonUtil.nvl(surveyVO.getNextPage(), "");

    if (lastP.equals(nextP)) {
      masterDetail.setNextPage("");
    } else {
      if (nextP.length() > 0) {
        nextP = nextP.substring(1, 3);
        int intNextP = Integer.parseInt(nextP) + 1;
        if (intNextP < 10) {
          nextP = "P0" + intNextP;
        } else {
          nextP = "P" + intNextP;
        }
        masterDetail.setNextPage(nextP);
      }
    }

    model.addAttribute("masterDetail", masterDetail);
  }

  @Override
  public void selectSurveyListDetail(Model model, SurveyVO surveyVO) {
    surveyVO.setGetType("GROUP");
    List<SurveyVO> groupDetailList = surveyDAO.selectPollListDetail(surveyVO);

    surveyVO.setGetType("OTHER");
    List<SurveyVO> questDetailList = surveyDAO.selectPollListDetail(surveyVO);

    model.addAttribute("groupDetailList", groupDetailList);
    model.addAttribute("questDetailList", questDetailList);
  }

  @Override
  public void selectSurveyItemDetail(Model model, SurveyVO surveyVO) {
    List<SurveyVO> itemDetailList = surveyDAO.selectPollItemDetail(surveyVO);
    model.addAttribute("itemDetailList", itemDetailList);
  }

  @Override
  public void slelectSurveyMasterData(Model model, SurveyVO surveyVO) {
    model.addAttribute("objectData", surveyDAO.slelectSurveyMasterData(surveyVO));
  }

  @Override
  public Map<String, Object> slelectSurveyMasterPersonList(SurveyVO surveyVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", surveyDAO.slelectSurveyMasterPersonCnt(surveyVO));
    map.put("list", surveyDAO.slelectSurveyMasterPersonList(surveyVO));

    return map;
  }

  @Override
  public int pollDateSave(SurveyVO surveyVO) {
    return surveyDAO.pollDateSave(surveyVO);
  }

  @Override
  public void pollDelete(Model model, SurveyVO surveyVO) {
//    surveyDAO.initPollItem(surveyVO);
//    surveyDAO.initPollSubList(surveyVO);
//    surveyDAO.pollDelete(surveyVO);

    surveyDAO.deletePollAnsDetail(surveyVO);
    surveyDAO.deletePollAnsMaster(surveyVO);
    surveyDAO.deletePollItemList(surveyVO);
    surveyDAO.deletePollList(surveyVO);
    surveyDAO.deletePollMaster(surveyVO);

    model.addAttribute("code", "0000");
    model.addAttribute("msg", "OK");
  }

  @Override
  public void pollNoUse(Model model, SurveyVO surveyVO) {
    surveyDAO.pollNoUse(surveyVO);

    model.addAttribute("code", "0000");
    model.addAttribute("msg", "OK");
  }

  @Override
  public void selectPollItemStat(Model model, SurveyVO surveyVO) {
    List<SurveyVO> itemStatList = surveyDAO.selectPollItemStat(surveyVO);
    model.addAttribute("itemStatList", itemStatList);
  }

  @Override
  public void selectPollItemTextStat(Model model, SurveyVO surveyVO) {
    List<SurveyVO> itemTextStatList = surveyDAO.selectPollItemTextStat(surveyVO);
    model.addAttribute("itemTextStatList", itemTextStatList);
  }

  @Override
  public void selectSurveyData(Model model, SurveyVO surveyVO) {
    model.addAttribute("objectData", surveyDAO.selectSurveyData(surveyVO));
    model.addAttribute("result", "OK");
  }

  @Override
  public String selectSurveyFile(SurveyVO surveyVO) {
    return surveyDAO.selectSurveyFile(surveyVO);
  }

  @Override
  public Map<String, Object> selectNoneSurveyList(SearchSurveyVO searchSurveyVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", surveyDAO.selectNoneSurveyCount(searchSurveyVO));
    map.put("list", surveyDAO.selectNoneSurveyList(searchSurveyVO));

    return map;
  }

  @Override
  public void pollCopy(Model model, SurveyVO surveyVO) {
    surveyDAO.pollCopyMaster(surveyVO);
    surveyDAO.pollCopyList(surveyVO);
    surveyDAO.pollCopyItemList(surveyVO);
  }

  @Override
  public void selectPollMasterDetailExcel(HashMap<String, Object> params, ModelMap model) {
    HashMap<String, Object> masterDetail = surveyDAO.selectPollMasterDetailExcel(params);
    String lastP = (String) masterDetail.get("lastPage");
    String nextP = CommonUtil.nvl(params.get("nextPage"), "");

    if (lastP.equals(nextP)) {
      masterDetail.put("nextPage", "");
    } else {
      if (nextP.length() > 0) {
        nextP = nextP.substring(1, 3);

        int intNextP = Integer.parseInt(nextP) + 1;

        if (intNextP < 10) {
          nextP = "P0" + intNextP;
        } else {
          nextP = "P" + intNextP;
        }

        masterDetail.put("nextPage", nextP);
      }
    }

    model.addAttribute("masterDetail", masterDetail);
  }

  @Override
  public void selectPollListDetailExcel(HashMap<String, Object> params, ModelMap model) {

    params.put("getType", "GROUP");
    ArrayList<HashMap<String, Object>> groupList = surveyDAO.selectPollListDetailExcel(params);

    params.put("getType", "OTHER");
    ArrayList<HashMap<String, Object>> questList = surveyDAO.selectPollListDetailExcel(params);

    model.addAttribute("groupList", groupList);
    model.addAttribute("questList", questList);
  }

  public void selectPollItemDetailExcel(HashMap<String, Object> params, ModelMap model) {
    ArrayList<HashMap<String, Object>> itemList = surveyDAO.selectPollItemDetailExcel(params);
    model.addAttribute("itemList", itemList);
  }

  @Override
  public void selectPollItemStatExcel(HashMap<String, Object> params, ModelMap model) {
    ArrayList<HashMap<String, Object>> itemStatList = surveyDAO.selectPollItemStatExcel(params);
    model.addAttribute("itemStatList", itemStatList);
  }

  @Override
  public void selectPollAnsListExcel(HashMap<String, Object> params, ModelMap model) {
    ArrayList<HashMap<String, Object>> pollAnsList = surveyDAO.selectPollAnsListExcel(params);
    model.addAttribute("pollAnsList", pollAnsList);
  }

  @Override
  public void selectPollAnsMAsterListExcel(HashMap<String, Object> params, ModelMap model) {
    ArrayList<HashMap<String, Object>> pollAnsMasterList = surveyDAO.selectPollAnsMAsterListExcel(
        params);
    model.addAttribute("pollAnsMasterList", pollAnsMasterList);
  }
}
