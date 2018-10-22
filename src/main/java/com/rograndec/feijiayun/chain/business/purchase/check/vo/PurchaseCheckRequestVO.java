package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Asze on 2017/9/20.
 */
public class PurchaseCheckRequestVO implements Serializable {

    private CheckHeadVO checkHeadVO;

    private List<CheckDeatilsVO> checkDeatilsVO;

    public CheckHeadVO getCheckHeadVO() {
        return checkHeadVO;
    }

    public void setCheckHeadVO(CheckHeadVO checkHeadVO) {
        this.checkHeadVO = checkHeadVO;
    }

    public List<CheckDeatilsVO> getCheckDeatilsVO() {
        return checkDeatilsVO;
    }

    public void setCheckDeatilsVO(List<CheckDeatilsVO> checkDeatilsVO) {
        this.checkDeatilsVO = checkDeatilsVO;
    }

    @Override
    public String toString() {
        return "PurchaseCheckRequestVO[" +
                "checkHeadVO=" + checkHeadVO +
                ", checkDeatilsVO=" + checkDeatilsVO +
                ']';
    }
}
