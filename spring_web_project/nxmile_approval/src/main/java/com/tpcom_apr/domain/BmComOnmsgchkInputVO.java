package com.tpcom_apr.domain;

import lombok.Data;

@Data
public class BmComOnmsgchkInputVO {
    private String svc_modu_id;
    private String telgrm_no;
    private String organ_cd;
    private String trc_no;
    private String mcht_no;
    private String mcht_biz_no;
    private String deal_dy;
    private String crd_no;
    private String resd_no;
    private String orgn_deal_dy;
    private String orgn_deal_aprv_no;
    private String orgn_deal_coopco_aprv_no;
    private Double deal_amt_sum;
    private Double deal_amt1;
    private Double orgn_deal_amt;
    private String cncl_req_fg;
    private String aprv_no;
    private String ans_cd1;
    private String telgrm_fg;
    private String valid_chk_yn;
}
