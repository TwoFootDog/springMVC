package com.test.dao;

import com.test.domain.BoardVO;
import com.test.mapper.TestMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Setter(onMethod_= {@Autowired})
    private TestMapper testMapper;

    BoardVO boardVO;

    public BoardVO getBoardInfo(Long bno) {

        boardVO = testMapper.getBoard(bno);

        return boardVO;
    }
}
