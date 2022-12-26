package kr.co.happyict.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import kr.co.happyict.service.OneonOneService;
import kr.co.happyict.service.OneonReplyService;
import kr.co.happyict.vo.OneonOneVO;
import kr.co.happyict.vo.OneonReplyVO;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/oneonone")
public class OneOnOneController {

	private final OneonOneService oneonOneService;

	private final OneonReplyService oneonReplyService;

	public OneOnOneController(OneonReplyService oneonReplyService, OneonOneService oneonOneService) {
		this.oneonOneService = oneonOneService;
		this.oneonReplyService = oneonReplyService;
	}
	
	@ApiOperation(value = "1:1 ReadALL", notes = "GET방식으로 1대1 문의조회")
	@GetMapping("/board/list")
	public ResponseEntity<List<OneonOneVO>> selectAll() throws Exception {
		List<OneonOneVO> vo = oneonOneService.selectAll();
		return ResponseEntity.ok().body(vo);
	}

	@ApiOperation(value = "1:1 Register", notes = "POST방식으로 1대1 문의등록")
	@PostMapping("/board/register")
	public ResponseEntity<String> register(@RequestBody OneonOneVO oneonOneVO) {
		ResponseEntity<String> entity = null;
		try {
			oneonOneService.register(oneonOneVO);
			entity = new ResponseEntity<String>("", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@ApiOperation(value = "1:1 ReadOne", notes = "1대1 게시판 상세조회")
	@GetMapping("/board/list/{bno}")
	public ResponseEntity<List<OneonOneVO>> readOne(@PathVariable("bno") int bno) throws Exception {
		ResponseEntity<List<OneonOneVO>> entity = null;
		try {
			entity = new ResponseEntity<>(oneonOneService.readOne(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@ApiOperation(value = "1:1 Update", notes = "1대1 문의 수정")
	@PutMapping("/board/update/{bno}")
	public ResponseEntity<String> update(@PathVariable("bno") Long bno, @RequestBody OneonOneVO oneonOneVO) {
		ResponseEntity<String> entity = null;
		try {
			oneonOneVO.setBno(bno);
			oneonOneService.update(oneonOneVO);
			entity = new ResponseEntity<String>("", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@ApiOperation(value = "1:1 Delete", notes = "1대1 문의 삭제")
	@DeleteMapping("/board/delete/{bno}")
	public ResponseEntity<String> delete(@PathVariable("bno") int bno) {
		ResponseEntity<String> entity = null;
		try {
			oneonOneService.delete(bno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 이 아래로는 댓글
	//
	//
	//

	@ApiOperation(value = "1:1 Reply ReadAll", notes = "1대1 답변 전체 조회")
	@GetMapping("/reply/{bno}")
	public ResponseEntity<List<OneonReplyVO>> selectAll(@PathVariable("bno") int bno) {
		ResponseEntity<List<OneonReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(oneonReplyService.selectAll(bno), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@ApiOperation(value = "1:1 Reply Create", notes = "1대1 답변 작성")
	@PostMapping("/reply/register")
	public ResponseEntity<String> register(@RequestBody OneonReplyVO oneonReplyVO) {
		ResponseEntity<String> entity = null;
		try {
			oneonReplyService.create(oneonReplyVO);
			entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
		return entity;
	}

	@ApiOperation(value = "1:1 Reply Update", notes = "1대1 답변 수정")
	@PutMapping("/reply/update/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody OneonReplyVO oneonReplyVO) {
		ResponseEntity<String> entity = null;
		try {
			oneonReplyVO.setRno(rno);
			oneonReplyService.update(oneonReplyVO);
			entity = new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@ApiOperation(value = "1:1 Reply Delete", notes = "1대1 답변 삭제")
	@DeleteMapping("/reply/delete/{rno}")
	public ResponseEntity<String> deletereply(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		try {
			oneonReplyService.delete(rno);
			entity = new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
