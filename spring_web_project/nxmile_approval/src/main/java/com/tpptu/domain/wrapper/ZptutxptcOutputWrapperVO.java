package com.tpptu.domain.wrapper;

import com.commons.domain.CustomizeHeaderVO;
import com.tpptu.domain.ZptutxptcOutputVO;
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
    private int totalBodyCnt;
}
