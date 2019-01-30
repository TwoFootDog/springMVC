package com.tpcom_apr.mapper;


import com.tpcom_apr.domain.sql.*;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class Apr_dealtr_trnMapperTests {

    @Setter(onMethod_ = {@Autowired})
    Apr_dealtr_trnMapper apr_dealtr_trnMapper;

    @Test
    public void apr_dealtr_trn_tpcom_vs2001Tests() {
        Apr_dealtr_trn_tpcom_vs2001OutputVO outputVO =
                apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vs2001(
                        new Apr_dealtr_trn_tpcom_vs2001InputVO("A", "20190103", "1111111111111111", "99999999", "N", "ZRCPTXPTC0001", 10000L, "F88888888", "")
                );
    }

    @Test
    public void apr_dealtr_trn_tpcom_vs2002Tests() {
        Apr_dealtr_trn_tpcom_vs2002OutputVO outputVO =
                apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vs2002(
                        new Apr_dealtr_trn_tpcom_vs2002InputVO("A", "20190103", "2200111133331352", "89004518", "", "F88405584", 10000L, "5004", "ZPTUTXPTC0001"
                        )
                );

    }

    @Test
    public void apr_dealtr_trn_tpcom_vf2001Tests() {
        Apr_dealtr_trn_tpcom_vf2001OutputVO outputVO =
                apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vf2001(
                        new Apr_dealtr_trn_tpcom_vf2001InputVO("A","F88405584","20190103","11","0360184577")
                );
    }
}
