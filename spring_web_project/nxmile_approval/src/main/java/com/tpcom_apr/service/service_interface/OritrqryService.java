package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.OritrqryInputVO;
import com.tpcom_apr.domain.service.OritrqryOutputVO;
import com.tpcom_apr.domain.service.wrapper.OritrqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OritrqryOutputWrapperVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OritrqryService {
    public OritrqryOutputWrapperVO syncCall(OritrqryInputWrapperVO inputWrapperVO);
    public int sqlTypeSetting(OritrqryInputVO inputVO);
    public Map<String, String> changeOrgnAprvNo(String orgn_deal_aprv_no, String orgn_deal_coopco_aprv_no);
}
