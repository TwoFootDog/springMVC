package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardService boardService;

	@Test
	public void testDelete() {
		log.info("Remove Result : " + boardService.remove(5L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = boardService.get(4L);
		if (board == null) {
			return;
		}
		board.setTitle("제목 수정했습니다");
		log.info("modify result : " + boardService.modify(board));
	}
	
/*	@Test
	public void testGet() {
		log.info(boardService.get(5L));
	}*/
	
/*	@Test
	public void testGetList() {
		boardService.getList().forEach(board->log.info(board));
	}*/
	
/*	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("신인");
		boardService.register(board);
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}*/

/*	@Test
	public void testExist() {
		log.info(boardService);
		assertNotNull(boardService);
	}*/
}
