package com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class InOutRecordDetailReportPageVO {


    @ApiModelProperty(value="收入合计")
    private BigDecimal inQuantityTotal;

    @ApiModelProperty(value="发出合计")
    private BigDecimal outQuantityTotal;

    @ApiModelProperty(value="结存")
    private BigDecimal storageBalance;

    private List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS;


    public BigDecimal getInQuantityTotal() {
        return inQuantityTotal;
    }

    public void setInQuantityTotal(BigDecimal inQuantityTotal) {
        this.inQuantityTotal = inQuantityTotal;
    }

    public BigDecimal getOutQuantityTotal() {
        return outQuantityTotal;
    }

    public void setOutQuantityTotal(BigDecimal outQuantityTotal) {
        this.outQuantityTotal = outQuantityTotal;
    }

    public BigDecimal getStorageBalance() {
        return storageBalance;
    }

    public void setStorageBalance(BigDecimal storageBalance) {
        this.storageBalance = storageBalance;
    }

    public List<InOutRecordDetailReportVO> getInOutRecordDetailReportVOS() {
        return inOutRecordDetailReportVOS;
    }

    public void setInOutRecordDetailReportVOS(List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS) {
        this.inOutRecordDetailReportVOS = inOutRecordDetailReportVOS;
    }
}
