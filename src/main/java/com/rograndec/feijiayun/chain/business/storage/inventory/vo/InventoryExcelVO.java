package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_inventory
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryExcelVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 单据类型（5301）
     */
    @ApiModelProperty(value = "单据类型（5301）")
    private Integer orderType;

    /**
     * 盘点创建日期
     */
    @ApiModelProperty(value = "盘点日期")
    private Date invDate;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;

    /**
     * 盘点方法（0-按货位；1-按商品）
     */
    @ApiModelProperty(value = "盘点方法（0-按货位；1-按商品）")
    private Integer invType;

    /**
     * 盘点范围（0-全盘；1-抽盘）
     */
    @ApiModelProperty(value = "盘点范围（0-全盘；1-抽盘）")
    private Integer invRange;

    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private String warehouseAreaIds;

    /**
     * 库区名称
     */
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaNames;

    /**
     * 货区／柜组ID
     */
    @ApiModelProperty(value = "货区／柜组ID")
    private String cargoAreaIds;

    /**
     * 货区／柜组名称
     */
    @ApiModelProperty(value = "货区／柜组名称")
    private String cargoAreaNames;

    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;

    /**
     * 登记人员编码
     */
    @ApiModelProperty(value = "登记人员编码")
    private String registerManCode;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员名称")
    private String registerManName;

    /**
     * 登记方法登记方法
     */
    @ApiModelProperty(value = "登记方法登记方法(0-按账面登记；1-按实物登记)")
    private Integer registerType;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "盘点人员ID")
    private Long invManId;

    /**
     * 盘点人员编码
     */
    @ApiModelProperty(value = "盘点人员编码")
    private String invManCode;

    /**
     * 盘点人员名称
     */
    @ApiModelProperty(value = "盘点人员名称")
    private String invManName;

    /**
     * 复盘人员ID
     */
    @ApiModelProperty(value = "复盘人员ID")
    private Long secondInvManId;

    /**
     * 复盘人员编码
     */
    @ApiModelProperty(value = "复盘人员编码")
    private String secondInvManCode;

    /**
     * 复盘人员名称
     */
    @ApiModelProperty(value = "复盘人员名称")
    private String secondInvManName;

    /**
     * 处理日期
     */
    @ApiModelProperty(value = "处理日期")
    private Date handleDate;

    /**
     * 处理人员ID
     */
    @ApiModelProperty(value = "处理人员ID")
    private Long handleManId;

    /**
     * 处理人员编码
     */
    @ApiModelProperty(value = "处理人员编码")
    private String handleManCode;

    /**
     * 处理人员名称
     */
    @ApiModelProperty(value = "处理人员名称")
    private String handleManName;

    /**
     * 过账日期
     */
    @ApiModelProperty(value = "过账日期")
    private Date postDate;

    /**
     * 过账人员ID
     */
    @ApiModelProperty(value = "过账人员ID")
    private Long postManId;

    /**
     * 过账人员编码
     */
    @ApiModelProperty(value = "过账人员编码")
    private String postManCode;

    /**
     * 过账人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

    /**
     * 账面数量合计
     */
    @ApiModelProperty(value = "账面数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 实盘数量合计
     */
    @ApiModelProperty(value = "实盘数量合计")
    private BigDecimal invQuantityTotal;

    /**
     * 损益数量合计
     */
    @ApiModelProperty(value = "损益数量合计")
    private BigDecimal diffQuantityTotal;

    /**
     * 账面金额合计
     */
    @ApiModelProperty(value = "账面金额合计")
    private BigDecimal amountTotal;

    /**
     * 实盘金额合计
     */
    @ApiModelProperty(value = "实盘金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 损益金额合计
     */
    @ApiModelProperty(value = "损益金额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税账面金额合计
     */
    @ApiModelProperty(value = "不含税账面金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 不含税实盘金额合计
     */
    @ApiModelProperty(value = "不含税实盘金额合计")
    private BigDecimal realNotaxAmountTotal;

    /**
     * 不含税损益金额合计
     */
    @ApiModelProperty(value = "不含税损益金额合计")
    private BigDecimal diffNotaxAmountTotal;

    /**
     * 账面税额合计
     */
    @ApiModelProperty(value = "账面税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 实盘税额合计
     */
    @ApiModelProperty(value = "实盘税额合计")
    private BigDecimal realTaxAmountTotal;

    /**
     * 损益税额合计
     */
    @ApiModelProperty(value = "损益税额合计")
    private BigDecimal diffTaxAmountTotal;

    /**
     * 账面零售金额合计
     */
    @ApiModelProperty(value = "账面零售金额合计")
    private BigDecimal retailAmountTotal;

    /**
     * 实盘零售金额合计
     */
    @ApiModelProperty(value = "实盘零售金额合计")
    private BigDecimal realRetailAmountTotal;

    /**
     * 损益零售金额合计
     */
    @ApiModelProperty(value = "损益零售金额合计")
    private BigDecimal diffRetailAmountTotal;

    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private List<InventoryDetailForOrderDetailVO> detailForAddVOList;

    /**
     * saas_inventory
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据类型（5301）
     * @return order_type 单据类型（5301）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5301）
     * @param orderType 单据类型（5301）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 盘点创建日期
     * @return inv_date 盘点创建日期
     */
    public Date getInvDate() {
        return invDate;
    }

    /**
     * 盘点创建日期
     * @param invDate 盘点创建日期
     */
    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    /**
     * 盘点单号
     * @return code 盘点单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 盘点单号
     * @param code 盘点单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 盘点方法（0-按货位；1-按商品）
     * @return inv_type 盘点方法（0-按货位；1-按商品）
     */
    public Integer getInvType() {
        return invType;
    }

    /**
     * 盘点方法（0-按货位；1-按商品）
     * @param invType 盘点方法（0-按货位；1-按商品）
     */
    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    /**
     * 盘点范围（0-全盘；1-抽盘）
     * @return inv_range 盘点范围（0-全盘；1-抽盘）
     */
    public Integer getInvRange() {
        return invRange;
    }

    /**
     * 盘点范围（0-全盘；1-抽盘）
     * @param invRange 盘点范围（0-全盘；1-抽盘）
     */
    public void setInvRange(Integer invRange) {
        this.invRange = invRange;
    }

    /**
     * 仓库ID
     * @return warehouse_id 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 仓库ID
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 仓库名称
     * @return warehouse_name 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 仓库名称
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getWarehouseAreaIds() {
        return warehouseAreaIds;
    }

    public void setWarehouseAreaIds(String warehouseAreaIds) {
        this.warehouseAreaIds = warehouseAreaIds;
    }

    public String getWarehouseAreaNames() {
        return warehouseAreaNames;
    }

    public void setWarehouseAreaNames(String warehouseAreaNames) {
        this.warehouseAreaNames = warehouseAreaNames;
    }

    public String getCargoAreaIds() {
        return cargoAreaIds;
    }

    public void setCargoAreaIds(String cargoAreaIds) {
        this.cargoAreaIds = cargoAreaIds;
    }

    public String getCargoAreaNames() {
        return cargoAreaNames;
    }

    public void setCargoAreaNames(String cargoAreaNames) {
        this.cargoAreaNames = cargoAreaNames;
    }

    /**
     * 登记日期
     * @return register_date 登记日期
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 登记日期
     * @param registerDate 登记日期
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 登记人员ID
     * @return register_man_id 登记人员ID
     */
    public Long getRegisterManId() {
        return registerManId;
    }

    /**
     * 登记人员ID
     * @param registerManId 登记人员ID
     */
    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    /**
     * 登记人员编码
     * @return register_man_code 登记人员编码
     */
    public String getRegisterManCode() {
        return registerManCode;
    }

    /**
     * 登记人员编码
     * @param registerManCode 登记人员编码
     */
    public void setRegisterManCode(String registerManCode) {
        this.registerManCode = registerManCode == null ? null : registerManCode.trim();
    }

    /**
     * 登记人员名称
     * @return register_man_name 登记人员名称
     */
    public String getRegisterManName() {
        return registerManName;
    }

    /**
     * 登记人员名称
     * @param registerManName 登记人员名称
     */
    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName == null ? null : registerManName.trim();
    }

    /**
     * 登记人员ID
     * @return inv_man_id 登记人员ID
     */
    public Long getInvManId() {
        return invManId;
    }

    /**
     * 登记人员ID
     * @param invManId 登记人员ID
     */
    public void setInvManId(Long invManId) {
        this.invManId = invManId;
    }

    /**
     * 盘点人员编码
     * @return inv_man_code 盘点人员编码
     */
    public String getInvManCode() {
        return invManCode;
    }

    /**
     * 盘点人员编码
     * @param invManCode 盘点人员编码
     */
    public void setInvManCode(String invManCode) {
        this.invManCode = invManCode == null ? null : invManCode.trim();
    }

    /**
     * 盘点人员名称
     * @return inv_man_name 盘点人员名称
     */
    public String getInvManName() {
        return invManName;
    }

    /**
     * 盘点人员名称
     * @param invManName 盘点人员名称
     */
    public void setInvManName(String invManName) {
        this.invManName = invManName == null ? null : invManName.trim();
    }

    /**
     * 复盘人员ID
     * @return second_inv_man_id 复盘人员ID
     */
    public Long getSecondInvManId() {
        return secondInvManId;
    }

    /**
     * 复盘人员ID
     * @param secondInvManId 复盘人员ID
     */
    public void setSecondInvManId(Long secondInvManId) {
        this.secondInvManId = secondInvManId;
    }

    /**
     * 复盘人员编码
     * @return second_inv_man_code 复盘人员编码
     */
    public String getSecondInvManCode() {
        return secondInvManCode;
    }

    /**
     * 复盘人员编码
     * @param secondInvManCode 复盘人员编码
     */
    public void setSecondInvManCode(String secondInvManCode) {
        this.secondInvManCode = secondInvManCode == null ? null : secondInvManCode.trim();
    }

    /**
     * 复盘人员名称
     * @return second_inv_man_name 复盘人员名称
     */
    public String getSecondInvManName() {
        return secondInvManName;
    }

    /**
     * 复盘人员名称
     * @param secondInvManName 复盘人员名称
     */
    public void setSecondInvManName(String secondInvManName) {
        this.secondInvManName = secondInvManName == null ? null : secondInvManName.trim();
    }

    /**
     * 处理日期
     * @return handle_date 处理日期
     */
    public Date getHandleDate() {
        return handleDate;
    }

    /**
     * 处理日期
     * @param handleDate 处理日期
     */
    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    /**
     * 处理人员ID
     * @return handle_man_id 处理人员ID
     */
    public Long getHandleManId() {
        return handleManId;
    }

    /**
     * 处理人员ID
     * @param handleManId 处理人员ID
     */
    public void setHandleManId(Long handleManId) {
        this.handleManId = handleManId;
    }

    /**
     * 处理人员编码
     * @return handle_man_code 处理人员编码
     */
    public String getHandleManCode() {
        return handleManCode;
    }

    /**
     * 处理人员编码
     * @param handleManCode 处理人员编码
     */
    public void setHandleManCode(String handleManCode) {
        this.handleManCode = handleManCode == null ? null : handleManCode.trim();
    }

    /**
     * 处理人员名称
     * @return handle_man_name 处理人员名称
     */
    public String getHandleManName() {
        return handleManName;
    }

    /**
     * 处理人员名称
     * @param handleManName 处理人员名称
     */
    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName == null ? null : handleManName.trim();
    }

    /**
     * 过账日期
     * @return post_date 过账日期
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * 过账日期
     * @param postDate 过账日期
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * 过账人员ID
     * @return post_man_id 过账人员ID
     */
    public Long getPostManId() {
        return postManId;
    }

    /**
     * 过账人员ID
     * @param postManId 过账人员ID
     */
    public void setPostManId(Long postManId) {
        this.postManId = postManId;
    }

    /**
     * 过账人员编码
     * @return post_man_code 过账人员编码
     */
    public String getPostManCode() {
        return postManCode;
    }

    /**
     * 过账人员编码
     * @param postManCode 过账人员编码
     */
    public void setPostManCode(String postManCode) {
        this.postManCode = postManCode == null ? null : postManCode.trim();
    }

    /**
     * 过账人员名称
     * @return post_man_name 过账人员名称
     */
    public String getPostManName() {
        return postManName;
    }

    /**
     * 过账人员名称
     * @param postManName 过账人员名称
     */
    public void setPostManName(String postManName) {
        this.postManName = postManName == null ? null : postManName.trim();
    }

    /**
     * 账面数量合计
     * @return quantity_total 账面数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 账面数量合计
     * @param quantityTotal 账面数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 实盘数量合计
     * @return inv_quantity_total 实盘数量合计
     */
    public BigDecimal getInvQuantityTotal() {
        return invQuantityTotal;
    }

    /**
     * 实盘数量合计
     * @param invQuantityTotal 实盘数量合计
     */
    public void setInvQuantityTotal(BigDecimal invQuantityTotal) {
        this.invQuantityTotal = invQuantityTotal;
    }

    /**
     * 损益数量合计
     * @return diff_quantity_total 损益数量合计
     */
    public BigDecimal getDiffQuantityTotal() {
        return diffQuantityTotal;
    }

    /**
     * 损益数量合计
     * @param diffQuantityTotal 损益数量合计
     */
    public void setDiffQuantityTotal(BigDecimal diffQuantityTotal) {
        this.diffQuantityTotal = diffQuantityTotal;
    }

    /**
     * 账面金额合计
     * @return amount_total 账面金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 账面金额合计
     * @param amountTotal 账面金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 实盘金额合计
     * @return real_amount_total 实盘金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实盘金额合计
     * @param realAmountTotal 实盘金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 损益金额合计
     * @return diff_amount_total 损益金额合计
     */
    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    /**
     * 损益金额合计
     * @param diffAmountTotal 损益金额合计
     */
    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    /**
     * 不含税账面金额合计
     * @return notax_amount_total 不含税账面金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税账面金额合计
     * @param notaxAmountTotal 不含税账面金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    /**
     * 不含税实盘金额合计
     * @return real_notax_amount_total 不含税实盘金额合计
     */
    public BigDecimal getRealNotaxAmountTotal() {
        return realNotaxAmountTotal;
    }

    /**
     * 不含税实盘金额合计
     * @param realNotaxAmountTotal 不含税实盘金额合计
     */
    public void setRealNotaxAmountTotal(BigDecimal realNotaxAmountTotal) {
        this.realNotaxAmountTotal = realNotaxAmountTotal;
    }

    /**
     * 不含税损益金额合计
     * @return diff_notax_amount_total 不含税损益金额合计
     */
    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    /**
     * 不含税损益金额合计
     * @param diffNotaxAmountTotal 不含税损益金额合计
     */
    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    /**
     * 账面税额合计
     * @return tax_amount_total 账面税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 账面税额合计
     * @param taxAmountTotal 账面税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 实盘税额合计
     * @return real_tax_amount_total 实盘税额合计
     */
    public BigDecimal getRealTaxAmountTotal() {
        return realTaxAmountTotal;
    }

    /**
     * 实盘税额合计
     * @param realTaxAmountTotal 实盘税额合计
     */
    public void setRealTaxAmountTotal(BigDecimal realTaxAmountTotal) {
        this.realTaxAmountTotal = realTaxAmountTotal;
    }

    /**
     * 损益税额合计
     * @return diff_tax_amount_total 损益税额合计
     */
    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    /**
     * 损益税额合计
     * @param diffTaxAmountTotal 损益税额合计
     */
    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    /**
     * 账面零售金额合计
     * @return retail_amount_total 账面零售金额合计
     */
    public BigDecimal getRetailAmountTotal() {
        return retailAmountTotal;
    }

    /**
     * 账面零售金额合计
     * @param retailAmountTotal 账面零售金额合计
     */
    public void setRetailAmountTotal(BigDecimal retailAmountTotal) {
        this.retailAmountTotal = retailAmountTotal;
    }

    /**
     * 实盘零售金额合计
     * @return real_retail_amount_total 实盘零售金额合计
     */
    public BigDecimal getRealRetailAmountTotal() {
        return realRetailAmountTotal;
    }

    /**
     * 实盘零售金额合计
     * @param realRetailAmountTotal 实盘零售金额合计
     */
    public void setRealRetailAmountTotal(BigDecimal realRetailAmountTotal) {
        this.realRetailAmountTotal = realRetailAmountTotal;
    }

    /**
     * 损益零售金额合计
     * @return diff_retail_amount_total 损益零售金额合计
     */
    public BigDecimal getDiffRetailAmountTotal() {
        return diffRetailAmountTotal;
    }

    /**
     * 损益零售金额合计
     * @param diffRetailAmountTotal 损益零售金额合计
     */
    public void setDiffRetailAmountTotal(BigDecimal diffRetailAmountTotal) {
        this.diffRetailAmountTotal = diffRetailAmountTotal;
    }

    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     * @return status 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     * @param status 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderType=").append(orderType);
        sb.append(", invDate=").append(invDate);
        sb.append(", code=").append(code);
        sb.append(", invType=").append(invType);
        sb.append(", invRange=").append(invRange);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", warehouseName=").append(warehouseName);

        sb.append(", registerDate=").append(registerDate);
        sb.append(", registerManId=").append(registerManId);
        sb.append(", registerManCode=").append(registerManCode);
        sb.append(", registerManName=").append(registerManName);
        sb.append(", invManId=").append(invManId);
        sb.append(", invManCode=").append(invManCode);
        sb.append(", invManName=").append(invManName);
        sb.append(", secondInvManId=").append(secondInvManId);
        sb.append(", secondInvManCode=").append(secondInvManCode);
        sb.append(", secondInvManName=").append(secondInvManName);
        sb.append(", handleDate=").append(handleDate);
        sb.append(", handleManId=").append(handleManId);
        sb.append(", handleManCode=").append(handleManCode);
        sb.append(", handleManName=").append(handleManName);
        sb.append(", postDate=").append(postDate);
        sb.append(", postManId=").append(postManId);
        sb.append(", postManCode=").append(postManCode);
        sb.append(", postManName=").append(postManName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", invQuantityTotal=").append(invQuantityTotal);
        sb.append(", diffQuantityTotal=").append(diffQuantityTotal);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", diffAmountTotal=").append(diffAmountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", realNotaxAmountTotal=").append(realNotaxAmountTotal);
        sb.append(", diffNotaxAmountTotal=").append(diffNotaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", realTaxAmountTotal=").append(realTaxAmountTotal);
        sb.append(", diffTaxAmountTotal=").append(diffTaxAmountTotal);
        sb.append(", retailAmountTotal=").append(retailAmountTotal);
        sb.append(", realRetailAmountTotal=").append(realRetailAmountTotal);
        sb.append(", diffRetailAmountTotal=").append(diffRetailAmountTotal);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public List<InventoryDetailForOrderDetailVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForOrderDetailVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }
}