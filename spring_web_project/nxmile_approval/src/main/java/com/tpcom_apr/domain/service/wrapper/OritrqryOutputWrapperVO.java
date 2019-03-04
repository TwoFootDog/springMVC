package com.tpcom_apr.domain.service.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.OritrqryOutputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OritrqryOutputWrapperVO {
    private CustomizeHeaderVO header;
    private OritrqryOutputVO body;
}
