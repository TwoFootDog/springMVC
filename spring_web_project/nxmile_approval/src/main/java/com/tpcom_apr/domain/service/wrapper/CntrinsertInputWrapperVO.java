package com.tpcom_apr.domain.service.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.CntrinsertInputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CntrinsertInputWrapperVO {
    private CustomizeHeaderVO header;
    private CntrinsertInputVO body;
}
