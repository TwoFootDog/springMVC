package com.tpptu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZptutxptcInputVO {
    private Long grid_cnt1;
    private String mbrsh_pgm_id;
    private String mbrsh_svc_fg;
    private String ter_fg;
    private String ter_no;
    private String wcc;
    private String mcht_no;
    private String mcht_biz_no;
    private String deal_dy;
    private String deal_tm;
    private String track_ii_data;
    private String resd_no;
    private String orgn_deal_dy;
    private String orgn_deal_aprv_no;
    private String orgn_deal_coopco_aprv_no;
    private String msg_1;
    private String msg_2;
    private String msg_3;
    private String msg_4;
    private String slp_cd;
    private String deal_fg;
    private String mix_sttl_yn;
    private Long orgn_deal_amt_pnt;
    private String cash_arcpt_proc_fg;
    private String deal_caus_cd;
    private String bat_file_nm;
    private String web_svc_call_fg;
}
