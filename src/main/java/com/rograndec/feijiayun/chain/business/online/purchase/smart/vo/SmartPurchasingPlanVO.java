package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import com.rograndec.feijiayun.chain.business.online.purchase.constant.OperationType;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartPurchasingPlan;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author  Lei.Su
 */
public class SmartPurchasingPlanVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long tid;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long id;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String genericName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 生产企业ID
     */
    @ApiModelProperty(value = "生产企业ID")
    private Long manufacturerId;

    /**
     * 生产企业
     */
    @ApiModelProperty(value = "生产企业")
    private String manufacturer;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private BigDecimal stockQuantity;

    /**
     * 缺货数量
     */
    @ApiModelProperty(value = "缺货数量")
    private BigDecimal lackQuantity;

    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量")
    private BigDecimal quantity;

    /**
     * 0系统添加，1手动添加
     */
    @ApiModelProperty(value = "0系统添加，1手动添加")
    private Integer type;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getLackQuantity() {
        return lackQuantity;
    }

    public void setLackQuantity(BigDecimal lackQuantity) {
        this.lackQuantity = lackQuantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "SmartPurchasingPlanVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", genericName='" + genericName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", lackQuantity=" + lackQuantity +
                ", quantity=" + quantity +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static SmartPurchasingPlanVO convertTOVO(PurchasePlanGoodsVO vo) {
        SmartPurchasingPlanVO planVO = new SmartPurchasingPlanVO();
        planVO.setId(vo.getId());
        planVO.setCode(vo.getCode());
        planVO.setName(vo.getName());
        planVO.setGenericName(vo.getGenericName());
        planVO.setManufacturerId(vo.getManufacturerId());
        planVO.setManufacturer(vo.getManufacturer());
        planVO.setStockQuantity(vo.getStockQuantity());
        planVO.setLackQuantity(vo.getQuantity());
        planVO.setQuantity(vo.getQuantity());
        planVO.setType(OperationType.SYSTEM_ADD.getType());
        return planVO;
    }

    public static SmartPurchasingPlan convertTOEntity(SmartPurchasingPlanVO vo, UserVO userVO) throws Exception {
        SmartPurchasingPlan plan = new SmartPurchasingPlan();
        plan.setGoodsId(vo.getId());
        plan.setPurchaseQuantity(vo.getQuantity());
        plan.setType(OperationType.AUTO_ADD.getType());
        UserEnterpriseUtils.setUserCreateOrModify(plan,userVO,true);
        plan.setEnterpriseId(userVO.getEnterpriseId());
        return plan;
    }
}
