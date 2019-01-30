package com.tpcom_apr.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetaprvnoOutputVO {
    private String aprv_dy;
    private String aprv_tm;
    private String aprv_no;
    private String rep_aprv_no;
}
