package com.tpcom_apr.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnmsgchkOutputVO {
    private String mbrsh_pgm_id;
    private String telgrm_typ;
    private String ans_cd;
    private String msg_fg;
}
