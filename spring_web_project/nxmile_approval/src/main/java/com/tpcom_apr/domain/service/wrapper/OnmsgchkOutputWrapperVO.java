package com.tpcom_apr.domain.service.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.OnmsgchkOutputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnmsgchkOutputWrapperVO {
    private CustomizeHeaderVO header;
    private OnmsgchkOutputVO body;
}
