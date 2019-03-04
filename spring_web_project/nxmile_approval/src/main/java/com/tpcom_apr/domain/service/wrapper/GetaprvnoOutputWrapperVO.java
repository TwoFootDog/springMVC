package com.tpcom_apr.domain.service.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetaprvnoOutputWrapperVO {
    private CustomizeHeaderVO header;
    private GetaprvnoOutputVO body;
}
