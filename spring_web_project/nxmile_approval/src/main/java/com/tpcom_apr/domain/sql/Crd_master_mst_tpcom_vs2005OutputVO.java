package com.tpcom_apr.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crd_master_mst_tpcom_vs2005OutputVO {
    private String mbr_id;
    private String coopco_cd;
    private String coop_crd_cd;
    private String issu_fg;
    private String issu_dy;
    private String crd_sts;
    private String co_rep_crd_yn;
    private String fir_mcht_sale_dy;
    private String last_mcht_sale_dy;
    private String fir_cpn_sav_dy;
    private String only_coopco_cd;
    private String only_dy;
    private String cs_dy;
    private String cs_coopco_cd;
}
