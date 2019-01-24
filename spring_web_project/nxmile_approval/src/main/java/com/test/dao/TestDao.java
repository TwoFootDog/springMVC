package com.test.dao;

import com.test.domain.BoardVO;
import com.test.mapper.TestMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Log4j
public class TestDao {

    @Setter(onMethod_= {@Autowired})
    private TestMapper testMapper;

    BoardVO boardVO;

    public BoardVO getBoardInfo(Long bno) {

        beforeFunction();

        boardVO = testMapper.getBoard(bno);

        afterFunction();

        return boardVO;

    }

    public void beforeFunction() {
        log.info("beforeFunction-----------------------------------");
    }
    public void afterFunction() {}


}
