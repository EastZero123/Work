package kr.co.happyict.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.co.happyict.common.Constants;
import kr.co.happyict.dao.BoardDAO;
import kr.co.happyict.dao.BoardManagementDAO;
import kr.co.happyict.service.BoardService;
import kr.co.happyict.util.CommonUtil;
import kr.co.happyict.util.HappyFile;
import kr.co.happyict.vo.AccountVO;
import kr.co.happyict.vo.BoardAttachVO;
import kr.co.happyict.vo.BoardManagementVO;
import kr.co.happyict.vo.BoardVO;
import kr.co.happyict.vo.RestVO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

  private final String uploadLivePath = Constants.UPLOAD_LIVE_PATH;
  private final String uploadTempPath = Constants.UPLOAD_TEMP_PATH;

  private final BoardDAO boardDAO;

  private final BoardManagementDAO boardManagementDAO;

  private final HappyFile happyFile;

  private final HttpServletRequest request;

  private String ajaxResult = Constants.AJAX_RETURN;

  public BoardServiceImpl(BoardDAO boardDAO, BoardManagementDAO boardManagementDAO,
      HappyFile happyFile, HttpServletRequest request) {
    this.boardDAO = boardDAO;
    this.boardManagementDAO = boardManagementDAO;
    this.happyFile = happyFile;
    this.request = request;
  }

  @Override
  public Map<String, Object> selectBoardList(BoardVO searchBoardVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", boardDAO.selectBoardCount(searchBoardVO));
    map.put("list", boardDAO.selectBoardList(searchBoardVO));

    return map;
  }

//  @Override
//  public Map<String, Object> selectPage(RestVO title) {
//    return boardDAO.selectPage(title);
//  }

  @Override
  public BoardVO selectBoard(long boardSeq) {
    BoardVO boardVO = boardDAO.selectBoard(boardSeq);

    if (boardVO != null) {
      if (boardVO.getContent() != null) {
        boardVO.setContent(CommonUtil.editorToHtml(boardVO.getContent()));
      }

      boardVO.setBoardAttachList(boardDAO.selectBoardAttachList(boardSeq));
    }

    return boardVO;
  }

  @Override
  public String insertBoard(BoardManagementVO boardManagementVO, BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (!boardManagementVO.getBoardCode().equals("")) {
      boardVO.setRegId(accountLogin.getAccountId());
      boardVO.setRegName(accountLogin.getAccountName());

      // 썸네일 존재시 파일복사 처리
      if (boardManagementVO.getThumbimgYn().equals("Y")) {
        try {
          if (!boardVO.getThumImg().equals("")) {
            happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                boardVO.getThumImg());
          }

          if (boardVO.getThumImg2() != null) {
            if (!boardVO.getThumImg2().equals("")) {
              happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                  boardVO.getThumImg2());
            }
          }

          if (boardVO.getThumImg3() != null) {
            if (!boardVO.getThumImg3().equals("")) {
              happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                  boardVO.getThumImg3());
            }
          }
        } catch (IOException e) {
          ajaxResult = "imgFail";

          return ajaxResult;
        }
      }

      if (boardManagementVO.getAttYn().equals("Y")) {
        if (boardVO.getBoardAttachList() != null) {
          for (BoardAttachVO boardAttachVO : boardVO.getBoardAttachList()) {
            if (!boardAttachVO.getFileName().equals("")) {
              boardVO.setFileYn("Y");
              boardVO.setAttFile("Y");

              break;
            }
          }
        }
      }

      if (boardManagementVO.getEditYn().equals("Y")) {
        //에디터 이미지 실폴더로 이동 및 삭제처리
        happyFile.editorFileService(boardVO, "insert");
        boardVO.setContent(boardVO.getContent().replaceAll(uploadTempPath, uploadLivePath));
      }

      boardDAO.insertBoard(boardVO);

      if (boardManagementVO.getAttYn().equals("Y")) {
        if (boardVO.getBoardAttachList() != null) {
          for (BoardAttachVO boardAttachVO : boardVO.getBoardAttachList()) {
            if (!boardAttachVO.getFileName().equals("")) {
              try {
                boardAttachVO.setSpecId(boardManagementVO.getBoardCode() + "-file");
                happyFile.happyFileCopy(boardAttachVO.getSpecId(), boardAttachVO.getFileName());
              } catch (Throwable e) {
                ajaxResult = "fileFail";

                return ajaxResult;
              }

              boardAttachVO.setBoardSeq(boardVO.getBoardSeq());
              boardAttachVO.setRegId(accountLogin.getAccountId());
              boardDAO.insertBoardAttach(boardAttachVO);
            }
          }
        }
      }
    }

    return ajaxResult;
  }

  @Override
  public String updateBoard(BoardManagementVO boardManagementVO, BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (boardManagementVO.getAttYn().equals("Y")) {
      if (boardVO.getBoardAttachList() != null) {
        for (BoardAttachVO boardAttachVO : boardVO.getBoardAttachList()) {
          if (!boardAttachVO.getFileName().equals("")) {
            boardVO.setFileYn("Y");
            boardVO.setAttFile("Y");

            break;
          }
        }
      }
    }

    if (boardVO.getBoardSeq() > 0) {
      if (boardManagementVO.getThumbimgYn().equals("Y")) {
        // 썸네일 존재시 파일복사 처리
        try {
          if (!boardVO.getThumImg().equals(boardVO.getTempThumImg())) {
            if (!boardVO.getTempThumImg().equals("")) {
              happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                  boardVO.getTempThumImg());
            }

            if (!boardVO.getThumImg().equals("")) {
              happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                  boardVO.getThumImg());
            }
          }

          if (boardVO.getThumImg2() != null) {
            if (!boardVO.getThumImg2().equals(boardVO.getTempThumImg2())) {
              if (!boardVO.getTempThumImg2().equals("")) {
                happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                    boardVO.getTempThumImg2());
              }

              if (!boardVO.getThumImg2().equals("")) {
                happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                    boardVO.getThumImg2());
              }
            }
          }

          if (boardVO.getThumImg3() != null) {
            if (!boardVO.getThumImg3().equals(boardVO.getTempThumImg3())) {
              if (!boardVO.getTempThumImg3().equals("")) {
                happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                    boardVO.getTempThumImg3());
              }

              if (!boardVO.getThumImg3().equals("")) {
                happyFile.happyFileCopy(boardManagementVO.getBoardCode() + "-img",
                    boardVO.getThumImg3());
              }
            }
          }
        } catch (IOException e) {
          ajaxResult = "imgFail";
          return ajaxResult;
        }
      }

      if (boardManagementVO.getAttYn().equals("Y")) {
        if (boardVO.getBoardAttachList() != null) {
          for (BoardAttachVO boardAttachVO : boardVO.getBoardAttachList()) {
            if (!boardAttachVO.getFileName().equals(boardAttachVO.getTempName())) {
              boardAttachVO.setSpecId(boardManagementVO.getBoardCode() + "-file");
              try {
                if (boardAttachVO.getTempName() != null) {
                  if (!boardAttachVO.getTempName().equals("")) {
                    happyFile.happyFileDelete(boardAttachVO.getSpecId(),
                        boardAttachVO.getTempName());
                    // db삭제
                    boardAttachVO.setBoardSeq(boardVO.getBoardSeq());
                    boardAttachVO.setUpdId(accountLogin.getAccountId());
                    boardDAO.deleteBoardAttach(boardAttachVO);
                  }
                }

                if (boardAttachVO.getFileName() != null) {
                  if (!boardAttachVO.getFileName().equals("")) {
                    happyFile.happyFileCopy(boardAttachVO.getSpecId(), boardAttachVO.getFileName());

                    boardAttachVO.setBoardSeq(boardVO.getBoardSeq());
                    boardAttachVO.setRegId(accountLogin.getAccountId());
                    boardDAO.insertBoardAttach(boardAttachVO);
                  }
                }
              } catch (Throwable e) {
                ajaxResult = "fileFail";
                return ajaxResult;
              }
            }

            if (!boardAttachVO.getFileName().equals("")) {
              boardVO.setFileYn("Y");
            }
          }
        }
      }

      if (boardManagementVO.getEditYn().equals("Y")) {
        //에디터 이미지 실폴더로 이동 및 삭제처리
        happyFile.editorFileService(boardVO, "update");
        boardVO.setContent(boardVO.getContent().replaceAll(uploadTempPath, uploadLivePath));
      }

      boardVO.setUpdId(accountLogin.getAccountId());
      boardDAO.updateBoard(boardVO);
    }

    return ajaxResult;
  }

  @Override
  public String deleteBoard(BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (boardVO.getBoardManagementSeq() > 0) {
      BoardManagementVO boardManagementVO = boardManagementDAO.selectBoardManagementSeq(
          boardVO.getBoardManagementSeq());
      boardVO.setUpdId(accountLogin.getAccountId());

      if (boardManagementVO != null) {
        if (boardManagementVO.getThumbimgYn().equals("Y")) {
          List<BoardVO> thumList = boardDAO.selectDelBoardThumImg(boardVO);
          for (BoardVO delThum : thumList) {
            if (delThum.getThumImg() != null) {
              if (!delThum.getThumImg().equals("")) {
                happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                    delThum.getThumImg());
              }
            }

            if (delThum.getThumImg2() != null) {
              if (!delThum.getThumImg2().equals("")) {
                happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                    delThum.getThumImg2());
              }
            }

            if (delThum.getThumImg3() != null) {
              if (!delThum.getThumImg3().equals("")) {
                happyFile.happyFileDelete(boardManagementVO.getBoardCode() + "-img",
                    delThum.getThumImg3());
              }
            }
          }
        }

        if (boardManagementVO.getAttYn().equals("Y")) {

          List<BoardAttachVO> boardAttachList = boardDAO.selectDeleteBoardAttach(boardVO);

          for (BoardAttachVO boardAttachVO : boardAttachList) {
            if (!boardAttachVO.getTempName().equals("")) {
              happyFile.happyFileDelete(boardAttachVO.getSpecId(), boardAttachVO.getTempName());
              // db삭제
              boardAttachVO.setUpdId(accountLogin.getAccountId());
              boardDAO.deleteBoardAttach(boardAttachVO);
            }
          }
        }

        boardDAO.deleteBoard(boardVO);
      } else {
        ajaxResult = "boardManagementSeqFail";
      }
    }

    return ajaxResult;
  }

  @Override
  public String updateBoardUseAjax(BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;
    AccountVO accountLogin = (AccountVO) request.getSession().getAttribute("accountLogin");

    if (boardVO.getBoardManagementSeq() == 1 && boardVO.getUseYn().equals("Y")) {
      if (boardDAO.selectBoardUseCount(boardVO) > 2) {
        return Constants.AJAX_RETURN_FAIL;
      }
    }

    if (boardVO.getBoardSeq() > 0) {
      boardVO.setUpdId(accountLogin.getAccountId());
      boardDAO.updateBoardUseAjax(boardVO);
    }

    return ajaxResult;
  }

  @Override
  public void updateMainBasic(BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;

    if (boardVO.getBoardSeq() > 0) {
      boardDAO.updateMainBasic(boardVO);
    }

  }

  @Override
  public void updateOrderAjax(BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;

    if (boardVO.getBoardSeq() > 0) {
      boardDAO.updateOrderAjax(boardVO);
    }

  }

  @Override
  public void updateMainOrderAjax(BoardVO boardVO) {
    ajaxResult = Constants.AJAX_RETURN;

    if (boardVO.getBoardSeq() > 0) {
      boardDAO.updateMainOrderAjax(boardVO);
    }

  }

  @Override
  public List<BoardVO> selectBoardMainList(long boardManagementSeq) {
    return boardDAO.selectBoardMainList(boardManagementSeq);
  }

  @Override
  public Map<String, Object> selectHistory(BoardVO searchBoardVO) {
    Map<String, Object> map = new HashMap<>();

    map.put("total", boardDAO.selectHistoryCount(searchBoardVO));
    map.put("list", boardDAO.selectHistory(searchBoardVO));

    return map;
  }

  @Override
  public RestVO selectRestPage(RestVO restVO) {
    return boardDAO.selectRestPage(restVO);
  }

  @Override
  public List<BoardVO> selectRestBoardList(BoardVO boardVO) {
    return boardDAO.selectRestBoardList(boardVO);
  }

  @Override
  public int selectBoardCount(BoardVO boardVO) {
    return boardDAO.selectBoardCount(boardVO);
  }
}
