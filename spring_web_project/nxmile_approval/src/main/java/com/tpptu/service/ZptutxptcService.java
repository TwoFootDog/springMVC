package com.tpptu.service;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.service.BmComOnmsgchkModule;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZptutxptcService {

    @Setter(onMethod_ = {@Autowired})
    BmComOnmsgchkModule onmsgchkModule;

    public ZptutxptcOutputVO synCall(ZptutxptcInputVO InputVo) {

    }
}
