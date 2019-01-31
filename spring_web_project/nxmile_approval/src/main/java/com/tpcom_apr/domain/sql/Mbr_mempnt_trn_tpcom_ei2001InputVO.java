package com.tpcom_apr.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mbr_mempnt_trn_tpcom_ei2001InputVO {
    private Long cur_pnt;
    private Long avl_pnt;
    private String organ_cd;
    private String mbrsh_pgm_id;
    private String mbr_id;
    private String pnt_knd_cd;
}

