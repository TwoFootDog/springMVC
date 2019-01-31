package com.tpcom_apr.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MempntuptInputVO {
    private Long cur_pnt;
    private Long avl_pnt;
    private String mbrsh_pgm_id;
    private String mbr_id;
    private String fam_grp_mbr_id;
    private String pnt_knd_cd;
    private String organ_cd;
    private String aprv_no;
}
