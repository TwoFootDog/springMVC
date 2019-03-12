package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.OritrqryInputVO;
import com.tpcom_apr.domain.service.wrapper.OritrqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OritrqryOutputWrapperVO;

import java.util.Map;

public interface OritrqryService {
    public OritrqryOutputWrapperVO syncCall(OritrqryInputWrapperVO inputWrapperVO);
    public int sqlTypeSetting(OritrqryInputVO inputVO);
    public Map<String, String> changeOrgnAprvNo(String orgn_deal_aprv_no, String orgn_deal_coopco_aprv_no);
}
