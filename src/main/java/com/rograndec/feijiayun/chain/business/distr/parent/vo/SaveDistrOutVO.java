package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SaveDistrOutVO implements Serializable {

    @ApiModelProperty(value = "要货单位ID", required = true)
    private Long requestUnitId;

    @ApiModelProperty(value = "配货日期", required = true)
    private Date distrDate;

    @ApiModelProperty(value = "配货人员ID", required = true)
    private Long sendManId;

    @ApiModelProperty(value = "配货类型", required = true)
    private Integer distrType;

    @ApiModelProperty(value = "流通监管码", required = true)
    private String flowCode;

    @ApiModelProperty(value = "出库日期", required = true)
    private Date outDate;

    @ApiModelProperty(value = "出库人员ID", required = true)
    private Long outManId;

    @ApiModelProperty(value = "整单折扣（%）", required = true)
    private BigDecimal wholeDiscount;

    @ApiModelProperty(value = "整单优惠金额", required = true)
    private BigDecimal wholeDiscountAmount;

    /**
     * 调用单据的单据ID
     */
    @ApiModelProperty(required = true, value = "调用单据的单据ID")
    private Long callDataId;

    /**
     * 调用单据的单据类型ID
     */
    @ApiModelProperty(required = true, value = "调用单据的单据类型ID")
    private Integer callDataType;

    List<SaveDistrOutDetailVO> saveDistrOutDetailVOList;


    public static SaveDistrOutVO getSaveDistrOutVO(DistrReturnInVO distrReturnInVO,DistrInReturnOut distrInReturnOut,Long distrManId,Long inOutManId){

        DistrReturnIn distrReturnIn = distrReturnInVO.getDistrReturnIn();

        SaveDistrOutVO saveDistrOutVO = new SaveDistrOutVO();
        saveDistrOutVO.setRequestUnitId(distrInReturnOut.getOutboundUnitId());

        saveDistrOutVO.setDistrDate(distrReturnIn.getReturnInDate());

        saveDistrOutVO.setSendManId(distrManId);

        saveDistrOutVO.setDistrType(distrReturnIn.getDistrType());

        saveDistrOutVO.setOutDate(new Date());

        /**
         * 调用单据的单据ID
         */
        saveDistrOutVO.setCallDataId(distrReturnIn.getId());

        /**
         * 调用单据的单据类型ID
         */
        saveDistrOutVO.setCallDataType(OrderRule.DISTR_RETURN_IN.getType());
        /**
         * 出库人员ID
         */
        saveDistrOutVO.setOutManId(inOutManId);

        saveDistrOutVO.setWholeDiscount(distrReturnIn.getWholeDiscount());

        saveDistrOutVO.setWholeDiscountAmount(distrReturnIn.getWholeDiscountAmount());

        List<SaveDistrOutDetailVO> saveDistrOutDetailVOs = SaveDistrOutDetailVO.getSaveDistrOutDetailVOs(distrReturnInVO);

        saveDistrOutVO.setSaveDistrOutDetailVOList(saveDistrOutDetailVOs);

        return saveDistrOutVO;
    }


    public static SaveDistrOutVO getSaveDistrOutVO(PickOrder pickOrder, DistrSend distrSend){

        SaveDistrOutVO saveDistrOutVO = new SaveDistrOutVO();
        saveDistrOutVO.setRequestUnitId(distrSend.getRequestUnitId());

        saveDistrOutVO.setDistrDate(distrSend.getDistrDate());

        saveDistrOutVO.setSendManId(distrSend.getDistrManId());

        saveDistrOutVO.setDistrType(distrSend.getDistrType());

        saveDistrOutVO.setOutDate(new Date());


        /**
         * 出库人员ID
         */
        saveDistrOutVO.setOutManId(distrSend.getDistrManId());

        saveDistrOutVO.setWholeDiscount(distrSend.getWholeDiscount());

        saveDistrOutVO.setWholeDiscountAmount(distrSend.getWholeDiscountAmount());


        List<PickDetail> pickDetails = pickOrder.getPickDetails();

        List<DistrSendDetail> distrSendDetailList = distrSend.getDistrSendDetailList();

        List<SaveDistrOutDetailVO> saveDistrOutDetailVOs = SaveDistrOutDetailVO.getSaveDistrOutDetailVOs(pickDetails, distrSendDetailList);

        saveDistrOutVO.setSaveDistrOutDetailVOList(saveDistrOutDetailVOs);

        return saveDistrOutVO;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public Date getDistrDate() {
        return distrDate;
    }

    public void setDistrDate(Date distrDate) {
        this.distrDate = distrDate;
    }

    public Long getSendManId() {
        return sendManId;
    }

    public void setSendManId(Long sendManId) {
        this.sendManId = sendManId;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
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

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public Long getCallDataId() {
        return callDataId;
    }

    public void setCallDataId(Long callDataId) {
        this.callDataId = callDataId;
    }

    public Integer getCallDataType() {
        return callDataType;
    }

    public void setCallDataType(Integer callDataType) {
        this.callDataType = callDataType;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public List<SaveDistrOutDetailVO> getSaveDistrOutDetailVOList() {
        return saveDistrOutDetailVOList;
    }

    public void setSaveDistrOutDetailVOList(List<SaveDistrOutDetailVO> saveDistrOutDetailVOList) {
        this.saveDistrOutDetailVOList = saveDistrOutDetailVOList;
    }

    @Override
    public String toString() {
        return "SaveDistrOutVO[" +
                "requestUnitId=" + requestUnitId +
                ", distrDate=" + distrDate +
                ", sendManId=" + sendManId +
                ", distrType=" + distrType +
                ", flowCode='" + flowCode + '\'' +
                ", outDate=" + outDate +
                ", outManId=" + outManId +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", callDataId=" + callDataId +
                ", callDataType=" + callDataType +
                ", saveDistrOutDetailVOList=" + saveDistrOutDetailVOList +
                ']';
    }
}
