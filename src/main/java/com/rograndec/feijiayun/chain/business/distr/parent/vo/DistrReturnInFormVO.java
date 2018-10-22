package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.exception.DistrInReturnBizException;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DistrReturnInFormVO implements Serializable {



    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期",required = true)
    private Date returnInDate;


    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID",required = true)
    private Long storageManId;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    /**
     * 校验单id
     */
    @ApiModelProperty(value = "校验单id",required = true)
    private Long checkId;


    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）",required = true)
    private BigDecimal wholeDiscount;


    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额",required = true)
    private BigDecimal wholeDiscountAmount;

    @ApiModelProperty(value = "购后退出明细单集合")
    private List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS;


    public static DistrReturnInFormVO getDistrReturnInFormVO(DistrReturnCheck distrReturnCheck,
                                                             List<DistrReturnCheckDetail> distrReturnCheckDetailList,
                                                             List<DistrReturnCheckLot> distrReturnCheckLots,
                                                             DistrReturnNotice distrReturnNotice,
                                                             List<DistrReturnNoticeDetail> distrReturnNoticeDetails,
                                                             Long inOutManId,
                                                             Map<Long, SafetyStock> safetyStockMap){

        DistrReturnInFormVO distrReturnInFormVO = new DistrReturnInFormVO();
        /**
         * 入库日期
         */
        distrReturnInFormVO.setReturnInDate(new Date());

        /**
         * 入库人员ID
         */
        distrReturnInFormVO.setStorageManId(inOutManId);

        /**
         * 校验单id
         */
        distrReturnInFormVO.setCheckId(distrReturnCheck.getId());

        /**
         * 整单折扣（%）
         */
        distrReturnInFormVO.setWholeDiscount(distrReturnNotice.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrReturnInFormVO.setWholeDiscountAmount(distrReturnNotice.getWholeDiscountAmount());

        List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS = new ArrayList<>();

        distrReturnInFormVO.setDistrReturnInDetailFormVOS(distrReturnInDetailFormVOS);
        for(DistrReturnCheckDetail distrReturnCheckDetail : distrReturnCheckDetailList){

            DistrReturnInDetailFormVO distrReturnInDetailFormVO = new DistrReturnInDetailFormVO();

            /**
             *  配后退回验收明细单ID
             */
            distrReturnInDetailFormVO.setCheckDtId(distrReturnCheckDetail.getId());

            for(DistrReturnNoticeDetail distrReturnNoticeDetail : distrReturnNoticeDetails){

                if(distrReturnCheckDetail.getGoodsId().equals(distrReturnNoticeDetail.getGoodsId())){

                    /**
                     * 单价
                     */
                    distrReturnInDetailFormVO.setUnitPrice(distrReturnNoticeDetail.getUnitPrice());

                    /**
                     * 行折扣（%）
                     */
                    distrReturnInDetailFormVO.setLineDiscount(distrReturnNoticeDetail.getLineDiscount());

                    /**
                     * 税率ID
                     */
                    distrReturnInDetailFormVO.setTaxRateId(distrReturnNoticeDetail.getTaxRateId());

                }


            }
            /**
             * 行号
             */
            distrReturnInDetailFormVO.setLineNum(distrReturnCheckDetail.getLineNum());

            distrReturnInDetailFormVOS.add(distrReturnInDetailFormVO);

            List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS = new ArrayList<>();

            distrReturnInDetailFormVO.setDistrReturnInShelfFormVOS(distrReturnInShelfFormVOS);

            Integer lineNum = 0;
            for(DistrReturnCheckLot distrReturnCheckLot : distrReturnCheckLots){
                if(distrReturnCheckLot.getDtlId().equals(distrReturnCheckDetail.getId())){

                    DistrReturnInShelfFormVO distrReturnInShelfFormVO = new DistrReturnInShelfFormVO();

                    /**
                     * 货位ID
                     */
                    SafetyStock safetyStock = safetyStockMap.get(distrReturnCheckLot.getGoodsId());
                    if(null != safetyStock){
                        if(null == safetyStock.getDefaultShelfId() || 0 == safetyStock.getDefaultShelfId()) throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK,"当前门店不存在的默认货位");
                        distrReturnInShelfFormVO.setShelfId(safetyStock.getDefaultShelfId());
                    }


                    /**
                     * 验收单批号
                     */
                    distrReturnInShelfFormVO.setLotNumber(distrReturnCheckLot.getLotNumber());

                    distrReturnInShelfFormVO.setJobArea(0);

                    /**
                     * 行号
                     */
                    lineNum++;
                    distrReturnInShelfFormVO.setLineNum(lineNum);
                    distrReturnInShelfFormVOS.add(distrReturnInShelfFormVO);
                }

            }
        }


        return distrReturnInFormVO;

    }


    public Date getReturnInDate() {
        return returnInDate;
    }

    public void setReturnInDate(Date returnInDate) {
        this.returnInDate = returnInDate;
    }

    public Long getStorageManId() {
        return storageManId;
    }

    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }


    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
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

    public List<DistrReturnInDetailFormVO> getDistrReturnInDetailFormVOS() {
        return distrReturnInDetailFormVOS;
    }

    public void setDistrReturnInDetailFormVOS(List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS) {
        this.distrReturnInDetailFormVOS = distrReturnInDetailFormVOS;
    }
}
