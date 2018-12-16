package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    private Long[] bnoArr = {6L, 7L, 8L, 9L, 10L};

    @Test
    public void testUpdate() {
        Long targetRno = 3002L;
        ReplyVO replyVO = replyMapper.read(3002L);
        replyVO.setReply("댓글테스트 수정본");
        int count = replyMapper.update(replyVO);
        log.info("update count : " + count);
    }


    @Test
    public void testDelete() {
        Long targetRno = 3001L;
        replyMapper.delete(targetRno);
    }

    @Test
    public void testRead() {
        Long targetRno = 3001L;
        ReplyVO replyVO = replyMapper.read(targetRno);
        log.info(replyVO);
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1,10).forEach(i->{
            ReplyVO replyVO = new ReplyVO();
            replyVO.setBno(bnoArr[i%5]);
            replyVO.setReply("댓글테스트" + i);
            replyVO.setReplyer("댓글테스터" + i);
            replyMapper.insert(replyVO);
    });
}

    @Test
    public void testMapper() {
        log.info(replyMapper);
    }

}
