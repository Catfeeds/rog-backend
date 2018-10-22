package com.rograndec.feijiayun.chain.business.storage.inventory.vo.register;

import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryDetailForOrderDetailVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
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
public class InventoryForRegisterVO2 implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 盘点方法（0-按货位；1-按商品）
     */
    @ApiModelProperty(value = "盘点方法（0-按货位；1-按商品）")
    private Integer invType;


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
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员名称")
    private String registerManName;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记方法登记方法(0-按账面登记；1-按实物登记)")
    private Integer registerType;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "盘点人员ID")
    private Long invManId;

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
     * 复盘人员名称
     */
    @ApiModelProperty(value = "复盘人员名称")
    private String secondInvManName;


    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;


    private List<InventoryDetailForOrderDetailVO> detailForAddVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getRegisterManId() {
        return registerManId;
    }

    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Long getInvManId() {
        return invManId;
    }

    public void setInvManId(Long invManId) {
        this.invManId = invManId;
    }

    public String getInvManName() {
        return invManName;
    }

    public void setInvManName(String invManName) {
        this.invManName = invManName;
    }

    public Long getSecondInvManId() {
        return secondInvManId;
    }

    public void setSecondInvManId(Long secondInvManId) {
        this.secondInvManId = secondInvManId;
    }

    public String getSecondInvManName() {
        return secondInvManName;
    }

    public void setSecondInvManName(String secondInvManName) {
        this.secondInvManName = secondInvManName;
    }

    public List<InventoryDetailForOrderDetailVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForOrderDetailVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}