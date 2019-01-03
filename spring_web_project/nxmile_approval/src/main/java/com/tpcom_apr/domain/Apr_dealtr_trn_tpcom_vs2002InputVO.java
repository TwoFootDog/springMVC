package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apr_dealtr_trn_tpcom_vs2002InputVO {
    private String mbrsh_pgm_id;
    private String orgn_deal_dy;
    private String crd_no;
    private String mcht_no;
    private String orgn_deal_coopco_aprv_no;
    private String orgn_deal_aprv_no;
    private Long orgn_deal_amt;
    private String orgn_cd;
    private String svc_modu_id;
}
