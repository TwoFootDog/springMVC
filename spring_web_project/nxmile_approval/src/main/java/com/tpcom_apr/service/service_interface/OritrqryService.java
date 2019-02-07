package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.OritrqryInputVO;
import com.tpcom_apr.domain.service.OritrqryOutputVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OritrqryService {
    public ResponseEntity<OritrqryOutputVO> syncCall(HttpHeaders requestHeaders, OritrqryInputVO inputVO);
    public int sqlTypeSetting(HttpHeaders requestHeaders, OritrqryInputVO inputVO);
    public Map<String, String> changeOrgnAprvNo(String orgn_deal_aprv_no, String orgn_deal_coopco_aprv_no);
}
