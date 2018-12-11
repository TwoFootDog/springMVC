package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

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
		board.setTitle("���� �����߽��ϴ�");
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
		board.setTitle("���� �ۼ��ϴ� ��");
		board.setContent("���� �ۼ��ϴ� ����");
		board.setWriter("����");
		boardService.register(board);
		log.info("������ �Խù��� ��ȣ : " + board.getBno());
	}*/

/*	@Test
	public void testExist() {
		log.info(boardService);
		assertNotNull(boardService);
	}*/
}
