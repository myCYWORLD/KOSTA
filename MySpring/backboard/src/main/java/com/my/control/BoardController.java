package com.my.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.dto.ResultBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.BoardService;

import net.coobird.thumbnailator.Thumbnailator;
@Controller
public class BoardController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private BoardService service;

	@Autowired
	private ServletContext sc;

	@GetMapping("boardlist")
	@ResponseBody
	public ResultBean<PageBean<Board>> list(@RequestParam(required = false, defaultValue = "1") int currentPage){
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb = service.boardList(currentPage);
			rb.setStatus(1);
			rb.setT(pb);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	@GetMapping("searchboard")
	@ResponseBody
	public ResultBean<PageBean<Board>> search(
			@RequestParam(required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "") String word){
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb;
			if("".equals(word)) { 
				pb =  service.boardList(currentPage);
			}else {
				pb = service.searchBoard(word, currentPage);
			}
			rb.setStatus(1);
			rb.setT(pb);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	@GetMapping("viewboard")
	@ResponseBody
	public ResultBean<Board> viewBoard(int boardNo){
		ResultBean<Board> rb = new ResultBean<>();
		try {
			Board b = service.viewBoard(boardNo);
			rb.setStatus(1);
			rb.setT(b);
		}catch(FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	@PostMapping("/writeboard")
	@ResponseBody //mvc 사용하지 않겠다는 어노테이션
	//MultipartFile을 사용하려면 @RequestPart 가 필요함
	//제이슨 형태의 응답내용이 아니라 응답의 성공과 실패 여부만 보내고 싶을때 ResponseEntity<?> 사용 
	// ResponseEntity->응답 상태코드값을 조절가능 (응답상태코드값을 200으로 설정, 내용도 직접 설정 가능)
	public ResponseEntity<?> write(
			@RequestPart(required = false) List<MultipartFile> letterFiles 
			,@RequestPart(required = false) MultipartFile imageFile
			,Board board
			,String greeting
			,HttpSession session){

		logger.info("요청전달데이터 title=" + board.getBoardTitle() + ", content=" + board.getBoardContent());
		logger.info("파일 개수 : letterFiles.size()=" + letterFiles.size()); 
		//이력서자소서첨부 파일을 넣지 않았는데 legeerFiles의 사이즈가 1 나오는걸로 판별 x getSize의 크기나 파일 이름의 이름을 봐야함 (무조건 1개 이상으로 나옴)
		logger.info("파일 크기 : imageFile.getSize()=" + imageFile.getSize() + ", 업로드된 파일 이름 : imageFile.getOriginalFileName()=" + imageFile.getOriginalFilename());
		logger.info(greeting);

		//게시글내용 DB에 저장
		try {
			//			String loginedId = (String)session.getAttribute("loginInfo");
			//---로그인대신할 샘플데이터--
			String loginedId = "id1";
			//----------------------
			board.setBoardId(loginedId);
			service.writeBoard(board);
			//			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AddException e1) {
			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//		//파일 경로 생성
		//String saveDirectory = "c:\\files";
		String saveDirectory = sc.getInitParameter("filePath");
		if ( ! new File(saveDirectory).exists()) {
			logger.info("업로드 실제경로생성");
			new File(saveDirectory).mkdirs();
		}

		int wroteBoardNo = board.getBoardNo();//저장된 글의 글번호

		//letterFiles 저장
		int savedletterFileCnt = 0;//서버에 저장된 파일수
		if(letterFiles != null) {
			for(MultipartFile letterFile: letterFiles) {
				long letterFileSize = letterFile.getSize();
				//파일을 첨부하지 않았을 때 = 0
				if(letterFileSize > 0) {
					String letterOriginFileName = letterFile.getOriginalFilename(); //자소서 파일원본이름얻기
					//지원서 파일들 저장하기
					logger.info("지원서 파일이름: " + letterOriginFileName +" 파일크기: " + letterFile.getSize());
					//저장할 파일이름을 지정한다 ex) 글번호_letter_XXXX_원본이름
					String letterfileName = wroteBoardNo + "_letter_" + UUID.randomUUID() + "_" + letterOriginFileName;
					File savevdLetterFile = new File(saveDirectory, letterfileName);//파일생성
					try {
						//savevdLetterFile가 저장될 파일이름
						FileCopyUtils.copy(letterFile.getBytes(), savevdLetterFile);
						logger.info("지원서 파일저장:" + savevdLetterFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					savedletterFileCnt++;
				}//end if(letterFileSize > 0)
			}
		}//end if(letterFiles != null)
		logger.info("저장된 letter 파일개수: " + savedletterFileCnt);
		//		return new ResponseEntity<>(HttpStatus.OK);

		File thumbnailFile = null;
		long imageFileSize = imageFile.getSize();
		int imageFileCnt = 0;//서버에 저장된 이미지파일수

		if(imageFileSize > 0) {
			//이미지파일 저장하기
			String imageOrignFileName = imageFile.getOriginalFilename(); //이미지파일원본이름얻기
			logger.info("이미지 파일이름:" + imageOrignFileName +", 파일크기: " + imageFile.getSize());

			//저장할 파일이름을 지정한다 ex) 글번호_image_XXXX_원본이름
			String imageFileName = wroteBoardNo + "_image_" + UUID.randomUUID() + "_" + imageOrignFileName;
			//이미지파일생성
			File savedImageFile = new File(saveDirectory, imageFileName);

			try {
				FileCopyUtils.copy(imageFile.getBytes(), savedImageFile);
				logger.info("이미지 파일저장:" + savedImageFile.getAbsolutePath());

				//파일형식 확인
				String contentType = imageFile.getContentType();
				if(!contentType.contains("image/")) { //이미지파일형식이 아닌 경우
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				//이미지파일인 경우 섬네일파일을 만듦
				String thumbnailName =  "s_"+imageFileName; //섬네일 파일명은 s_글번호_XXXX_원본이름
				thumbnailFile = new File(saveDirectory,thumbnailName);
				FileOutputStream thumbnailOS;
				thumbnailOS = new FileOutputStream(thumbnailFile);
				InputStream imageFileIS = imageFile.getInputStream();
				int width = 100;
				int height = 100;
				Thumbnailator.createThumbnail(imageFileIS, thumbnailOS, width, height);
				logger.info("섬네일파일 저장:" + thumbnailFile.getAbsolutePath() + ", 섬네일파일 크기:" + thumbnailFile.length());

				//이미지 썸네일다운로드하기
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set(HttpHeaders.CONTENT_LENGTH, thumbnailFile.length()+"");
				responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(thumbnailFile.toPath()));
				responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+URLEncoder.encode("a", "UTF-8"));
				logger.info("섬네일파일 다운로드");
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbnailFile), 
						responseHeaders, 
						HttpStatus.OK);

			} catch (IOException e2) {
				e2.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}//end if(imageFileSize > 0) 
		else {
			logger.error("이미지파일이 없습니다");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}