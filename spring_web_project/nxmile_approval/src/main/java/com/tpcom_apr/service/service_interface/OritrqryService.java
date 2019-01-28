package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OritrqryService {
    public ResponseEntity<OritrqryOutputVO> syncCall(HttpServletRequest request, OritrqryInputVO inputVO);
    public int sqlTypeSetting(OritrqryInputVO inputVO);
    public Map<String, String> changeOrgnAprvNo(String orgn_deal_aprv_no, String orgn_deal_coopco_aprv_no);
}
