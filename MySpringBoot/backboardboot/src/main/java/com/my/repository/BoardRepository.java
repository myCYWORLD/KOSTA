package com.my.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.dto.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	List<Board> findAll(org.springframework.data.domain.Pageable paging);
	@Query(value="SELECT *\r\n"
			+ "FROM (\r\n"
			+ "  SELECT rownum r, a.*\r\n"
			+ "  FROM (SELECT level,\r\n"
			+ "  board_no, board_parent_no, board_title, board_content, board_id,\r\n"
			+ "  board_viewcount, board_dt\r\n"
			+ "  FROM board_jpa\r\n"
			+ "  START WITH board_parent_no = 0\r\n"
			+ "  CONNECT BY PRIOR board_no = board_parent_no\r\n"
			+ "  ORDER SIBLINGS BY board_no DESC\r\n"
			+ "  ) a\r\n"
			+ ")\r\n"
			+ "WHERE r BETWEEN ?1 AND ?2"
			,nativeQuery = true)
	List<Board> findByPage(int startRow, int endRow);

//	@Query(value = "DELETE FROM board_jpa\r\n"
//			+ "WHERE board_no IN ( SELECT board_no\r\n"
//			+ "                    FROM board_jpa\r\n"
//			+ "                    START WITH board_parent_no = ?1\r\n"
//			+ "                    CONNECT BY PRIOR board_no = board_parent_no)"
//			, nativeQuery = true)
//	void deleteReply(Long boardNo);
	
	@Query(value = "SELECT *\r\n"
			+ "        FROM (\r\n"
			+ "        SELECT rownum r, a.*\r\n"
			+ "        FROM (SELECT level,\r\n"
			+ "        board_no, board_parent_no, board_title, board_content, board_id,\r\n"
			+ "        board_dt, board_viewcount\r\n"
			+ "        FROM board_jpa\r\n"
			+ "        WHERE board_title LIKE %?1% OR board_id LIKE %?1%\r\n"
			+ "        START WITH board_parent_no = 0\r\n"
			+ "        CONNECT BY PRIOR board_no = board_parent_no\r\n"
			+ "        ORDER SIBLINGS BY board_no DESC\r\n"
			+ "        ) a\r\n"
			+ "        )\r\n"
			+ "        WHERE r BETWEEN ?2 AND ?3"
			,nativeQuery = true)
	List<Board> findByWord (String word, int startRow, int EndRow);

	@Query(value = "SELECT COUNT(*) FROM board_jpa WHERE board_title LIKE %?1% OR board_id LIKE %?1%"
			, nativeQuery = true)
	int findByCount(String word);
	
	/**
	 * @Query ????????????????????? DML??? ????????? ????????? 
	 * ????????? @Modify ?????????????????? ????????????
	 * 
	 * ????????? ????????????, ???????????? ????????????
	 * @param boardNo
	 */
	
	@Modifying
	@Query(value = "DELETE FROM board_jpa\r\n"
			+"WHERE board_no IN ( SELECT  board_no\r\n"
            +"      			  FROM board\r\n"
            +"       			  START WITH board_parent_no = ?1\r\n"
            +"        			  CONNECT BY PRIOR board_no = board_parent_no)"
     ,nativeQuery = true)
	void deleteReply(Long boardNo);
	
}
