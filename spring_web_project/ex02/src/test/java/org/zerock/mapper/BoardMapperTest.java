package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	
	@Test
	public void testGetList() {
		boardService.getList(new Criteria(1,3)).forEach(board -> log.info(board));
	}

	@Test
	public void testPaging() {
		Criteria cri = new Criteria(2, 5);
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
/*	@Test
	public void testGetList() {
		boardMapper.getList().forEach(board -> log.info(board));
	}

	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성한 내용");
		board.setWriter("newbie");
		boardMapper.insert(board);
		log.info(board);
	}

	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성한 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		boardMapper.insert(board);
		log.info(board);
	}

	@Test
	public void testRead() {
		BoardVO board = boardMapper.read(5L);
		log.info(board);
	}

	@Test
	public void testDelete() {
		log.info("Delete count : " + boardMapper.delete(5L));
	}*/
	
/*	@Test
	public void testUpdate() { 
		BoardVO board = new BoardVO();
		board.setBno(6L);
		board.setTitle("수정제목1");
		board.setContent("수정 내용");
		board.setWriter("수정자1");
		
		int count = boardMapper.update(board);
		log.info("update count : " + count);
	}*/
}
