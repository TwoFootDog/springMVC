package com.tpcom_apr.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetaprvnoInputVO {
    private String svc_modu_id;
    private String telgrm_fg;
    private String onoff_mcht_fg;
    private String mbrsh_pgm_id;
    private String mcht_no;
}
