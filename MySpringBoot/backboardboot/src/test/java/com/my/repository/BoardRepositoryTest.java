package com.my.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.my.dto.Board;
@SpringBootTest
class BoardRepositoryTest {
	@Autowired
	private BoardRepository repository;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	void testFindByIdValid() {
		Long boardNo = 1L;
		//게시글 번호의 게시글 조회한다
		Optional<Board> optB1 = repository.findById(boardNo);
		assertTrue(optB1.isPresent());
	}

	@Test
	void testFindByIdInvalid() {
		Long boardNo = 1L;
		//게시글 번호의 게시글 조회한다
		Optional<Board> optB1 = repository.findById(boardNo);
		assertFalse(optB1.isPresent());
	}
	
	@Test
	@Transactional
	void testWrite() {
		Board b = new Board();
		b.setBoardTitle("title_t1");
		b.setBoardContent("content_t1");
		b.setBoardId("id1");
//		b.setBoardParentNo(0);
		repository.save(b);
	}
	@Test
	void testReply() {
		Board b = new Board();
		b.setBoardParentNo(1L);
		b.setBoardTitle("1_re_title");
		b.setBoardContent("1_re_content");
		b.setBoardId("id2");
		repository.save(b);
	}
	
	@Test
	void testModify() {
//		Board board = new Board();
//		board.setBoardNo(2L);
//		board.setBoardContent("글2내용수정");
//		repository.(board);
//		고정된 sql구문으로 update된 후에 내용 말고 다른 값들이 다 null로 됨 -> @DynamicUpdate를 사용
		Optional<Board>optB = repository.findById(3L);
		optB.ifPresent((b)->{ //b의 자료형이 Board타입
			b.setBoardContent("글3내용수정"); //그 중 글 내용만 바꾸겠다
			repository.save(b); //기존 내용은 놔두고 바뀐 내용의 멤버변수만 set
		});
	} 
	
	@Test
	void testUpdateViewCount() {
		Long boardNo = 1L;
		Optional<Board> optB = repository.findById(boardNo);
		optB.ifPresent((b)->{
			logger.error(b.toString());
			int oldViewCount = b.getBoardViewcount();
			int newViewCount = oldViewCount+1;
			b.setBoardViewcount(newViewCount);
			repository.save(b);
			
			int expectedNewViewCount = newViewCount;
			assertEquals(expectedNewViewCount, repository.findById(boardNo).get().getBoardViewcount());
		});
	}
	
	@Test
	void testDelete() {
		Long boardNo = 1L;
		repository.deleteReply(boardNo);
		repository.deleteById(boardNo);
		
		assertFalse(repository.findById(boardNo).isPresent());
	}
	
	@Test
	void testFindAllPage() {
		int currentPage = 1;
		Pageable pageable = PageRequest.of(currentPage-1, 3);
		List<Board> list = repository.findAll(pageable);
		list.forEach((b)->{
			logger.error(b.toString());
		});
	}
	
	@Test
	void testFindByPage() {
		int currentPage = 1;
		int cntPerPage = 3;
		int endRow = currentPage * cntPerPage;
		int startRow = endRow - cntPerPage + 1;
		List<Board> list = repository.findByPage(startRow, endRow);
		list.forEach((b)->{
			logger.error(b.toString());
		});
	}
	
	@Test
	void testFindByWord() {
		String word = "1";
		int currentPage = 1;
		int cntPerPage = 3;
		int endRow = currentPage * cntPerPage;
		int startRow = endRow - cntPerPage + 1;
		List<Board> list = repository.findByWord(word, startRow, endRow);
		list.forEach((b)->{
			logger.error(b.toString());
		});
	}
	
	@Test
	void testFindByCount() {
		String word = "1";
		int count = repository.findByCount(word);
	}
}

