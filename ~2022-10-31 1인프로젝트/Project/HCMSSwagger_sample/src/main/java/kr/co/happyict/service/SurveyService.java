package kr.co.happyict.service;

import java.util.HashMap;
import java.util.Map;
import kr.co.happyict.vo.SearchSurveyVO;
import kr.co.happyict.vo.SurveyVO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

public interface SurveyService {

  Map<String, Object> selectGeneralSurveyList(SearchSurveyVO searchSurveyVO);

  Model insertSurveySummar(Model model, SurveyVO surveyVO);

  Map<String, Object> selectSurveyList(SearchSurveyVO searchSurveyVO);

  int insertSurveyGroup(SurveyVO surveyVO);

  int insertSurveyQuest(SurveyVO surveyVO);

  int insertSurveyItem(SurveyVO surveyVO);

  void selectSurveyMasterDetail(Model model, SurveyVO surveyVO);

  void selectSurveyListDetail(Model model, SurveyVO surveyVO);

  void selectSurveyItemDetail(Model model, SurveyVO surveyVO);

  void slelectSurveyMasterData(Model model, SurveyVO surveyVO);

  Map<String, Object> slelectSurveyMasterPersonList(SurveyVO surveyVO);

  int pollDateSave(SurveyVO surveyVO);

  void pollDelete(Model model, SurveyVO surveyVO);

  void pollNoUse(Model model, SurveyVO surveyVO);

  void selectPollItemStat(Model model, SurveyVO surveyVO);

  void selectPollItemTextStat(Model model, SurveyVO surveyVO);

  void selectSurveyData(Model model, SurveyVO surveyVO);

  String selectSurveyFile(SurveyVO surveyVO);

  Map<String, Object> selectNoneSurveyList(SearchSurveyVO searchSurveyVO);

  void pollCopy(Model model, SurveyVO surveyVO);

  void selectPollMasterDetailExcel(HashMap<String, Object> params, ModelMap model);

  void selectPollListDetailExcel(HashMap<String, Object> params, ModelMap model);

  void selectPollItemDetailExcel(HashMap<String, Object> params, ModelMap model);

  void selectPollItemStatExcel(HashMap<String, Object> params, ModelMap model);

  void selectPollAnsListExcel(HashMap<String, Object> params, ModelMap model);

  void selectPollAnsMAsterListExcel(HashMap<String, Object> params, ModelMap model);
}
