package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @ClassName: GoodsLockVO
* @Description:  储存管理-商品处理-药品锁定
* @author zhengbin.jin
* @version 1.0
* @date 2017-09-27 17:26:40
*/
@ApiModel(value = "GoodsLockVO", description = "储存管理-商品处理-药品锁定")
public class GoodsLockExcelVO implements Serializable {

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
   private String productDateStr;

   public String getProductDateStr() {
      return DateUtils.DateToString(this.productDate,"yyyy-MM-dd");
   }

   public void setProductDateStr(String productDateStr) {
      this.productDateStr = productDateStr;
   }

   public String getValidDateStr() {
      return DateUtils.DateToString(this.validDate,"yyyy-MM-dd");
   }

   public void setValidDateStr(String validDateStr) {
      this.validDateStr = validDateStr;
   }

   private String validDateStr;
   /**
    * 商品编码
    */
   @ApiModelProperty(value = "商品编码")
   private String goodsCode;
   /**
    * 商品通用名称
    */
   @ApiModelProperty(value = "商品通用名称")
   private String goodsGenericName;
   /**
    * 单位名称
    */
   @ApiModelProperty(value = "单位名称")
   private String unitName;

   /**
    * 商品规格
    */
   @ApiModelProperty(value = "商品规格")
   private String goodsSpecification;

   /**
    * 生产厂商
    */
   @ApiModelProperty(value = "生产厂商")
   private String manufacturer;

   /**
    * 剂型名称
    */
   @ApiModelProperty(value = "剂型名称")
   private String dosageName;

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

   public String getGoodsCode() {
      return goodsCode;
   }

   public void setGoodsCode(String goodsCode) {
      this.goodsCode = goodsCode;
   }

   public String getGoodsGenericName() {
      return goodsGenericName;
   }

   public void setGoodsGenericName(String goodsGenericName) {
      this.goodsGenericName = goodsGenericName;
   }

   public String getUnitName() {
      return unitName;
   }

   public void setUnitName(String unitName) {
      this.unitName = unitName;
   }

   public String getGoodsSpecification() {
      return goodsSpecification;
   }

   public void setGoodsSpecification(String goodsSpecification) {
      this.goodsSpecification = goodsSpecification;
   }

   public String getManufacturer() {
      return manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public String getDosageName() {
      return dosageName;
   }

   public void setDosageName(String dosageName) {
      this.dosageName = dosageName;
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
}