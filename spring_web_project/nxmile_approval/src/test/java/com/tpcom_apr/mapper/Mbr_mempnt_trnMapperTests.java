package com.tpcom_apr.mapper;


import com.tpcom_apr.domain.sql.Mbr_mempnt_trn_tpcom_ei2001InputVO;
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
public class Mbr_mempnt_trnMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private Mbr_mempnt_trnMapper mbr_mempnt_trnMapper;

    @Test
    public void mbr_mempnt_trn_tpcom_ei2001Tests() {
        int result = mbr_mempnt_trnMapper.mbr_mempnt_trn_tpcom_ei2001(
                        new Mbr_mempnt_trn_tpcom_ei2001InputVO(0L,10000L,"950S","A","111111111","O11"));
        log.info("Result =================== " + result);
    }
}
