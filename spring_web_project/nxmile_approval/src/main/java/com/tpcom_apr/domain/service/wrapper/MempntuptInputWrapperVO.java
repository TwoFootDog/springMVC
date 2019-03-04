package com.tpcom_apr.domain.service.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.MempntuptInputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MempntuptInputWrapperVO {
    private CustomizeHeaderVO header;
    private MempntuptInputVO body;
}
