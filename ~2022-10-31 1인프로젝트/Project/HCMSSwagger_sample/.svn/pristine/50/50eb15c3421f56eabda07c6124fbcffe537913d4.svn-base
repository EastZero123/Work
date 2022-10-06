package kr.co.happyict.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.co.happyict.service.RestService;
import kr.co.happyict.vo.BoardManagementRestVO;
import kr.co.happyict.vo.BoardRestVO;

@Api(tags = {"CMS REST 기능 테스트 2"})
@org.springframework.web.bind.annotation.RestController
public class BoardRestController {

  private static final Logger log = LoggerFactory.getLogger(BoardRestController.class);

  private final RestService restService;

  public BoardRestController(RestService restService) {
	this.restService = restService;
  }

  /**
   * 특정 게시판에서 글 정보 가져오기.<br> post: 게시글, 포스트((블로그, 소셜 네트워크 등에 올리는 사진이나 글))
   *
   * @param code 코드명. Ex) notice, support, ...
   * @param seq  해당 게시판의 글 번호
   * @return RestVO -> json
   */
  @ApiOperation(value = "특정게시판 목록", notes = "게시판 코드 번호(boardSeq)를 통해 해당코드의 게시판목록 조회할 수 있습니다."
  		+ "</br></br> Ex)[bno=1] = 배너관리 / [bno=2] = 단일페이지 관리 / [bno=8] = 해당코드의 게시판이 존재하지않음(<strong>상태코드 204</strong>)"
  		+ "</br></br><strong>※ 상태코드 설명</strong>"
  		+ "</br></br><strong>200</strong> - 성공 / <strong>204</strong> - 데이터 없음"
  		+ "")
  @ApiImplicitParam(
		  name = "boardSeq",
		  value = "게시판 코드 번호",
		  required = true,
		  dataType = "integer",
		  paramType = "path",
		  defaultValue = "None",
		  example = "0"
		  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  @GetMapping("/api2/boardlist/{boardSeq}")
  public ResponseEntity<List<BoardRestVO>> getPost(@PathVariable long boardSeq) throws Exception {
    log.debug("[BoardRestController - /api/" + boardSeq + "/" + "]");
    List<BoardRestVO> list = restService.selectBoardRest(boardSeq);

    if (list.isEmpty()) {
    	System.out.println("데이터x");
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    return ResponseEntity.ok().body(list);
  }

  // 소개 페이지
  @ApiOperation(value = "특정게시글 조회", notes = "게시글 번호(bno)를 통해 해당 게시글을 조회할 수 있습니다."
  		+ "</br></br> Ex) [bno=1] 일경우 글번호가 1번인 글의 정보가 출력됩니다."
  		+ "</br>　　[bno=99999] 일경우 해당 글번호가 없어 데이터가 출력되지 않습니다."
  		+ "</br></br><strong>※ 상태코드 설명</strong>"
  		+ "</br></br><strong>200</strong> - 성공 / <strong>204</strong> - 데이터 없음"
  		+ "")
  @ApiImplicitParam(
		  name = "bno",
		  value = "게시글 번호",
		  required = true,
		  dataType = "integer",
		  paramType = "path",
		  defaultValue = "None",
		  example = "0"
		  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "요청에 성공했지만, 데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  @GetMapping("/api2/board/{bno}")
  public ResponseEntity<BoardRestVO> getPage(@PathVariable long bno) throws Exception {
    log.debug("[BoardRestController - /api/page/" + bno + "]");

    //RestVO restVO = new RestVO();
    BoardRestVO boardRestVO = new BoardRestVO();
    //restVO.setTitle(id);
    //restVO = boardService.selectRestPage(restVO);
    boardRestVO.setBoardSeq(bno);
    boardRestVO = restService.selectBoard(bno);

    if (boardRestVO == null) {
    	System.out.println("데이터 없다");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    System.out.println("데이터 있다");
    return ResponseEntity.ok().body(boardRestVO);
  }
  @ApiOperation(value = "게시판 코드 목록", notes = "게시판의 코드 목록들을 조회할 수 있습니다."
  		+ "</br></br> 가져온 데이터중 boardCode 값과 boardName 값을 통해 해당 게시판의 목록을 조회할 수 있습니다."
  		+ "</br></br> Ex) boardCode = mainBanner , boardName = 배너관리")
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "요청에 성공했지만, 데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  @GetMapping("/api2/boards/list")
  public ResponseEntity<List<BoardManagementRestVO>> getManagement() throws Exception {
	  List<BoardManagementRestVO> vo = restService.selectBoardManagementList();
	  return ResponseEntity.ok().body(vo);
  }

  
  
}
