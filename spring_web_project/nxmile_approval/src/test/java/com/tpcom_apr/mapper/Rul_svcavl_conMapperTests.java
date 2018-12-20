package com.tpcom_apr.mapper;

import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001InputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001OutputVO;
import jdk.nashorn.internal.objects.NativeJSON;
import lombok.Setter;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Supplier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log
public class Rul_svcavl_conMapperTests {

    @Setter(onMethod_= {@Autowired})
    Rul_svcavl_conMapper rul_svcavl_conMapper;

    @Test
    public void rul_svcavl_conMapperTests() {
        Rul_svcavl_con_tpcom_vs2001OutputVO outputVO = rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                new Rul_svcavl_con_tpcom_vs2001InputVO("5004", "K110", "ZPTUTXPTC0001", "ON"));
        log.info("Rul_svcavl_con_tpcom_vs2001OutputVO : " + outputVO);
    }
}
