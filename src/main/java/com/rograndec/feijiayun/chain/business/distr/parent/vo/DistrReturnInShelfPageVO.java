package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckLot;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_in_shelf
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInShelfPageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "货位明细表id")
    private Long id;

    /**
     * 配后退回入库明细ID
     */
    @ApiModelProperty(value = "配后退回入库明细ID")
    private Long dtlId;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    @ApiParam(value = "质量状况0-合格品，2不合格品", required = true)
    private Integer jobArea;
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
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

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
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public static List<DistrReturnInShelfPageVO> getDistrReturnInShelfPageVO4Check(
            DistrReturnCheckLot distrReturnInShelf
            ,SafetyStock safetyStock
    ){

        List<DistrReturnInShelfPageVO> list = new ArrayList<>();


        DistrReturnInShelfPageVO distrReturnInShelfPageVOq = new DistrReturnInShelfPageVO();
        DistrReturnInShelfPageVO distrReturnInShelfPageVOunQ = new DistrReturnInShelfPageVO();


        distrReturnInShelfPageVOq.setShelfStatusDesc("合格品");
        distrReturnInShelfPageVOq.setJobArea(0);
        distrReturnInShelfPageVOq.setLotNumber(distrReturnInShelf.getLotNumber());
        distrReturnInShelfPageVOq.setProductDate(distrReturnInShelf.getProductDate());
        distrReturnInShelfPageVOq.setValidDate(distrReturnInShelf.getValidDate());
        distrReturnInShelfPageVOq.setGoodsId(distrReturnInShelf.getGoodsId());
        /**
         * 货位ID
         */
        distrReturnInShelfPageVOq.setShelfId(safetyStock.getDefaultShelfId());

        /**
         * 货位名称
         */
        distrReturnInShelfPageVOq.setShelfName(safetyStock.getDefaultShelfName());

        distrReturnInShelfPageVOq.setQuantity(distrReturnInShelf.getQualifiedQuantity());




        /**
         * 货位质量状态描述
         */
        if(null != distrReturnInShelf.getUnqualifiedQuantity() && distrReturnInShelf.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) > 0){
            distrReturnInShelfPageVOunQ.setShelfStatusDesc("不合格品");
            distrReturnInShelfPageVOunQ.setJobArea(2);
            /**
             * 批号
             */

            distrReturnInShelfPageVOunQ.setLotNumber(distrReturnInShelf.getLotNumber());

            /**
             * 生产日期
             */

            distrReturnInShelfPageVOunQ.setProductDate(distrReturnInShelf.getProductDate());

            /**
             * 有效期
             */

            distrReturnInShelfPageVOunQ.setValidDate(distrReturnInShelf.getValidDate());

            /**
             * 商品ID
             */

            distrReturnInShelfPageVOunQ.setGoodsId(distrReturnInShelf.getGoodsId());
            /**
             * 货位ID
             */
            distrReturnInShelfPageVOq.setShelfId(safetyStock.getDefaultShelfId());
            /**
             * 货位名称
             */
            distrReturnInShelfPageVOq.setShelfName(safetyStock.getDefaultShelfName());
            distrReturnInShelfPageVOunQ.setQuantity(distrReturnInShelf.getUnqualifiedQuantity());
            list.add(distrReturnInShelfPageVOunQ);
        }

        list.add(distrReturnInShelfPageVOq);

        return list;
    }


    public static List<DistrReturnInShelfPageVO> getDistrReturnInShelfPageVOs(List<DistrReturnInShelf> distrReturnInShelfs){

        List<DistrReturnInShelfPageVO> distrReturnInShelfPageVOS = new ArrayList<>();

        distrReturnInShelfs.forEach(dris -> {

            DistrReturnInShelfPageVO distrReturnInShelfPageVO = getDistrReturnInShelfPageVO(dris);
            distrReturnInShelfPageVOS.add(distrReturnInShelfPageVO);
        });

        return distrReturnInShelfPageVOS;
    }

    public static DistrReturnInShelfPageVO getDistrReturnInShelfPageVO(DistrReturnInShelf distrReturnInShelf){

        DistrReturnInShelfPageVO distrReturnInShelfPageVO = new DistrReturnInShelfPageVO();

        /**
         * 主键ID
         */
        distrReturnInShelfPageVO.setId(distrReturnInShelf.getId());

        /**
         * 配后退回入库明细ID
         */
        distrReturnInShelfPageVO.setDtlId(distrReturnInShelf.getDtlId());

        /**
         * 货位质量状态描述
         */
        distrReturnInShelfPageVO.setShelfStatusDesc(distrReturnInShelf.getShelfStatusDesc());

        /**
         * 批号
         */
        distrReturnInShelfPageVO.setLotNumber(distrReturnInShelf.getLotNumber());

        /**
         * 生产日期
         */
        distrReturnInShelfPageVO.setProductDate(distrReturnInShelf.getProductDate());

        /**
         * 有效期
         */
        distrReturnInShelfPageVO.setValidDate(distrReturnInShelf.getValidDate());

        /**
         * 商品ID
         */
        distrReturnInShelfPageVO.setGoodsId(distrReturnInShelf.getGoodsId());

        /**
         * 货位ID
         */
        distrReturnInShelfPageVO.setShelfId(distrReturnInShelf.getShelfId());

        /**
         * 货位名称
         */
        distrReturnInShelfPageVO.setShelfName(distrReturnInShelf.getShelfName());

        /**
         * 数量
         */
        distrReturnInShelfPageVO.setQuantity(distrReturnInShelf.getQuantity());

        distrReturnInShelfPageVO.setLineNum(distrReturnInShelf.getLineNum());


        return distrReturnInShelfPageVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }
}