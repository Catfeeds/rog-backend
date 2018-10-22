package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WarehouseShelfDefVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "货位id")
    private Long shelfId;



    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 编码前缀
     */
    @ApiModelProperty(value = "编码前缀")
    private String prefix;

    @ApiModelProperty(value = "起始编码")
    private String startCode;

    @ApiModelProperty(value = "结束编码")
    private String endCode;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    private Stock stock;

    /**
     * 货位质量状态描述
     */
    private String shelfStatusDesc;


    public static List<WarehouseShelfDefVO> getWarehouseShelfDefVOs4StockLok( List<StockLockRecordVO> stockLockRecords){

        List<WarehouseShelfDefVO> list = new ArrayList<>();


        for(StockLockRecordVO st : stockLockRecords){
            WarehouseShelfDefVO warehouseShelfDefVO = new WarehouseShelfDefVO();

            /**
             * 主键ID
             */
            warehouseShelfDefVO.setShelfId(st.getShelfId());



            /**
             * 编码
             */
            warehouseShelfDefVO.setCode(st.getGoodsCode());


            /**
             * 名称
             */
            warehouseShelfDefVO.setName(st.getGoodsName());


            /**
             * 数量
             */
            warehouseShelfDefVO.setQuantity(st.getUsableQuantity());

            /**
             * 货位质量状态描述
             */
            warehouseShelfDefVO.setShelfStatusDesc(st.getShelfStatusDesc());
            list.add(warehouseShelfDefVO);

        }

        return list;

    }

    public static List<WarehouseShelfDefVO> getWarehouseShelfDefVOs(List<WarehouseShelf> warehouseShelve,Map<Long,BigDecimal> shelfIds,List<Stock> defShelfs){

        List<WarehouseShelfDefVO> list = new ArrayList<>();
        for(WarehouseShelf warehouseShelf : warehouseShelve){

            WarehouseShelfDefVO warehouseShelfDefVO = getWarehouseShelfDefVO(warehouseShelf, shelfIds, defShelfs);
            list.add(warehouseShelfDefVO);
        }

        return list;
    }


    public static WarehouseShelfDefVO getWarehouseShelfDefVO(WarehouseShelf warehouseShelve,Map<Long,BigDecimal> shelfIds,List<Stock> defShelfs){

        WarehouseShelfDefVO warehouseShelfDefVO = new WarehouseShelfDefVO();
        /**
         * 主键ID
         */
        warehouseShelfDefVO.setShelfId(warehouseShelve.getId());

        /**
         * 编码
         */
        warehouseShelfDefVO.setCode(warehouseShelve.getCode());

        /**
         * 编码前缀
         */
        warehouseShelfDefVO.setPrefix(warehouseShelve.getPrefix());

        warehouseShelfDefVO.setStartCode(warehouseShelve.getStartCode());

        warehouseShelfDefVO.setEndCode(warehouseShelve.getEndCode());
        /**
         * 名称
         */
        warehouseShelfDefVO.setName(warehouseShelve.getName());

        /**
         * 状态（0-禁用；1-启用）
         */
        warehouseShelfDefVO.setStatus(warehouseShelve.getStatus());

        /**
         * 数量
         */
        warehouseShelfDefVO.setQuantity(shelfIds.get(warehouseShelve.getId()));

        /**
         * 货位质量状态描述
         */
        for(Stock stock : defShelfs){
            if(stock.getShelfId().equals(warehouseShelve.getId())){
                warehouseShelfDefVO.setShelfStatusDesc(stock.getShelfStatusDesc());
                warehouseShelfDefVO.setStock(stock);
            }
        }

        return warehouseShelfDefVO;

    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "WarehouseShelfDefVO{" +
                "shelfId=" + shelfId +
                ", code='" + code + '\'' +
                ", prefix='" + prefix + '\'' +
                ", startCode='" + startCode + '\'' +
                ", endCode='" + endCode + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", stock=" + stock +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                '}';
    }
}