package com.test.service;

import com.test.persistent.BoardDAO;
import com.test.service.serviceInterface.BoardService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = {@Autowired})
    private BoardDAO boardDAO;

    @Override
    public String selectTime() {
        return boardDAO.getTime();
    }

    @Override
    public String selectReplyer(int bno) {
        return boardDAO.getReplyer(bno);
    }
}
