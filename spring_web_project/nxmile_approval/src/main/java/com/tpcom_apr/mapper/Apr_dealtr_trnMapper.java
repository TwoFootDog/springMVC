package com.tpcom_apr.mapper;


import com.tpcom_apr.domain.sql.*;

import java.util.List;

public interface Apr_dealtr_trnMapper {
    public Apr_dealtr_trn_tpcom_vs2001OutputVO apr_dealtr_trn_tpcom_vs2001(Apr_dealtr_trn_tpcom_vs2001InputVO inputVO);
    public Apr_dealtr_trn_tpcom_vs2002OutputVO apr_dealtr_trn_tpcom_vs2002(Apr_dealtr_trn_tpcom_vs2002InputVO inputVO);
    public Apr_dealtr_trn_tpcom_vs2003OutputVO apr_dealtr_trn_tpcom_vs2003(Apr_dealtr_trn_tpcom_vs2003InputVO inputVO);
    public Apr_dealtr_trn_tpcom_vs2004OutputVO apr_dealtr_trn_tpcom_vs2004(Apr_dealtr_trn_tpcom_vs2004InputVO inputVO);
    public Apr_dealtr_trn_tpcom_vs2035OutputVO apr_dealtr_trn_tpcom_vs2035(Apr_dealtr_trn_tpcom_vs2035InputVO inputVO);
    public List<Apr_dealtr_trn_tpcom_vf2001OutputVO> apr_dealtr_trn_tpcom_vf2001(Apr_dealtr_trn_tpcom_vf2001InputVO inputVO);
    public int apr_dealtr_trn_tpcom_ei2001(Apr_dealtr_trn_tpcom_ei2001InputVO inputVO);
    public int apr_dealtr_trn_tpcom_eu2001(Apr_dealtr_trn_tpcom_eu2001InputVO inputVO);

}
