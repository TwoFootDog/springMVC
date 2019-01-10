package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OritrqryOutputVO {
    private String mbrsh_pgm_id;    // 멤버쉽프로그램ID
    private String aprv_dy;         // 승인일자
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
    private String coop_crd_cd;
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

    public OritrqryOutputVO(Apr_dealtr_trn_tpcom_vs2002OutputVO apr) {
        mbrsh_pgm_id =              apr.getMbrsh_pgm_id();
        aprv_dy =                   apr.getAprv_dy();
        aprv_no =                   apr.getAprv_no();
        crd_no =                    apr.getCrd_no();
        aprv_tm =                   apr.getAprv_tm();
        deal_dy =                   apr.getDeal_dy();
        mcht_no =                   apr.getMcht_no();
        fam_rep_mbr_id =            apr.getFam_rep_mbr_id();
        mbr_id =                    apr.getMbr_id();
        slp_cd =                    apr.getSlp_cd();
        deal_fg =                   apr.getDeal_fg();
        deal_tm =                   apr.getDeal_tm();
        prz_cd =                    apr.getPrz_cd();
        oilcls_cd =                 apr.getOilcls_cd();
        sale_qty =                  apr.getSale_qty();
        sale_prc =                  apr.getSale_prc();
        deal_amt =                  apr.getDeal_amt();
        dsc_amt =                   apr.getDsc_amt();
        mcht_pnt =                  apr.getMcht_pnt();
        cur_pnt =                   apr.getCur_pnt();
        avl_pnt =                   apr.getAvl_pnt();
        pnt_knd_cd =                apr.getPnt_knd_cd();
        annfee =                    apr.getAnnfee();
        mbrsh_svc_annfee =          apr.getMbrsh_svc_annfee();
        refu_lmt_inc_amt =          apr.getRefu_lmt_inc_amt();
        deal_bef_cur_pnt =          apr.getDeal_bef_cur_pnt();
        deal_bef_avl_pnt =          apr.getDeal_bef_avl_pnt();
        deal_bef_annfee =           apr.getDeal_bef_annfee();
        deal_bef_mbrsh_svc_annfee = apr.getDeal_bef_mbrsh_svc_annfee();
        cncl_typ =                  apr.getCncl_typ();
        cs_slp_cd =                 apr.getCs_slp_cd();
        mbrsh_svc_fg =              apr.getMbrsh_svc_fg();
        dir_self_mng_fg =           apr.getDir_self_mng_fg();
        wrng_sale_lmt_exc_fg =      apr.getWrng_sale_lmt_exc_fg();
        incom_organ_cd =            apr.getIncom_organ_cd();
        deal_fg =                   apr.getDeal_fg();
        ans_cd =                    apr.getAns_cd();
        telgrm_no =                 apr.getTelgrm_no();
        trc_no =                    apr.getTrc_no();
        orgn_aprv_dy =              apr.getOrgn_aprv_dy();
        orgn_aprv_no =              apr.getOrgn_aprv_no();
        orgn_deal_dy =              apr.getOrgn_deal_dy();
        usb_dy =                    apr.getUsb_dy();
        rep_aprv_no =               apr.getRep_aprv_no();
        sttl_mcht_no =              apr.getSttl_mcht_no();
        sttl_amt =                  apr.getSttl_amt();
        sk_chrg_amt =               apr.getSk_chrg_amt();
        contr_fg_cd =               apr.getContr_fg_cd();
        svc_grp_no =                apr.getSvc_grp_no();
        cpn_prd_cd =                apr.getCpn_prd_cd();
        cpn_prd_qty =               apr.getCpn_prd_qty();
        dist_cust_cpn_mng_no =      apr.getDist_cust_cpn_mng_no();
        clltbrd_no =                apr.getClltbrd_no();
        coopco_cd =                 apr.getCoopco_cd();
        sttl_coopco_cd =            apr.getSttl_coopco_cd();
        mngco_chrg_sttl_mcht_no =   apr.getMngco_chrg_sttl_mcht_no();
        coop_crd_cd =               apr.getCoop_crd_Cd();
        vat_incld_yn =              apr.getVat_incld_yn();
        fee_apl_bas_fg =            apr.getFee_apl_bas_fg();
        fee_rt =                    apr.getFee_rt();
        fee_amt =                   apr.getFee_amt();
        cash_arcpt_issu_yn =        apr.getCash_arcpt_issu_yn();
        chip_deal_yn =              apr.getChip_deal_yn();
        coopco_aprv_no =            apr.getCoopco_aprv_no();
        coopco_mbr_id =             apr.getCoopco_mbr_id();
        coopco_mbr_ip_addr =        apr.getCoopco_mbr_ip_addr();
        bat_file_nm =               apr.getBat_file_nm();
        telgrm_fg =                 apr.getTelgrm_fg();
        deal_caus_cd =              apr.getDeal_caus_cd();
        mix_sttl_yn =               apr.getMix_sttl_yn();
        mix_sttl_tot_amt =          apr.getMix_sttl_tot_amt();
    }
}
