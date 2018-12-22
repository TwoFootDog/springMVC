package com.tpptu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZptutxptcInputVO {
    private long grid_cnt1;
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
    private String orgn_deal_amt;

}
