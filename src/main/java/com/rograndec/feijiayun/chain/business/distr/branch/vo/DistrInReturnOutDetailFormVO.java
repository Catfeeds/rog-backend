package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * saas_distr_in_return_out_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutDetailFormVO implements Serializable {


    @ApiModelProperty(value = "购进退出出库明细单id 保存时不需要赋值,修改时需要赋值")
    private Long id;

    /**
     * 购进退出单明细id
     */
    @ApiModelProperty(value = "购进退出单明细id",required = true)
    private Long returnDetailId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量",required = true)
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价",required = true)
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）",required = true)
    private BigDecimal lineDiscount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID",required = true)
    private Long taxRateId;
    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID",required = true)
    private Long lotId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 商品id,新增和调用的时候需要传递
     */
    @ApiModelProperty(value = "商品id,新增和调用的时候需要传递")
    private Long goodsId;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    @ApiModelProperty(value = "货位删除id集合")
    private List<Long> deleteShelfIds = new ArrayList<>();

    @ApiModelProperty(value = "购进退出出库单货位明细信息")
    private List<DistrInReturnOutShelfFormVO> distrInReturnOutShelfFormVOS;

    public static List<DistrInReturnOutDetailFormVO> getDistrInReturnOutDetailFormVOs(List<DistrInReturnOutAddFormDetailVO> distrInReturnOutAddFormDetailVOS){

        return distrInReturnOutAddFormDetailVOS.stream().map(distrInReturnOutAddFormDetailVO -> {

            DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO = new DistrInReturnOutDetailFormVO();
            /**
             * 数量
             */
            distrInReturnOutDetailFormVO.setQuantity(distrInReturnOutAddFormDetailVO.getQuantity());

            /**
             * 单价
             */
            distrInReturnOutDetailFormVO.setUnitPrice(distrInReturnOutAddFormDetailVO.getUnitPrice());

            /**
             * 行折扣（%）
             */
            distrInReturnOutDetailFormVO.setLineDiscount(distrInReturnOutAddFormDetailVO.getLineDiscount());

            /**
             * 税率ID
             */
            distrInReturnOutDetailFormVO.setTaxRateId(distrInReturnOutAddFormDetailVO.getTaxRateId());

            /**
             * 批号ID
             */
            distrInReturnOutDetailFormVO.setLotId(distrInReturnOutAddFormDetailVO.getLotId());

            /**
             * 备注
             */
            distrInReturnOutDetailFormVO.setRemark(distrInReturnOutAddFormDetailVO.getRemark());

            /**
             * 行号
             */
            distrInReturnOutDetailFormVO.setLineNum(distrInReturnOutAddFormDetailVO.getLineNum());

            List<DistrInReturnOutAddFormShelfVO> distrInReturnOutAddFormShelfVOS = distrInReturnOutAddFormDetailVO.getDistrInReturnOutAddFormShelfVOS();

            List<DistrInReturnOutShelfFormVO> distrInReturnOutShelfFormVOs = DistrInReturnOutShelfFormVO.getDistrInReturnOutShelfFormVOs(distrInReturnOutAddFormShelfVOS);
            distrInReturnOutDetailFormVO.setDistrInReturnOutShelfFormVOS(distrInReturnOutShelfFormVOs);

            distrInReturnOutDetailFormVO.setGoodsId(distrInReturnOutAddFormDetailVO.getGoodsId());
            return distrInReturnOutDetailFormVO;

        }).collect(Collectors.toList());


    }



    public static List<Long> getShelfIds(List<DistrInReturnOutDetailFormVO> distrReturnInDetailFormVOS){

        List<Long> ids = new ArrayList<>();

        distrReturnInDetailFormVOS.forEach(

                drid -> {

                    List<DistrInReturnOutShelfFormVO> distrReturnInShelfFormVOS = drid.getDistrInReturnOutShelfFormVOS();

                    distrReturnInShelfFormVOS.forEach(dris -> {
                        ids.add(dris.getShelfId());
                    });
                }
        );

        return ids;
    }


    public static List<Long> getTaxRateIds(List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS){

        return distrInReturnOutDetailFormVOS.stream().map(dris -> dris.getTaxRateId()).collect(Collectors.toList());
    }

    public Long getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(Long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }


    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DistrInReturnOutShelfFormVO> getDistrInReturnOutShelfFormVOS() {
        return distrInReturnOutShelfFormVOS;
    }

    public void setDistrInReturnOutShelfFormVOS(List<DistrInReturnOutShelfFormVO> distrInReturnOutShelfFormVOS) {
        this.distrInReturnOutShelfFormVOS = distrInReturnOutShelfFormVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public List<Long> getDeleteShelfIds() {
        return deleteShelfIds;
    }

    public void setDeleteShelfIds(List<Long> deleteShelfIds) {
        this.deleteShelfIds = deleteShelfIds;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}