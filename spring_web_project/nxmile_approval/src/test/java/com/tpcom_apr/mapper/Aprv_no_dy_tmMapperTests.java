package com.tpcom_apr.mapper;



import com.tpcom_apr.domain.sql.Aprv_dy_tm_tpcom_vs2001OutputVO;
import com.tpcom_apr.domain.sql.Aprv_no_ocboff_tpcom_vs_2001OutputVO;
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
public class Aprv_no_dy_tmMapperTests {
    @Setter(onMethod_ = {@Autowired})
    Aprv_no_dy_tmMapper aprv_no_dy_tmMapper;

    @Test
    public void Aprv_dy_tm_tpcom_vs2001Tests() {
         Aprv_dy_tm_tpcom_vs2001OutputVO output = aprv_no_dy_tmMapper.aprv_dy_tm_tpcom_vs2001();
         log.info("result = " + output);
    }

    @Test
    public void Aprv_no_ocboff_tpcom_vs2001Tests(){
        Aprv_no_ocboff_tpcom_vs_2001OutputVO output = aprv_no_dy_tmMapper.aprv_no_ocboff_tpcom_vs2001();
        log.info("result = " + output);
    }
}
