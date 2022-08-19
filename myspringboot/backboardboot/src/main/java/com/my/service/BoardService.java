package com.my.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.repository.BoardRepository;
@Service
public class BoardService {
	private static final int CNT_PER_PAGE = 3; //페이지별 보여줄 목록수
	@Autowired
	private BoardRepository repository;
	/**
	 * 페이지별 게시글 목록과 페이지그룹정보를 반환한다 
	 * @param currentPage 검색할 페이지
	 * @return 
	 * @throws FindException
	 */
	public PageBean<Board> boardList(int currentPage) throws FindException{
		//		List<Board>list = repository.selectByPage(currentPage, CNT_PER_PAGE);
		//		int totalCnt = repository.selectCount();//총 행수 12, 13
		//		int cntPerPageGroup = 2;//페이지별 보여줄 페이지수
		//		PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);

		//
		//		return pb1;
		//		Pageable pageable = PageRequest.of(currentPage, CNT_PER_PAGE, Direction.ASC);
		//		repository.findAll(pageable);
		//		return null;
		int endRow = currentPage * CNT_PER_PAGE;
		int startRow = endRow - CNT_PER_PAGE + 1;
		List<Board>list = repository.findByPage(startRow, endRow);
		Long totalCnt = repository.count();
		int cntPerPageGroup = 2; //페이지별 보여줄 페이지수
		PageBean<Board> pb = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);
		return pb;
	}

	/**
	 * 검색어를 이용한 게시글 검색 목록과 페이지 그룹정보를 반환한다
	 * @param word 검색어
	 * @param currentPage 검색할 페이지
	 * @return
	 * @throws FindException
	 */
	public PageBean<Board> searchBoard(String word, int currentPage) throws FindException{
		//List<Board> list = repository.selectByWord(word, currentPage, CNT_PER_PAGE);
		//int totalCnt = repository.selectCount(word);
		//int cntPerPageGroup = 2;
		//PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);
		//return pb1;
		List<Board>list = repository.findByWord(word, currentPage, CNT_PER_PAGE);
		int totalCnt = repository.findByCount(word);
		int cntPerPageGroup = 2;
		PageBean<Board> pb1 = new PageBean<>(list, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);
		return pb1;
	}

	/**
	 * 게시글번호의 조회수를 1증가한다
	 * 게시글번호의 게시글을 반환한다
	 * @param boardNo 게시글번호
	 * @return
	 * @throws FindException
	 */
	public Board viewBoard(Long boardNo) throws FindException{
		//조회수를 1증가한다
		//Board b = new Board(); 
		//b.setBoardNo(boardNo);
		//b.setBoardViewcount(-1);
		//repository.update(b);
		Optional<Board> optB = repository.findById(boardNo);  //우선 조회를 한 번 한다.
		if(optB.isPresent()) {
			Board b = optB.get();
			b.setBoardViewcount(b.getBoardViewcount()+1);
			repository.save(b);				
		}else {
			throw new FindException("게시글이 없습니다");
		}
		//게시글번호의 게시글 조회한다
		//Board b1 = repository.selectByBoardNo(boardNo);
		Optional<Board> optB1 = repository.findById(boardNo);
		if(optB1.isPresent()) {
			Board b1 = optB1.get();
			return b1;
		}else {
			throw new FindException("게시글이 없습니다");
		}
	}

	/**
	 * 글쓰기
	 * @param board
	 * @throws AddException
	 */
	public void writeBoard(Board board) throws AddException{
		//			board.setBoardParentNo(0);
		repository.save(board);
	}

	/**
	 * 답글쓰기
	 * @param board
	 * @throws AddException
	 */
	public void replyBoard(Board board) throws AddException{
		//답글을 쓸 때 부모글 번호가 있어야 함
		if(board.getBoardParentNo() == 0L) {
			throw new AddException("글이 없습니다");
		}
		repository.save(board);
	}
	//글쓰기와 답글쓰기 둘 다 insert지만 usecase별로 메서드 생성



	/**
	 * 글 수정하기
	 * @param board
	 * @throws ModifyException
	 */
	public void modifyBoard(Board board) throws ModifyException{
		//			repository.update(board);
		Optional<Board> optB = repository.findById(board.getBoardNo());
		if(!optB.isPresent()) {
			throw new ModifyException("글이 없습니다");
		}else {
			Board b = optB.get();
			b.setBoardTitle(board.getBoardTitle());
			b.setBoardContent(board.getBoardContent());
			repository.save(b);
		}
	}
	//			optB.ifPresent((b)-> {
	//				
	//			}); //위 if문과 같은 구문, 람다식으로 작성해줘야함
	//repository.save(board);

	/**
	 * 글 삭제하기
	 * @param boardNo
	 * @throws RemoveException
	 */
	/**
	 * 삭제하기
	 * @param boardNo
	 * @throws FindException
	 */
	@Transactional
	public void removeBoard(Long boardNo) throws RemoveException{
		repository.deleteReply(boardNo);
		repository.findById(boardNo).orElseThrow(() ->  new RemoveException("글이 없습니다"));
		repository.deleteById(boardNo);
	}
}