package com.tpptu.domain;

import com.commons.domain.CustomizeHeaderVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZptutxptcInputWrapperVO {
    private CustomizeHeaderVO header;
    private List<ZptutxptcInputVO> body;
    private int totalBodyCnt;
}
