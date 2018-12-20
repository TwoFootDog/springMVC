package com.tpptu.mapper;

import com.tpptu.mapper.TimeMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class MapperTests {

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;
    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;
    @Setter(onMethod_ = {@Autowired})
    private TimeMapper timeMapper;

    @Test
    public void testMyBatis() {
        try (SqlSession session = sqlSessionFactory.openSession();
        Connection con = session.getConnection();
        ) {
            log.info(session);
            log.info(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTime() {
        log.info("TimeMapper Class Name : " + timeMapper.getClass().getName());
        log.info("getTime : " + timeMapper.getTime());
    }

    @Test
    public void testGetReplyer() {
        log.info("testReplyer : " + timeMapper.getReplyer(3002L));
    }
}
