package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_other_out_shelf
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class OtherOutShelfPageVO implements Serializable {
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
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状况
     */
    @ApiModelProperty(value = "货位质量状况")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;


    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public static OtherOutShelfPageVO getOtherOutShelfPageVO(OtherOutShelf otherOutShelf){

        OtherOutShelfPageVO otherOutShelfPageVO = new OtherOutShelfPageVO();

        otherOutShelfPageVO.setId(otherOutShelf.getId());
        otherOutShelfPageVO.setShelfId(otherOutShelf.getShelfId());
        otherOutShelfPageVO.setShelfName(otherOutShelf.getShelfName());
        otherOutShelfPageVO.setShelfStatusDesc(otherOutShelf.getShelfStatusDesc());
        otherOutShelfPageVO.setQuantity(otherOutShelf.getQuantity());
        otherOutShelfPageVO.setStatus(otherOutShelf.getStatus());
        otherOutShelfPageVO.setLotId(otherOutShelf.getLotId());
        otherOutShelfPageVO.setLotNumber(otherOutShelf.getLotNumber());
        /**
         * 有效期
         */
        otherOutShelfPageVO.setValidDate(otherOutShelf.getValidDate());


        /**
         * 生产日期
         */
        otherOutShelfPageVO.setProductDate(otherOutShelf.getProductDate());

        otherOutShelfPageVO.setLineNum(otherOutShelf.getLineNum());

        return otherOutShelfPageVO;
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

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}