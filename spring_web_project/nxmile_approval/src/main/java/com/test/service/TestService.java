package com.test.service;

import com.test.dao.TestDao;
import com.test.domain.BoardVO;

import com.test.mapper.TestMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Log4j
public class TestService {

    @Setter(onMethod_ = {@Autowired})
    private TestDao dao;

    BoardVO boardVO;

    public BoardVO getBoardInfo(Long bno) {



        boardVO = dao.getBoardInfo(bno);
        log.info("aaaaaaaaaaaa" + boardVO);
        if(!StringUtils.isEmpty(boardVO)) {
            serviceBefore();
            return boardVO;
        }else {
            throw new RuntimeException();
        }
    }

    public void serviceBefore(){
        log.info("====================serviceBefore====================");
    };

    public boolean numberCompare(int number1, int number2) {
        if (number1 == number2) {
            return true;
        } else {
            return false;
        }
    }
}
