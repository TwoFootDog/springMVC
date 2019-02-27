package com.tpptu.domain;

import com.commons.domain.CustomizeHeaderVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZptutxptcOutputWrapperVO {
    private CustomizeHeaderVO header;
    private List<ZptutxptcOutputVO> body;
    private int totalCnt;
    private boolean isSuccess;
}
