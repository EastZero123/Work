package kr.co.happyict.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SurveyVO extends PageVO {

  /* POLL_MASTER_T_NEW
   * 설문조사 기본(필수) 테이블 : 설문조사 제목등 기본(필수)값 테이블
   */
  private long pollMasterSeq;      //설문조사 시퀀스
  private long newPollMasterSeq;    //설문조사 복사시 사용할 시퀀스
  private String title;        //설문 제목
  private String content;        //설문 설명
  private String startDate;      //시작날짜
  private String endDate;        //종료날짜
  private String pollImg;        //이미지명
  private String orgPollImg;      //원본이미지명
  private String pollImgDesc;      //이미지설명 alt
  private String targetGradeCode;
  private String targetGradeName;
  private String targetCardinal;
  //private int targetCardinal;

  /* POLL_LIST_T_NEW
   * 설문조사 문항정보 테이블
   * pollMasterSeq 공통
   */
  private long pollListSeq;      //설문 항목 시퀀스
  private String groupId;        //그룹(페이지)ID
  private String objectId;      //항목ID
  private String objectType;      //항목구분 : 그룹:GROUP / 소제목:SUBTITLE / 질문:QUESTION'
  private String qstType;        //질문유형 : 단일(QTYPE1), 복수(QTYPE2), 주관식단답(QTYPE3), 주관식장문(QTYPE4), 선호도(QTYPE5), 표(QTYPE6), 그룹이동(QTYPE7)
  private String mainText;      //그룹명,소제목,질문명
  private String groupName;      //mainText
  private String questTitle;      //mainText
  private String subText;        //설명
  private String comment;        //subText
  private String questComment;    //subText
  private String addText;        //부연
  private String subComment;      //addText
  private String mdtYn;        //필수입력 여부
  private String sequence;      //정렬순서
  private String cntType1;      //시작값(최소값)
  private String cntType2;      //종료값(최대값)
  private String cntType1Text;    //시작(최소)값 설명
  private String cntType2Text;    //종료(최대)값 설명
  private String displayOrder;    //선호도형 역숙 여부 : 정순-값없음, 역순-(A)
  private String etcYn;        //기타답변 여부

  /* POLL_ITEM_LIST_T_NEW
   * 설문항목 상세내역 테이블
   * pollMasterSeq, groupId, objectId, mainText, sequence 공통
   */
  private long pollItemListSeq;    //상세항목 시퀀스
  private String rowNum;        //표형:열, 이동형:이동그룹
  private String colNum;        //표:행, 이동:종료그룹

  /*공통*/
  private String delYn;
  private String updId;
  private String updDate;
  private String regId;
  private String regDate;

  /*리스트 컬럼*/
  private String ansCnt;        //설문답변자수
  private String ansExtCnt;        //설문답변 제외자
  private String pollState;

  /*미리보기 페이지*/
  private String viewType;
  private String nextPage;
  private String lastPage;
  private String itemCnt;
  private String getType;
  private String rowNumIntegerCheck;
  private String finishPage;
  private String userInfoSeq;

  /* 리스트 조회 */
  private String searchTitle;
  private String searchStartDate;
  private String searchEndDate;

  /* POLL_ANS_MASTER_T_NEW
   * 설문답변 기본정보 테이블
   */
  private long pollAnsMasterSeq = 0;      //답변기본 시퀀스
  private String submitYn;        //제출여부(완료)
  private String existsYn;        //평가(통계)제외 여부

  /* POLL_ANS_DETAIL_T_NEW
   * 설문답변 세부정보 테이블
   * pollAnsMasterSeq 공통
   */
  private long pollAnsDetailSeq;      //세부답변 시퀀스
  private String ans1;          //선택값 답변
  private String ans2;          //주관식,장문형 답변
  private String ansEtc;          //단일,복수선택 기타 답변
  private int rowCnt;
  private int cnt;
  private long per;

  private String tempFile01;
  private String noneYn;
}
