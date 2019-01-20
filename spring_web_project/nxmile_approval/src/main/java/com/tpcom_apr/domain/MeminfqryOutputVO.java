package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeminfqryOutputVO {
    private String mbr_id;
    private String coopco_cd;
    private String coop_crd_cd;
    private String crd_typ;
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
//    private String resd_no;
//    private String co_no;
//    private String prn_biz_no;
//    private String mbr_fg;
//    private String fam_grp_mbr_id;
//    private String fir_mcht_sale_dy_det;
//    private String last_mcht_sale_dy_det;
//    private String fir_cpn_sav_dy_det;
//    private String only_coopco_cd_det;
//    private String only_dy_det;
//    private String cs_dy_det;
//    private String cs_coopco_cd_det;
//    private String treg_yn;
//    private String mbr_sts;
//    private String mbr_hnm;

    public MeminfqryOutputVO(Crd_master_mst_tpcom_vs2005OutputVO crd) {
        this.mbr_id = crd.getMbr_id();
        this.coopco_cd = crd.getCoopco_cd();
        this.coop_crd_cd = crd.getCoop_crd_cd();
        this.issu_fg = crd.getIssu_fg();
        this.issu_dy = crd.getIssu_dy();
        this.crd_sts = crd.getCrd_sts();
        this.co_rep_crd_yn = crd.getCo_rep_crd_yn();
        this.fir_mcht_sale_dy = crd.getFir_mcht_sale_Dy();
        this.last_mcht_sale_dy = crd.getLast_mcht_sale_dy();
        this.fir_cpn_sav_dy = crd.getFir_cpn_sav_dy();
        this.only_coopco_cd = crd.getOnly_coopco_cd();
        this.only_dy = crd.getOnly_dy();
        this.cs_dy = crd.getCs_dy();
        this.cs_coopco_cd = crd.getCs_coopco_cd();
    }

}

