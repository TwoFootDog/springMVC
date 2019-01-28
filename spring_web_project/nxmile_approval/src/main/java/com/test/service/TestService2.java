package com.test.service;

import com.test.dao.TestDao;
import com.test.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Log4j
public class TestService2 {

    @Setter(onMethod_ = {@Autowired})
    private TestDao dao;

    BoardVO boardVO;
    int age;

    public BoardVO getBoardInfo(Long bno) {

        serviceBefore();
        boardVO = dao.getBoardInfo(bno);
        if (!StringUtils.isEmpty(boardVO)) {
            return boardVO;
        } else {
            throw new RuntimeException();
        }
    }

    public int getAge(int age) {
        this.age = age + 1;
        return age;
    }

    public void setAge(int age) {
        this.age = age + 2;
    }

    public void serviceBefore() {
        log.info("====================serviceBefore====================");
    }

}
