package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OtherOutPageTotalVO implements Serializable {

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    @ApiModelProperty(value = "其他出库信息集合")
    private List<OtherOutPageVO> otherOutPageVOS;

    public static OtherOutPageTotalVO getOtherOutPageTotalVO(List<OtherOut> otherOuts){
        List<OtherOutPageVO> otherOutPageVOs = OtherOutPageVO.getOtherOutPageVOs(otherOuts);
        OtherOutPageTotalVO pageTotalVO = new OtherOutPageTotalVO();
        pageTotalVO.setOtherOutPageVOS(otherOutPageVOs);

        BigDecimal amountTotal = new BigDecimal(0);
        BigDecimal notaxAmountTotal = new BigDecimal(0);
        BigDecimal taxAmountTotal = new BigDecimal(0);
        for(OtherOut otherOut : otherOuts){
            amountTotal = amountTotal.add(otherOut.getAmountTotal()).setScale(2,BigDecimal.ROUND_HALF_UP);
            notaxAmountTotal = notaxAmountTotal.add(otherOut.getNotaxAmountTotal()).setScale(2,BigDecimal.ROUND_HALF_UP);
            taxAmountTotal = taxAmountTotal.add(otherOut.getTaxAmountTotal()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        pageTotalVO.setAmountTotal(amountTotal);
        pageTotalVO.setNotaxAmountTotal(notaxAmountTotal);
        pageTotalVO.setTaxAmountTotal(taxAmountTotal);
        return pageTotalVO;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public List<OtherOutPageVO> getOtherOutPageVOS() {
        return otherOutPageVOS;
    }

    public void setOtherOutPageVOS(List<OtherOutPageVO> otherOutPageVOS) {
        this.otherOutPageVOS = otherOutPageVOS;
    }
}
