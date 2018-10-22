package com.rograndec.feijiayun.chain.business.member.storedamount.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/26 <br/>
 * 描述：会员管理-储值管理列表（包含合计数据）
 */
public class StoredAmountTotalVO implements Serializable {

    private static final long serialVersionUID = -6416743801137894349L;

    /**
     * 储值合计（累计储值合计）
     */
    @ApiModelProperty(value = "储值合计（累计储值合计）")
    private BigDecimal totalSumStoredAmount = new BigDecimal(0.00);

    /**
     * 储值合计（储值余额合计）
     */
    @ApiModelProperty(value = "储值余额合计")
    private BigDecimal totalCurrentStoredAmount = new BigDecimal(0.00);;

    private List<StoredAmountPageVO> storedAmountList;

    public BigDecimal getTotalSumStoredAmount() {
        return totalSumStoredAmount;
    }

    public void setTotalSumStoredAmount(BigDecimal totalSumStoredAmount) {
        this.totalSumStoredAmount = totalSumStoredAmount;
    }

    public BigDecimal getTotalCurrentStoredAmount() {
        return totalCurrentStoredAmount;
    }

    public void setTotalCurrentStoredAmount(BigDecimal totalCurrentStoredAmount) {
        this.totalCurrentStoredAmount = totalCurrentStoredAmount;
    }

    public List<StoredAmountPageVO> getStoredAmountList() {
        return storedAmountList;
    }

    public void setStoredAmountList(List<StoredAmountPageVO> storedAmountList) {
        this.storedAmountList = storedAmountList;
    }
}
