package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_return_out
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutFormVO implements Serializable {

    @ApiModelProperty(value = "购进退出出库单id 保存时不需要赋值,修改时需要赋值")
    private Long id;

    /**
     * 购进退出单id
     */
    @ApiModelProperty(value = "购进退出单id,查询和初始化列表会带过去",required = true)
    private Long returnId;
    /**
     * 配进退出出库日期
     */
    @ApiModelProperty(value = "配进退出出库日期,查询和初始化列表会带过去,默认是当前时间,受质量控制可修改",required = true)
    private Date outDate;
    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID,查询和初始化列表会带过去,默认是当前登录人,受质量控制可修改",required = true)
    private Long outManId;


    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）查询和初始化列表会带过去,用户可以修改",required = true)
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额,查询和初始化列表会带过去,用户可以修改",required = true)
    private BigDecimal wholeDiscountAmount;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "购进退出单出库单明细信息")
    private List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS;


    public static DistrInReturnOutFormVO getDistrInReturnOutFormVO(DistrInReturnOutAddFormVO distrInReturnOutAddFormVO,DistrInReturn distrInReturn){

        DistrInReturnOutFormVO distrInReturnOutFormVO = new DistrInReturnOutFormVO();

        distrInReturnOutFormVO.setReturnId(distrInReturn.getId());
        /**
         * 配进退出出库日期
         */
        distrInReturnOutFormVO.setOutDate(distrInReturnOutAddFormVO.getOutDate());

        /**
         * 出库人员ID
         */
        distrInReturnOutFormVO.setOutManId(distrInReturnOutAddFormVO.getOutManId());

        /**
         * 整单折扣（%）
         */
        distrInReturnOutFormVO.setWholeDiscount(distrInReturnOutAddFormVO.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrInReturnOutFormVO.setWholeDiscountAmount(distrInReturnOutAddFormVO.getWholeDiscountAmount());

        /**
         * 备注
         */
        distrInReturnOutFormVO.setRemark(distrInReturnOutAddFormVO.getRemark());

        return distrInReturnOutFormVO;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public List<DistrInReturnOutDetailFormVO> getDistrInReturnOutDetailFormVOS() {
        return distrInReturnOutDetailFormVOS;
    }

    public void setDistrInReturnOutDetailFormVOS(List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS) {
        this.distrInReturnOutDetailFormVOS = distrInReturnOutDetailFormVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}