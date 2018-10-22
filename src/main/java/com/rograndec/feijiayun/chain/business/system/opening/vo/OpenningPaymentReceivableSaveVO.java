package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * 期初应付（应收）保存VO
 * @author zhangyu
 * @create 2018-01-03
 */
public class OpenningPaymentReceivableSaveVO {


    /**
     * 付款(收款)日期
     */
    @ApiModelProperty(value = "付款(收款)日期",required = true)
    @NotNull(message = "日期不能为空")
    private Date date;

    /**
     *
     * 付款(收款)人员ID
     */
    @ApiModelProperty(value = "付款人员ID")
    @NotNull(message = "人员不能为空")
    private Long manId;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 期初应付（应收）数据详情
     */
    @ApiModelProperty(value = "期初应付（应收）数据详情")
    @NotEmpty(message = "列表不能为空")
    @Valid
    private List<OpeningPaymentReceivableDetailVO> detailVOS;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OpeningPaymentReceivableDetailVO> getDetailVOS() {
        return detailVOS;
    }

    public void setDetailVOS(List<OpeningPaymentReceivableDetailVO> detailVOS) {
        this.detailVOS = detailVOS;
    }

}
