package com.rograndec.feijiayun.chain.business.retail.special.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: SpecialRegisterSaveOrupdateVO
 * @Description: 零售管理-专管登记-Rest接口
 * @date 2017-09-22 16:25:37
 */
@ApiModel(value = "SpecialRegisterSaveOrupdateVO", description = "零售管理-专管登记")
public class SpecialRegisterSaveOrupdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(required = false,value = "id")
    private Long id;

    /**
     * 购药者姓名
     */
    @NotNull(message = "购药者姓名不能为空")
    @ApiModelProperty(required = true, value = "购药者姓名")
    private String consumerName;

    @NotNull(message = "登记时间不能为空")
    @ApiModelProperty(required = true,value = "登记时间")
    private Date registerDate;

    @NotNull(message = "登记人id")
    @ApiModelProperty(required = true,value = "登记人id")
    private Long userId;
    /**
     * 性别（0-女；1-男）
     */
    @NotNull(message = "购药者性别不能为空")
    @ApiModelProperty(required = true, value = "性别（0-女；1-男）")
    private Integer sex;

    /**
     * 年龄
     */
    @NotNull(message = "购药者年龄不能为空")
    @ApiModelProperty(required = true, value = "年龄")
    private Integer age;

    /**
     * 身份证号
     */
    @NotNull(message = "购药者身份证号不能为空")
    @ApiModelProperty(required = true, value = "身份证号")
    private String idNum;

    /**
     * 整单折扣（%）
     */
    @NotNull(message = "整单折扣（%）不能为空!")
    @ApiModelProperty(required = true, value = "整单折扣（%）")
    private BigDecimal wholeDiscount=new BigDecimal(100);

    /**
     * 备注
     */
    @ApiModelProperty(required = false, value = "备注")
    private String remark;


    @ApiModelProperty(required = false, value = "会员信息id")
    private Integer memberId;

    @ApiModelProperty(required = false, value = "销售单id")
    private Long saleId;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(required = false,value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount=new BigDecimal(0.00);


    @ApiModelProperty(required = true,value = "专管登记单品种明细")
    private List<SpecialRegisterDetailSaveOrupdateVO> specialRegisterDetailSaveOrupdateVOList;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

//    public String getRegisterTime() {
//        return registerTime;
//    }
//
//    public void setRegisterTime(String registerTime) {
//        this.registerTime = registerTime;
//    }


    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public List<SpecialRegisterDetailSaveOrupdateVO> getSpecialRegisterDetailSaveOrupdateVOList() {
        return specialRegisterDetailSaveOrupdateVOList;
    }

    public void setSpecialRegisterDetailSaveOrupdateVOList(List<SpecialRegisterDetailSaveOrupdateVO> specialRegisterDetailSaveOrupdateVOList) {
        this.specialRegisterDetailSaveOrupdateVOList = specialRegisterDetailSaveOrupdateVOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 购药者姓名
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * 购药者姓名
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * 性别（0-女；1-男）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 性别（0-女；1-男）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 身份证号
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * 身份证号
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }


    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }


}