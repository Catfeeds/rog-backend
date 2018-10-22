package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;
import java.util.List;

public class CheckTrainPlanVO implements Serializable {

    ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO;

    List<ClickTrainPlanDetailVO > clickTrainPlanDetailVO;

    public ClickWaitTrainPlanHeadVO getClickWaitTrainPlanHeadVO() {
        return clickWaitTrainPlanHeadVO;
    }

    public void setClickWaitTrainPlanHeadVO(ClickWaitTrainPlanHeadVO clickWaitTrainPlanHeadVO) {
        this.clickWaitTrainPlanHeadVO = clickWaitTrainPlanHeadVO;
    }

    public List<ClickTrainPlanDetailVO> getClickTrainPlanDetailVO() {
        return clickTrainPlanDetailVO;
    }

    public void setClickTrainPlanDetailVO(List<ClickTrainPlanDetailVO> clickTrainPlanDetailVO) {
        this.clickTrainPlanDetailVO = clickTrainPlanDetailVO;
    }

    @Override
    public String toString() {
        return "CheckTrainPlanVO[" +
                "clickWaitTrainPlanHeadVO=" + clickWaitTrainPlanHeadVO +
                ", clickTrainPlanDetailVO=" + clickTrainPlanDetailVO +
                ']';
    }
}
