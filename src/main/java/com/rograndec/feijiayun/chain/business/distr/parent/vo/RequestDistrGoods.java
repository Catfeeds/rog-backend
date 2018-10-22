package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/8 <br/>
 * 描述：保存配送单传参-商品行传参
 */
public class RequestDistrGoods {
    @ApiModelProperty(value = "配货单明细主键ID，修改配货单时需要传", required = true)
    private Long id;

    @ApiModelProperty(value = "商品ID",required = true)
    private Long goodsId;

    @ApiModelProperty(value = "数量",required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "单价",required = true)
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "行折扣",required = true)
    private BigDecimal lineDiscount;

    @ApiModelProperty(value = "税率ID",required = true)
    private Long taxRateId;

    @ApiModelProperty(value = "税率",required = true)
    private BigDecimal taxRate;

    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    @ApiModelProperty(value = "备注",required = false)
    private String remark;

    @ApiModelProperty(value = "调用要货计划、缺配单、采购入库单、配后退回入库单时的基础单据明细ID",required = false)
    private Long baseOrderDtlId;
    @ApiModelProperty(value = "调用要货计划、缺配单、采购入库单、配后退回入库单时的基础单号类型ID",required = false)
    private Long baseOrderId;
    @ApiModelProperty(value = "调用要货计划、缺配单、采购入库单、配后退回入库单时的基础单号类型",required = false)
    private Integer baseOrderType;
    @ApiModelProperty(value = "要货计划要货数量，前端不用传",required = false)
    private BigDecimal requestQuantity = BigDecimal.ZERO;

    List<SaveDistrOutShelfVO> saveDistrOutShelfVOList;


    public static List<RequestDistrGoods> getRequestDistrGoods(List<DistrReturnInDetail> distrReturnInDetails){

        List<RequestDistrGoods> list = new ArrayList<>();

        int i = 0;
        for(DistrReturnInDetail distrReturnInDetail : distrReturnInDetails){

            i++;
            RequestDistrGoods requestDistrGoods = new RequestDistrGoods();
            requestDistrGoods.setGoodsId(distrReturnInDetail.getGoodsId());
            requestDistrGoods.setQuantity(distrReturnInDetail.getQuantity());
            requestDistrGoods.setUnitPrice(distrReturnInDetail.getUnitPrice());
            requestDistrGoods.setLineDiscount(BigDecimal.TEN.multiply(BigDecimal.TEN));
            requestDistrGoods.setTaxRateId(distrReturnInDetail.getTaxRateId());
            requestDistrGoods.setTaxRate(distrReturnInDetail.getTaxRate());
            requestDistrGoods.setBaseOrderId(distrReturnInDetail.getReturnInId());
            requestDistrGoods.setBaseOrderDtlId(distrReturnInDetail.getId());
            requestDistrGoods.setBaseOrderType(distrReturnInDetail.getOrderType());
            requestDistrGoods.setLineNum(i);
            list.add(requestDistrGoods);
        }

        return list;
    }

    public RequestDistrGoods() {
    }

    public RequestDistrGoods(Long goodsId, BigDecimal quantity, BigDecimal requestQuantity, Integer lineNum, Long baseOrderDtlId,
                             Long baseOrderId, Integer baseOrderType) {
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.requestQuantity = requestQuantity;
        this.lineNum = lineNum;
        this.baseOrderDtlId = baseOrderDtlId;
        this.baseOrderId = baseOrderId;
        this.baseOrderType = baseOrderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
        if (lineDiscount == null) {
            return new BigDecimal(100);
        }
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

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(BigDecimal requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public List<SaveDistrOutShelfVO> getSaveDistrOutShelfVOList() {
        return saveDistrOutShelfVOList;
    }

    public void setSaveDistrOutShelfVOList(List<SaveDistrOutShelfVO> saveDistrOutShelfVOList) {
        this.saveDistrOutShelfVOList = saveDistrOutShelfVOList;
    }
}
