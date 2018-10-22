package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.util.List;

public class ExportDistrReturnCheckVO implements Serializable {

    DistrReturnCheckHeadVO distrReturnCheckHeadVO;

    List<ExportDistrReturnCheckDtlVO> ExportDistrReturnCheckDtlVO;

    public DistrReturnCheckHeadVO getDistrReturnCheckHeadVO() {
        return distrReturnCheckHeadVO;
    }

    public void setDistrReturnCheckHeadVO(DistrReturnCheckHeadVO distrReturnCheckHeadVO) {
        this.distrReturnCheckHeadVO = distrReturnCheckHeadVO;
    }

    public List<com.rograndec.feijiayun.chain.business.distr.parent.vo.ExportDistrReturnCheckDtlVO> getExportDistrReturnCheckDtlVO() {
        return ExportDistrReturnCheckDtlVO;
    }

    public void setExportDistrReturnCheckDtlVO(List<com.rograndec.feijiayun.chain.business.distr.parent.vo.ExportDistrReturnCheckDtlVO> exportDistrReturnCheckDtlVO) {
        ExportDistrReturnCheckDtlVO = exportDistrReturnCheckDtlVO;
    }

    @Override
    public String toString() {
        return "ExportDistrReturnCheckVO[" +
                "distrReturnCheckHeadVO=" + distrReturnCheckHeadVO +
                ", ExportDistrReturnCheckDtlVO=" + ExportDistrReturnCheckDtlVO +
                ']';
    }
}
