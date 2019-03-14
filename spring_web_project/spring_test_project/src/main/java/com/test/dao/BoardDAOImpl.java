package com.test.dao;

import com.test.dao.daoInterface.BoardDAO;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Setter(onMethod_ = {@Autowired})
    private SqlSession sqlSession;

    @Override
    public String getTime() {
        return sqlSession.selectOne("com.test.mapper.BoardMapper.getTime");
    }

    @Override
    public String getReplyer(int bno) {
        return sqlSession.selectOne("com.test.mapper.BoardMapper.getReplyer", bno);
    }
}
