package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by madong on 2017/9/22.
 */
public class SaveShowDailyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "待日结/已日结单据")
    private WillDailySettleDetailVO willDailySettleDetailVO;
    @ApiModelProperty(value = "带日结/已日结明细")
    private List<DailySettleSaleVO> dailySettleSaleVOS;

    public List<DailySettleSaleVO> getDailySettleSaleVOS() {
        return dailySettleSaleVOS;
    }

    public void setDailySettleSaleVOS(List<DailySettleSaleVO> dailySettleSaleVOS) {
        this.dailySettleSaleVOS = dailySettleSaleVOS;
    }

    public WillDailySettleDetailVO getWillDailySettleDetailVO() {
        return willDailySettleDetailVO;
    }

    public void setWillDailySettleDetailVO(WillDailySettleDetailVO willDailySettleDetailVO) {
        this.willDailySettleDetailVO = willDailySettleDetailVO;
    }

    @Override
    public String toString() {
        return "SaveShowDailyInfo{" +
                "willDailySettleDetailVO=" + willDailySettleDetailVO +
                ", dailySettleSaleVOS=" + dailySettleSaleVOS +
                '}';
    }
}
