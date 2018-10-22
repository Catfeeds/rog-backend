package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;
import java.util.List;

public class ExportCheckTrainPlanVO implements Serializable {

    ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO;

    List<ExportClickTrainPlanDetailVO > exportClickTrainPlanDetailVO;

    public ClickWaitTrainPlanHeadVO getClickWaitTrainPlanHeadVO() {
        return clickWaitTrainPlanHeadVO;
    }

    public void setClickWaitTrainPlanHeadVO(ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO) {
        this.clickWaitTrainPlanHeadVO = clickWaitTrainPlanHeadVO;
    }

    public List<ExportClickTrainPlanDetailVO> getExportClickTrainPlanDetailVO() {
        return exportClickTrainPlanDetailVO;
    }

    public void setExportClickTrainPlanDetailVO(List<ExportClickTrainPlanDetailVO> exportClickTrainPlanDetailVO) {
        this.exportClickTrainPlanDetailVO = exportClickTrainPlanDetailVO;
    }

    @Override
    public String toString() {
        return "CheckTrainPlanVO[" +
                "clickWaitTrainPlanHeadVO=" + clickWaitTrainPlanHeadVO +
                ", exportClickTrainPlanDetailVO=" + exportClickTrainPlanDetailVO +
                ']';
    }
}
