package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OritrqryInputVO {
    private String svc_modu_id;
    private String trc_no;
    private String mbrsh_pgm_id;
    private String mcht_no;
    private String crd_no;
    private String orgn_Deal_dy;
    private String orgn_deal_aprv_no;
    private String orgn_deal_coopco_aprv_no;
    private Long orgn_deal_amt;
    private String mix_sttl_yn;
    private String ans_cd;
}
