package com.commons.domain;

import com.tpcom_apr.domain.service.CntrinsertInputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorOutputWrapperVO {
    private CustomizeHeaderVO header;
    private List<Object> body;
    private int totalBodyCnt;
}
