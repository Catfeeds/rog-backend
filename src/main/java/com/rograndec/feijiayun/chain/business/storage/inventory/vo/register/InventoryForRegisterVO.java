package com.rograndec.feijiayun.chain.business.storage.inventory.vo.register;

import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryDetailForRegisterVO;
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
public class InventoryForRegisterVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 盘点日期
     */
    @ApiModelProperty(value = "盘点日期")
    private Date invDate;
    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;

    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    /**
     * 登记方法
     */
    @ApiModelProperty(value = "登记方法(0-按账面登记；1-按实物登记)")
    private Integer registerType;

    /**
     * 盘点人员ID
     */
    @ApiModelProperty(value = "盘点人员ID")
    private Long invManId;

    /**
     * 复盘人员ID
     */
    @ApiModelProperty(value = "复盘人员ID")
    private Long secondInvManId;

    @ApiModelProperty(value = "暂存的rediskey")
    private String redisKeyValue;


    private List<InventoryDetailForRegisterVO> detailForAddVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSecondInvManId() {
        return secondInvManId;
    }

    public void setSecondInvManId(Long secondInvManId) {
        this.secondInvManId = secondInvManId;
    }


    public List<InventoryDetailForRegisterVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForRegisterVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public Long getRegisterManId() {
        return registerManId;
    }

    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }
}