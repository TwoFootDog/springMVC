package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OritrqryOutputVO {
    private String mbrsh_pgm_id;    // 멤버쉽프로그램ID
    private String aprv_Dy;         // 승인일자
    private String aprv_no;         // 승인번호
    private String crd_no;          // 카드번호
    private String aprv_tm;         // 승인시간
    private String deal_dy;         // 거래일자
    private String mcht_no;         // 가맹점번호
    private String fam_rep_mbr_id;  // 가족대표회원ID
    private String mbr_id;          // 회원ID
    private String slp_cd;          // 전표코드
    private String deal_fg;         // 거래구분
    private String deal_tm;         // 거래시간
    private String prz_cd;          // 사은품코드
    private String oilcls_cd;       // 유종코드
    private Double sale_qty;        // 판매수량
    private Double sale_prc;        // 매출단가
    private Long deal_amt;          // 거래금액
    private Long dsc_amt;           // 할인금액
    private Long mcht_pnt;          // 가맹점포인트
    private Long cur_pnt;           // 현재포인트
    private Long avl_pnt;           // 가용포인트
    private String pnt_knd_cd;      // 포인트종류코드
    private Long annfee;            // 연회비
    private Long mbrsh_svc_annfee;
    private Long refu_lmt_inc_amt;
    private Long deal_bef_cur_pnt;
    private Long deal_bef_avl_pnt;
    private Long deal_bef_annfee;
    private Long deal_bef_mbrsh_svc_annfee;
    private String cncl_typ;
    private String cs_slp_cd;
    private String mbrsh_svc_fg;
    private String dir_self_mng_fg;
    private String wrng_sale_lmt_exc_fg;
    private String incom_organ_cd;
    private String ter_fg;
    private String ans_cd;
    private String telgrm_no;
    private String trc_no;
    private String orgn_aprv_dy;
    private String orgn_aprv_no;
    private String orgn_deal_dy;
    private String usb_dy;
    private String rep_aprv_no;
    private String sttl_mcht_no;
    private Long sttl_amt;
    private Long sk_chrg_amt;
    private String contr_fg_cd;
    private String svc_grp_no;
    private String cpn_prd_cd;
    private Long cpn_prd_qty;
    private String dist_cust_cpn_mng_no;
    private String clltbrd_no;
    private String coopco_cd;
    private String sttl_coopco_cd;
    private String mngco_chrg_sttl_mcht_no;
    private String coop_crd_Cd;
    private String vat_incld_yn;
    private String fee_apl_bas_fg;
    private Double fee_rt;
    private Long fee_amt;
    private String cash_arcpt_issu_yn;
    private String chip_deal_yn;
    private String coopco_aprv_no;
    private String coopco_mbr_id;
    private String coopco_mbr_ip_addr;
    private String bat_file_nm;
    private String telgrm_fg;
    private String deal_caus_cd;
    private String mix_sttl_yn;
    private Long mix_sttl_tot_amt;
}
