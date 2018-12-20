package com.tpcom_apr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmComOnmsgchkOutputVO {
    private String mbrsh_pgm_id;
    private String telgrm_typ;
    private String ans_cd;
    private String msg_fg;
}
