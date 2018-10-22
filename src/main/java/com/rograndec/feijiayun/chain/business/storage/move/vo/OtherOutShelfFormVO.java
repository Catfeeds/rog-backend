package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * saas_other_out_shelf
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
@ApiModel
public class OtherOutShelfFormVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;
    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(value = "库存表id")
    private Long stockId;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public static List<OtherOutShelfFormVO> getOtherOutShelfFormVOs(List<OtherOutDetailFormVO> otherOutDetailFormVOS){

        List<OtherOutShelfFormVO> list = new ArrayList<>();

        otherOutDetailFormVOS.stream().forEach(
                detail -> {
                    list.addAll(detail.getOtherOutShelfFormVOS());
                }
        );

        return list;
    }

    public static BigDecimal getQuantityTotal4VO(List<OtherOutShelfFormVO> vos){

        BigDecimal quantityTotal = new BigDecimal(0);

        for(OtherOutShelfFormVO vo : vos){
            quantityTotal = quantityTotal.add(vo.getQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        return quantityTotal;
    }

    public static Set<Long> getLotIds(List<OtherOutShelfFormVO> detailVOs){

        Set<Long> ids = new HashSet<>();

        detailVOs.forEach(detail -> ids.add(detail.getLotId()));

        return ids;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public static Set<Long> getStockIds(List<OtherOutShelfFormVO> detailVOs){

        Set<Long> ids = new HashSet<>();

        detailVOs.forEach(detail -> ids.add(detail.getStockId()));

        return ids;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}