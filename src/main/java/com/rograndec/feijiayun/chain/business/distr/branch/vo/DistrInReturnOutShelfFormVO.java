package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * saas_distr_in_return_out_shelf
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutShelfFormVO implements Serializable {

    @ApiModelProperty(value = "购进退出出库货位单id 保存时不需要赋值,修改时需要赋值")
    private Long id;
    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID",required = true)
    private Long shelfId;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量",required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "货位质量状态描述",required = true)
    private String shelfStatusDesc;


    /**
     * 行号
     */
    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    public static List<DistrInReturnOutShelfFormVO> getDistrInReturnOutShelfFormVOs(List<DistrInReturnOutAddFormShelfVO> distrInReturnOutAddFormShelfVOS){

        return distrInReturnOutAddFormShelfVOS.stream().map(distrInReturnOutAddFormShelfVO -> {

            DistrInReturnOutShelfFormVO distrInReturnOutShelfFormVO = new DistrInReturnOutShelfFormVO();
            /**
             * 货位ID
             */
            distrInReturnOutShelfFormVO.setShelfId(distrInReturnOutAddFormShelfVO.getShelfId());

            /**
             * 数量
             */
            distrInReturnOutShelfFormVO.setQuantity(distrInReturnOutAddFormShelfVO.getQuantity());


            distrInReturnOutShelfFormVO.setShelfStatusDesc(distrInReturnOutAddFormShelfVO.getShelfStatusDesc());

            /**
             * 行号
             */
            distrInReturnOutShelfFormVO.setLineNum(distrInReturnOutAddFormShelfVO.getLineNum());

            return distrInReturnOutShelfFormVO;

        }).collect(Collectors.toList());

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
}