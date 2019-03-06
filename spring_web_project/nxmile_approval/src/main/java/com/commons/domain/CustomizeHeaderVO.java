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


}
