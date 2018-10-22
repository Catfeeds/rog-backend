package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 13:19
 */

public class RequestParamForListVO extends RequestBaseParamVO {

    @ApiModelProperty("单号 0/倒序；1/正序;默认0")
    private Integer codeOrder = 0;


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
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消")
    private String status;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "处理人员名称")
    private String handleManName;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public Integer getInvRange() {
        return invRange;
    }

    public void setInvRange(Integer invRange) {
        this.invRange = invRange;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCodeOrder() {
        if(codeOrder == null){
            return 0;
        }
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }
}