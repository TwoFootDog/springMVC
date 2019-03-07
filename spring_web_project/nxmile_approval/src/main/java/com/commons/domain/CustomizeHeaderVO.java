package com.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizeHeaderVO {
    private String telgrm_no;
    private String organ_cd;
    private String send_dy;
    private String send_tm;
    private String trc_no;
    private String telgrm_fg;
    private String ans_cd1;
    private String ans_cd2;
    private String message;

    public void set(CustomizeHeaderVO header) {
        this.telgrm_no = header.getTelgrm_no();
        this.organ_cd = header.getOrgan_cd();
        this.send_dy = header.getSend_dy();
        this.send_tm = header.getSend_tm();
        this.trc_no = header.getTrc_no();
        this.telgrm_fg = header.getTelgrm_fg();
        this.ans_cd1 = header.getAns_cd1();
        this.ans_cd2 = header.getAns_cd2();
        this.message = header.getMessage();
    }
}
