package kr.co.happyict.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.co.happyict.service.BoardManagementService;
import kr.co.happyict.service.BoardService;
import kr.co.happyict.util.PageUtil;
import kr.co.happyict.vo.BoardManagementVO;
import kr.co.happyict.vo.BoardVO;
import kr.co.happyict.vo.RestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"CMS REST API 1"})
@org.springframework.web.bind.annotation.RestController
public class RestController {

  private static final Logger log = LoggerFactory.getLogger(RestController.class);

  private final BoardService boardService;
  private final BoardManagementService boardManagementService;
  private final HttpServletRequest request;

  public RestController(BoardService boardService,
      BoardManagementService boardManagementService, HttpServletRequest request) {
    this.boardService = boardService;
    this.boardManagementService = boardManagementService;
    this.request = request;
  }

  /**
   * 특정 게시판에서 글 정보 가져오기.<br> post: 게시글, 포스트((블로그, 소셜 네트워크 등에 올리는 사진이나 글))
   *
   * @param code 코드명. Ex) notice, support, ...
   * @param seq  해당 게시판의 글 번호
   * @return RestVO -> json
   */
  @GetMapping("/api/{code}/{seq}")
  @ApiOperation(value = "특정게시판 상세 목록", notes = "게시판 코드(code)와 게시글 번호(seq)를 통해 해당게시판의 상세목록을 조회할 수 있습니다.")
  @ApiImplicitParams({
	  @ApiImplicitParam(
			  name = "code",
			  value = "게시판 코드",
			  required = true,
			  dataType = "string",
			  paramType = "path",
			  defaultValue = "None"
			  ),
	  @ApiImplicitParam(
			  name = "seq",
			  value = "게시글 번호",
			  required = true,
			  dataType = "integer",
			  paramType = "path",
			  defaultValue = "None",
			  example = "0"
			  )
  })
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  public ResponseEntity<BoardVO> getPost(@PathVariable String code, @PathVariable long seq) {
    log.debug("[BoardRestController - /api/" + code + "/" + seq + "]");

    // 해당 코드명의 BOARD_MANAGEMENT_SEQ 가져오기
    long boardManagementSeq = boardManagementService.selectBoardManagement(code)
        .getBoardManagementSeq();

    RestVO restVO = new RestVO();
    restVO.setBoardManagementSeq(boardManagementSeq);

    return ResponseEntity.ok(boardService.selectBoard(seq));
  }

  // 소개 페이지
  @GetMapping("/api/page/{id}")
  @ApiOperation(
		  value = "소개페이지 상세 목록",
		  notes = "소개페이지의 코드(id)를 통해 소개페이지 데이터를 조회할 수 있습니다. ex)intro, history, contact 등 ")
  @ApiImplicitParam(
		  name = "id",
		  value = "소개페이지 코드",
		  required = true,
		  dataType = "string",
		  paramType = "path",
		  defaultValue = "None"
		  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  public ResponseEntity<RestVO> getPage(@PathVariable String id) {
    log.debug("[BoardRestController - /api/page/" + id + "]");

    RestVO restVO = new RestVO();
    restVO.setTitle(id);
    restVO = boardService.selectRestPage(restVO);

    if (restVO != null) {
      System.out.println("데이터 있다");
    } else {
      System.out.println("데이터 없다");
    }

    return ResponseEntity.ok(restVO);
  }

  // 배너 페이지
  @GetMapping("/api/banner/{id}")
  @ApiOperation(value = "메인배너 데이터 요청", notes = "배너코드(id)를 통해 해당배너목록 조회할 수 있습니다.")
  @ApiImplicitParam(
		  name = "id",
		  value = "배너 코드",
		  required = true,
		  dataType = "string",
		  paramType = "path",
		  defaultValue = "None"
		  )
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  public ResponseEntity<Map<String, Object>> getBanner(@PathVariable String id) {
    log.debug("[BoardRestController - /api/banner/" + id + "]");

    BoardVO boardVO = new BoardVO();
    boardVO.setBoardManagementSeq(1);
    Map<String, Object> map = boardService.selectBoardList(boardVO);

    return ResponseEntity.ok(map);
  }

  // 게시판 목록
  @GetMapping("/api/board/{id}")
  @ApiOperation(value = "게시판 목록", notes = "게시판 코드(id)를 통해 해당게시판목록 조회할 수 있습니다.")
  @ApiImplicitParams({
	  @ApiImplicitParam(
			  name = "id",
			  value = "게시판 코드",
			  required = true,
			  dataType = "string",
			  paramType = "path"
			  ),
	  @ApiImplicitParam(
			  name = "search",
			  value = "검색어",
			  dataType = "integer",
			  paramType = "query",
			  defaultValue = ""
			  ),
	  @ApiImplicitParam(
			  name = "page",
			  value = "페이지",
			  dataType = "integer",
			  paramType = "query",
			  defaultValue = "1",
			  example = "1"
			  )
  })
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  public ResponseEntity<Map<String, Object>> getBoard(@PathVariable String id,
      @RequestParam(required = false) String search, @RequestParam(required = false) int page) {
    log.debug("[BoardRestController - /api/board/" + id + "]");

    String listUrl = request.getRequestURI();

    BoardManagementVO boardManagementVO = boardManagementService.selectBoardManagement(id);

    BoardVO boardVO = new BoardVO();
    boardVO.setCurrentPage(page);
    boardVO.setBoardManagementSeq(boardManagementVO.getBoardManagementSeq());
    boardVO.setPageRow(boardManagementVO.getPageRow());
    boardVO.setPageBlock(boardManagementVO.getPageBlock());
    boardVO.setStartLimit(boardVO.getPageRow() * (page - 1));
    boardVO.setSearch(search);

    List<BoardVO> list = boardService.selectRestBoardList(boardVO);

    boardVO.setTotalRow(boardService.selectBoardCount(boardVO));
    boardVO.setEndLimit(boardVO.getTotalRow() - (boardVO.getStartLimit()));
    boardVO.setTotalPage(
        PageUtil.getPageCount(boardVO.getPageRow(), boardVO.getTotalRow()));

    String paging = PageUtil.pageIndexList(boardVO.getCurrentPage(), boardVO.getTotalPage(),
        boardVO.getPageRow(), listUrl, boardVO.getPageBlock());

    Map<String, Object> result = new HashMap<>();
    result.put("list", list);
    result.put("paging", paging);

    return ResponseEntity.ok(result);
  }

  // 게시판 상세
  @GetMapping("/api/board/{id}/{page}")
  @ApiOperation(
		  value = "특정게시판의 상세 목록",
		  notes = "게시판 코드(id)와 게시글번호(page)를 통해 해당게시판목록 조회할 수 있습니다. ex) notice,")
  @ApiImplicitParams({
	  @ApiImplicitParam(
			  name = "id",
			  value = "게시판 코드",
			  required = true,
			  dataType = "string",
			  paramType = "path"
			  ),
	  @ApiImplicitParam(
			  name = "page",
			  value = "게시글 번호",
			  required = true,
			  dataType = "string",
			  paramType = "path",
			  defaultValue = "1"
			  )
  })
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공입니다.")
      ,@ApiResponse(code = 204, message = "데이터가 없습니다.")
      , @ApiResponse(code = 400, message = "필수 요청 변수가 없거나 요청 변수 이름이 잘못되었습니다.")
      , @ApiResponse(code = 404, message = "서버 요청 URL이 잘못되었습니다.")
  })
  public ResponseEntity<BoardVO> getDetail(@PathVariable String id, @PathVariable String page) {
    log.debug("[BoardRestController - /api/board/" + id + "/" + page + "]");

    BoardManagementVO boardManagementVO = boardManagementService.selectBoardManagement(id);

    BoardVO boardVO = new BoardVO();

    boardVO.setBoardSeq(Long.parseLong(page));

    // 게시판 페이지가 존재할 경우
    if (boardManagementVO != null) {
      boardVO = boardService.selectBoard(boardVO.getBoardSeq());
    } else {
      boardVO.setContent(null);
    }

    return ResponseEntity.ok(boardVO);
  }
}
