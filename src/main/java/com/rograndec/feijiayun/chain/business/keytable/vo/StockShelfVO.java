package com.rograndec.feijiayun.chain.business.keytable.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 21:02
 */

public class StockShelfVO {

    @ApiModelProperty(value = "数量")
    private BigDecimal quality;

    /**
     * 可用数量
     */
    @ApiModelProperty(value = "可用数量")
    private BigDecimal usableQuantity;

    /**
     * 批号id
     */
    @ApiModelProperty(value = "批号id")
    private Long lotId;
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

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
     * 质量状况
     */
    @ApiModelProperty(value = "质量状况")
    private String qualityState;


    public static StockShelfVO getStockShelfVO(DistrInShelf distrInShelf, LotNumber lotNumber){

        StockShelfVO stockShelfVO = new StockShelfVO();

        stockShelfVO.setQuality(distrInShelf.getQuantity());

        /**
         * 可用数量
         */
        stockShelfVO.setUsableQuantity(distrInShelf.getCanReturnQuantity());

        /**
         * 批号id
         */
        stockShelfVO.setLotId(lotNumber.getId());

        /**
         * 批号
         */
        stockShelfVO.setLotNumber(lotNumber.getLotNum());

        /**
         * 生产日期
         */
        stockShelfVO.setProductDate(distrInShelf.getProductDate());

        /**
         * 有效期
         */
        stockShelfVO.setValidDate(distrInShelf.getValidDate());

        /**
         * 货位ID
         */
        stockShelfVO.setShelfId(distrInShelf.getShelfId());

        /**
         * 货位名称
         */
        stockShelfVO.setShelfName(distrInShelf.getShelfName());

        /**
         * 质量状况
         */
        stockShelfVO.setQualityState(distrInShelf.getShelfStatusDesc());

        return stockShelfVO;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getValidDate() {
        return validDate;
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

    public BigDecimal getQuality() {
        return quality;
    }

    public void setQuality(BigDecimal quality) {
        this.quality = quality;
    }

    public String getQualityState() {
        return qualityState;
    }

    public void setQualityState(String qualityState) {
        this.qualityState = qualityState;
    }
}