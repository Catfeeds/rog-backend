package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_distr_in_return_out_shelf
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutShelfPageVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 配进退出出库品种ID
     */
    @ApiModelProperty(value = "配进退出出库品种ID")
    private Long dtlId;

    /**
     * 配进退出出库单ID
     */
    @ApiModelProperty(value = "配进退出出库单ID")
    private Long outId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty("货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;



    public static List<DistrInReturnOutShelfPageVO> getDistrInReturnOutShelfPageVOs4ReturnShelf(List<WarehouseShelfDefVO> warehouseShelfDefVOS){

        List<DistrInReturnOutShelfPageVO> distrInReturnOutShelfPageVOS = new ArrayList<>();

        for(WarehouseShelfDefVO warehouseShelfDefVO : warehouseShelfDefVOS){

            DistrInReturnOutShelfPageVO distrInReturnOutShelfPageVO = new DistrInReturnOutShelfPageVO();

            /**
             * 货位ID
             */
            distrInReturnOutShelfPageVO.setShelfId(warehouseShelfDefVO.getShelfId());

            distrInReturnOutShelfPageVO.setShelfName(warehouseShelfDefVO.getName());
            /**
             * 数量
             */
            distrInReturnOutShelfPageVO.setQuantity(warehouseShelfDefVO.getQuantity());

            distrInReturnOutShelfPageVO.setShelfStatusDesc(warehouseShelfDefVO.getShelfStatusDesc());

            distrInReturnOutShelfPageVOS.add(distrInReturnOutShelfPageVO);

        }


        return distrInReturnOutShelfPageVOS;
    }


    public static DistrInReturnOutShelfPageVO getDistrInReturnOutShelfPageVOs(DistrInReturnOutShelf distrInReturnOutShelf){

        DistrInReturnOutShelfPageVO distrInReturnOutShelfPageVO = new DistrInReturnOutShelfPageVO();

        /**
         * 主键ID
         */
        distrInReturnOutShelfPageVO.setId(distrInReturnOutShelf.getId());

        /**
         * 配进退出出库品种ID
         */
        distrInReturnOutShelfPageVO.setDtlId(distrInReturnOutShelf.getDtlId());

        /**
         * 配进退出出库单ID
         */
        distrInReturnOutShelfPageVO.setOutId(distrInReturnOutShelf.getOutId());

        /**
         * 货位ID
         */
        distrInReturnOutShelfPageVO.setShelfId(distrInReturnOutShelf.getShelfId());

        distrInReturnOutShelfPageVO.setShelfName(distrInReturnOutShelf.getShelfName());
        /**
         * 数量
         */
        distrInReturnOutShelfPageVO.setQuantity(distrInReturnOutShelf.getQuantity());

        distrInReturnOutShelfPageVO.setShelfStatusDesc(distrInReturnOutShelf.getShelfStatusDesc());

        distrInReturnOutShelfPageVO.setLineNum(distrInReturnOutShelf.getLineNum());

        return distrInReturnOutShelfPageVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}